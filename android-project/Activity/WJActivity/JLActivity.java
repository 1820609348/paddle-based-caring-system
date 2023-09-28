package com.example.myapp.Activity.WJActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class JLActivity extends AppCompatActivity {
    private Button recordButton; // 记录按钮
    private Button stopButton; // 停止按钮
    private Button viewButton; // 查看按钮
    private TextView statusText; // 状态文本
    private Button deleteButton;
    private Button recordButton2; // 记录按钮
    private Button recordButton3; // 记录按钮
    private File recordFile; // 记录文件
    private FileOutputStream fos; // 文件输出流
    private Timer timer; // 定时器
    private SimpleDateFormat sdf; // 日期格式化

    SensorEvent linAcc;
    SensorEvent gyro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jlactivity);
        ImageButton button_exit = findViewById(R.id.baisefanhui);
        button_exit.setOnClickListener(view -> {
            finish();
        });
        // 初始化控件
        recordButton = findViewById (R.id.record_button);
        stopButton = findViewById (R.id.stop_button);
        viewButton = findViewById (R.id.view_button);
        statusText = findViewById (R.id.status_text);
        deleteButton = findViewById (R.id.button_delete);

        recordButton2 = findViewById(R.id.record_button2);
        recordButton3 = findViewById(R.id.record_button3);
        // 初始化日期格式化
        sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss", Locale.getDefault ());

        // 获取传感器管理器
        SensorManager sensorManager = (SensorManager) getSystemService (Context.SENSOR_SERVICE);
// 获取线性加速度传感器
        Sensor linAccSensor = sensorManager.getDefaultSensor (Sensor.TYPE_LINEAR_ACCELERATION);
// 获取陀螺仪传感器
        Sensor gyroSensor = sensorManager.getDefaultSensor (Sensor.TYPE_GYROSCOPE);
// 创建线性加速度传感器监听器
        SensorEventListener linAccListener = new SensorEventListener () {
            @Override
            public void onSensorChanged (SensorEvent event) {
                // 将传感器事件中的值赋给 linAcc 变量
                linAcc = event;
            }

            @Override
            public void onAccuracyChanged (Sensor sensor, int accuracy) {
                // 不做任何操作
            }
        };
// 创建陀螺仪传感器监听器
        SensorEventListener gyroListener = new SensorEventListener () {
            @Override
            public void onSensorChanged (SensorEvent event) {
                // 将传感器事件中的值赋给 gyro 变量
                gyro = event;
            }

            @Override
            public void onAccuracyChanged (Sensor sensor, int accuracy) {
                // 不做任何操作
            }
        };
// 注册线性加速度传感器监听器，指定采样频率为最快
        sensorManager.registerListener (linAccListener, linAccSensor, SensorManager.SENSOR_DELAY_FASTEST);
