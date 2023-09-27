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


class FCNNet(nn.Module):
    def __init__(self, batchsize, epoch):
        super(FCNNet, self).__init__()
        self.batchsize = batchsize
        self.epoch = epoch
        self.labelMapping = {'motion': 0, 'step': 1, 'fall': 2, 'idle': 3,
                             0: 'motion', 1: "step", 2: 'fall', 3: "idle"}
        self.fc1 = nn.Sequential(
            nn.Linear(960, 320),
            nn.BatchNorm1d(320),
            nn.ReLU(),
        )
        self.fc2 = nn.Sequential(
            nn.Linear(320, 64),
            nn.BatchNorm1d(64),
            nn.ReLU(),
        )
        self.fc3 = nn.Sequential(
            nn.Linear(64, 4),
            nn.Softmax(),
        )

    def forward(self, x):
        x = self.fc1(x)
        x = self.fc2(x)
        x = self.fc3(x)
        return x

    def transform_x(self, x):
        x = torch.Tensor(np.array(x))
        return x

    def transform_y(self, y):
        y_ = []
        for i in range(len(y)):
            y_.append(self.labelMapping[y.iloc[i]])
        y_ = torch.Tensor(y_)
        return y_

    def dataLoder(self, x, y):
        x = self.transform_x(x)
        y = self.transform_y(y)
        return DataLoader(dataset=FallDownDataSet(x, y), batch_size=self.batchsize, shuffle=True)

    def fit(self, x, y):
        optimizer = SGD(self.parameters(), lr=0.02, momentum=0.9)
        criterion = nn.CrossEntropyLoss()
        for i in range(self.epoch):
            for x_batch, y_batch in self.dataLoder(x, y):
                y_pred_batch = self.forward(x_batch)
                optimizer.zero_grad()
                loss = criterion(y_pred_batch, y_batch.long())
                loss.backward()
                optimizer.step()

    def predict_num(self, x):
        x = self.transform_x(x)
        y_pred_raw = self.forward(x)
        y_pred = torch.argmax(y_pred_raw, dim=1)
        return y_pred

    def predict(self, x):
        y_pred = self.predict_num(x)
        y_pred_label = []
        for i in y_pred:
            y_pred_label.append(self.labelMapping[int(i)])
        return y_pred_label

    def score(self, x, y):
        x = self.transform_x(x)
        y = self.transform_y(y)
        y_pred = self.predict_num(x)
        return torch.sum(y == y_pred) / len(y)


fcn = FCNNet(32, 10)
fcn.fit(X_train, y_train)
print(fcn.score(X_test, y_test))
