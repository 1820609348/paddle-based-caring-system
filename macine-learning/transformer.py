# Data Handling
import numpy as np
import pandas as pd

pd.set_option("display.max_columns", None)
import matplotlib.pyplot as plt

plt.style.use("ggplot")

# Preprocessing
from sklearn.model_selection import train_test_split as tts
import torch
from torch import nn
from torch.utils.data import Dataset, DataLoader
from torch.optim import SGD
from torch.optim.lr_scheduler import StepLR

# Warnings
import warnings

warnings.filterwarnings("ignore")
data = pd.read_csv("./full_dataset.csv")
X = data.drop("label", axis=1)
y = data["label"]
SEED = 123
X_train, X_test, y_train, y_test = tts(X, y,
                                       test_size=0.2,
                                       shuffle=True,
                                       random_state=SEED,
                                       stratify=y)


# 构造数据集类
class FallDownDataSet(Dataset):
    def __init__(self, x, y):
        self.data = torch.Tensor(x)
        self.label = torch.Tensor(y)

    def __getitem__(self, index):
        return self.data[index], self.label[index]

    def __len__(self):
        return len(self.data)


# 构建 LSTM 模型
class LSTM_RNN(nn.Module):
    def __init__(self, batchsize, epoch, learning_rate, device=None):
        super(LSTM_RNN, self).__init__()
        # 定义batch_size和epoch
        self.device = device
        self.batchsize = batchsize
        self.learning_rate = learning_rate
        self.epoch = epoch
        self.hidden_state = 32
        self.labelMapping = {'motion': 0, 'step': 1, 'fall': 2, 'idle': 3,
                             0: 'motion', 1: "step", 2: 'fall', 3: "idle"}
        # 定义layer
        self.lstm1 = nn.LSTM(input_size=6, hidden_size=32, num_layers=3, batch_first=True,
                             dropout=0.2)

        self.fcn1 = nn.Sequential(
            nn.Linear(32, 168),
            nn.BatchNorm1d(160),
            nn.SELU()
        )
        self.fcn_out = nn.Sequential(
            nn.Linear(168, 4),
            nn.Softmax()
        )

    def forward(self, x):
        x = self.lstm1(x)[0]
        x = self.fcn1(x)
        x = self.fcn_out(x)
        x = x[:, -1, :]
        return x

    def transform_x(self, x):
        if isinstance(x, pd.DataFrame):
            x = torch.Tensor(x.values)
        x = x.reshape(-1, 6, 160).permute(0, 2, 1)
        if self.device:
            x = x.to(device)
        return x

    def transform_y(self, y):
        y_ = []
        for i in range(len(y)):
            y_.append(self.labelMapping[y.iloc[i]])
        y_ = torch.Tensor(y_).long()
        if self.device:
            y_ = y_.to(device)
        return y_

    def dataLoder(self, x, y):
        x = self.transform_x(x)
        y = self.transform_y(y)
        return DataLoader(dataset=FallDownDataSet(x, y), batch_size=self.batchsize, shuffle=True)

    def fit(self, x, y):
        optimizer = SGD(self.parameters(), lr=self.learning_rate, momentum=0.9)
        # lr_step = StepLR(optimizer, step_size=3, gamma=0.9)
        criterion = nn.CrossEntropyLoss()
        for i in range(self.epoch):
            total_loss = 0
            for x_batch, y_batch in self.dataLoder(x, y):
                y_pred_batch = self.forward(x_batch)
                optimizer.zero_grad()
                loss = criterion(y_pred_batch, y_batch.long()).mean()
                total_loss += loss
                loss.backward()
                optimizer.step()
            # lr_step.step()
            print("loss", float(total_loss))

    def predict(self, x):
        x = self.transform_x(x)
        y_pred_raw = self.forward(x)
        y_pred = torch.argmax(y_pred_raw, dim=1)
        return y_pred

    def score(self, x, y):
        # x = self.transform_x(x)
        y = self.transform_y(y)
        y_pred = self.predict(x)
        return torch.sum(y == y_pred) / len(y)


torch.cuda.empty_cache()
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
tf = LSTM_RNN(batchsize=32, epoch=20, learning_rate=0.1, device=device)
tf.to(device)
tf.fit(X_train, y_train)
print(tf.score(X_test, y_test))