// 注册陀螺仪传感器监听器，指定采样频率为最快
        sensorManager.registerListener (gyroListener, gyroSensor, SensorManager.SENSOR_DELAY_FASTEST);

        // 设置记录按钮的点击事件
        recordButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                startRecord1 (); // 开始记录
            }
        });
        recordButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecord2();
            }
        });
        recordButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecord3();
            }
        });

        // 设置停止按钮的点击事件
        stopButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                stopRecord (); // 停止记录
            }
        });

        // 设置查看按钮的点击事件
        viewButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                viewRecord (); // 查看记录
            }
        });
        // 设置删除按钮的点击事件
        deleteButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                deleteRecord (); // 删除记录
            }
        });

    }
    // 删除记录的方法
    private void deleteRecord () {
        // 获取记录文件夹的路径
        String path = getExternalFilesDir ("record").toString ();
        // 创建文件对象，指定路径
        File directory = new File (path);
        // 获取文件夹中的所有文件
        File [] files = directory.listFiles ();
        // 判断文件夹是否为空
        if (files != null && files.length > 0) {
            // 创建一个字符串数组，用于存储文件名
            String [] fileNames = new String [files.length];
            // 遍历文件数组，获取文件名
            for (int i = 0; i < files.length; i++) {
                fileNames [i] = files [i].getName ();
            }
            // 创建一个对话框，用于选择要删除的文件
            AlertDialog.Builder builder = new AlertDialog.Builder (this);
            builder.setTitle ("请选择要删除的记录文件"); // 设置对话框标题
            builder.setItems (fileNames, new DialogInterface.OnClickListener () { // 设置对话框选项
                @Override
                public void onClick (DialogInterface dialog, int which) {
                    // 点击某个选项时，删除对应的文件，并提示删除成功
                    deleteFile (files [which]);
                    Toast.makeText (JLActivity.this, "删除成功", Toast.LENGTH_SHORT).show ();
                }
            });
            builder.show (); // 显示对话框
        } else {
            statusText.setText ("没有找到记录文件"); // 更新状态文本，显示没有找到记录文件
        }
    }
    // 删除文件的方法
    private void deleteFile (File file) {
        if (file != null && file.exists ()) {
            file.delete (); // 调用文件对象的 delete 方法删除文件
        }
    }


    // 开始记录的方法
    private void startRecord1 () {
        try {
            // 获取当前时间作为文件名
            String fileName = sdf.format (new Date ()) +"-1" + ".txt";
            // 创建文件对象，指定存储路径和文件名
            recordFile = new File (getExternalFilesDir ("record"), fileName);
            // 创建文件输出流，用于写入文件内容
            fos = new FileOutputStream (recordFile);
            // 创建定时器，用于每隔一秒执行一次任务
            timer = new Timer ();
            String line = "1"+"\n";
            // 将字符串转换为字节数组
            byte [] bytes = line.getBytes ();
            // 将字节数组写入文件输出流
            fos.write (bytes);
            // 刷新文件输出流，确保内容被写入文件中
            fos.flush ();
            // 创建定时任务，用于获取当前时间、线性加速度和角速度并写入文件
            TimerTask task = new TimerTask () {
                @Override
                public void run () {
                    try {
                        // 获取当前时间并格式化为字符串
                        String time = sdf.format (new Date ());
                        // 获取线性加速度传感器的值
                        float linAccX = linAcc.values [0];
                        float linAccY = linAcc.values [1];
                        float linAccZ = linAcc.values [2];
                        // 获取陀螺仪传感器的值
                        float gyroX = gyro.values [0];
                        float gyroY = gyro.values [1];
                        float gyroZ = gyro.values [2];
                        // 将时间、线性加速度和角速度拼接成一行字符串
                        String line = linAccX + ";" + linAccY + ";" + linAccZ + ";" + gyroX + ";" + gyroY + ";" + gyroZ + "\n";
                        // 将字符串转换为字节数组
                        byte [] bytes = line.getBytes ();
                        // 将字节数组写入文件输出流
                        fos.write (bytes);
                        // 刷新文件输出流，确保内容被写入文件中
                        fos.flush ();
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }
                }
            };
            // 启动定时器，指定任务、延迟时间和间隔时间（单位为毫秒）
            timer.schedule (task, 0, 1000);
            // 更新状态文本，显示正在记录中
            statusText.setText ("正在记录中...");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
    // 开始记录的方法
    private void startRecord2 () {
        try {
            // 获取当前时间作为文件名
            String fileName = sdf.format (new Date ()) +"-2" +".txt";
            // 创建文件对象，指定存储路径和文件名
            recordFile = new File (getExternalFilesDir ("record"), fileName);
            // 创建文件输出流，用于写入文件内容
            fos = new FileOutputStream (recordFile);
            // 创建定时器，用于每隔一秒执行一次任务
            timer = new Timer ();
            String line = "2"+"\n";
            // 将字符串转换为字节数组
            byte [] bytes = line.getBytes ();
            // 将字节数组写入文件输出流
            fos.write (bytes);
            // 刷新文件输出流，确保内容被写入文件中
            fos.flush ();
            // 创建定时任务，用于获取当前时间、线性加速度和角速度并写入文件
            TimerTask task = new TimerTask () {
                @Override
                public void run () {
                    try {
                        // 获取当前时间并格式化为字符串
                        String time = sdf.format (new Date ());
                        // 获取线性加速度传感器的值
                        float linAccX = linAcc.values [0];
                        float linAccY = linAcc.values [1];
                        float linAccZ = linAcc.values [2];
                        // 获取陀螺仪传感器的值
                        float gyroX = gyro.values [0];
                        float gyroY = gyro.values [1];
                        float gyroZ = gyro.values [2];
                        // 将时间、线性加速度和角速度拼接成一行字符串
                        String line = linAccX + ";" + linAccY + ";" + linAccZ + ";" + gyroX + ";" + gyroY + ";" + gyroZ + "\n";
                        // 将字符串转换为字节数组
                        byte [] bytes = line.getBytes ();
                        // 将字节数组写入文件输出流
                        fos.write (bytes);
                        // 刷新文件输出流，确保内容被写入文件中
                        fos.flush ();
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }
                }
            };
            // 启动定时器，指定任务、延迟时间和间隔时间（单位为毫秒）
            timer.schedule (task, 0, 1000);
            // 更新状态文本，显示正在记录中
            statusText.setText ("正在记录中...");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
    // 开始记录的方法
    private void startRecord3 () {
        try {
            // 获取当前时间作为文件名
            String fileName = sdf.format (new Date ()) +"-3" +".txt";
            // 创建文件对象，指定存储路径和文件名
            recordFile = new File (getExternalFilesDir ("record"), fileName);
            // 创建文件输出流，用于写入文件内容
            fos = new FileOutputStream (recordFile);
            // 创建定时器，用于每隔一秒执行一次任务
            timer = new Timer ();
            String line = "3"+"\n";
            // 将字符串转换为字节数组
            byte [] bytes = line.getBytes ();
            // 将字节数组写入文件输出流
            fos.write (bytes);
            // 刷新文件输出流，确保内容被写入文件中
            fos.flush ();
            // 创建定时任务，用于获取当前时间、线性加速度和角速度并写入文件
            TimerTask task = new TimerTask () {
                @Override
                public void run () {
                    try {
                        // 获取当前时间并格式化为字符串
                        String time = sdf.format (new Date ());
                        // 获取线性加速度传感器的值
                        float linAccX = linAcc.values [0];
                        float linAccY = linAcc.values [1];
                        float linAccZ = linAcc.values [2];
                        // 获取陀螺仪传感器的值
                        float gyroX = gyro.values [0];
                        float gyroY = gyro.values [1];
                        float gyroZ = gyro.values [2];
                        // 将时间、线性加速度和角速度拼接成一行字符串
                        String line = linAccX + ";" + linAccY + ";" + linAccZ + ";" + gyroX + ";" + gyroY + ";" + gyroZ + "\n";
                        // 将字符串转换为字节数组
                        byte [] bytes = line.getBytes ();
                        // 将字节数组写入文件输出流
                        fos.write (bytes);
                        // 刷新文件输出流，确保内容被写入文件中
                        fos.flush ();
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }
                }
            };
            // 启动定时器，指定任务、延迟时间和间隔时间（单位为毫秒）
            timer.schedule (task, 0, 1000);
            // 更新状态文本，显示正在记录中
            statusText.setText ("正在记录中...");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
    // 停止记录的方法
    private void stopRecord () {
        try {
            if (timer != null) {
                timer.cancel (); // 取消定时器
                timer = null;
            }
            if (fos != null) {
                fos.close (); // 关闭文件输出流
                fos = null;
            }
            if (recordFile != null) {
                recordFile = null;
            }
            statusText.setText ("记录已停止"); // 更新状态文本，显示记录已停止

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    // 查看记录的方法
    private void viewRecord () {
        // 获取记录文件夹的路径
        String path = getExternalFilesDir ("record").toString ();
        // 创建文件对象，指定路径
        File directory = new File (path);
        // 获取文件夹中的所有文件
        File [] files = directory.listFiles ();
        // 判断文件夹是否为空
        if (files != null && files.length > 0) {
            // 创建一个字符串数组，用于存储文件名
            String [] fileNames = new String [files.length];
            // 遍历文件数组，获取文件名
            for (int i = 0; i < files.length; i++) {
                fileNames [i] = files [i].getName ();
            }
            // 创建一个对话框，用于显示文件列表
            AlertDialog.Builder builder = new AlertDialog.Builder (this);
            builder.setTitle ("记录文件列表"); // 设置对话框标题
            builder.setItems (fileNames, new DialogInterface.OnClickListener () { // 设置对话框选项
                @Override
                public void onClick (DialogInterface dialog, int which) {
                    // 点击某个选项时，打开对应的文件并显示内容
                    openFile(files [which]);
                }
            });
            builder.show (); // 显示对话框
        } else {
            statusText.setText ("没有找到记录文件"); // 更新状态文本，显示没有找到记录文件
        }
    }
    // 打开文件的方法
    private void openFile (File file) {
        try {
            // 创建一个字符缓冲输入流，用于读取文件内容
            BufferedReader br = new BufferedReader (new FileReader(file));
            // 创建一个字符串缓冲区，用于存储文件内容
            StringBuilder sb = new StringBuilder ();
            // 逐行读取文件内容，并添加到字符串缓冲区中
            String line;
            while ((line = br.readLine ()) != null) {
                sb.append (line).append ("\n");
            }
            br.close (); // 关闭字符缓冲输入流

            // 创建一个对话框，用于显示文件内容
            AlertDialog.Builder builder = new AlertDialog.Builder (this);
            builder.setTitle ("记录文件内容"); // 设置对话框标题
            builder.setMessage (sb.toString ()); // 设置对话框内容
            builder.setPositiveButton ("确定", null); // 设置对话框确定按钮
            builder.show (); // 显示对话框

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
