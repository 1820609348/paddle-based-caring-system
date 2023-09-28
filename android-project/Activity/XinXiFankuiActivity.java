package com.example.myapp.Activity;

import static com.example.myapp.utils.ConnectUtils.IP_URL;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapp.R;
import com.example.myapp.utils.HttpUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class XinXiFankuiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_xi_fankui);

        Intent intent0 = getIntent();
        String username = intent0.getStringExtra("admin");


        Button button_upload = findViewById(R.id.upload_button);
        ImageButton button_chakanfankui = findViewById(R.id.chakanfankui_btn);

        button_chakanfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(XinXiFankuiActivity.this,ChaKanFanKuiActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });


        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(XinXiFankuiActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(XinXiFankuiActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else
                    //访问相册
                    InputPicture();

            }
        });

        ImageButton button1 = (ImageButton) findViewById(R.id.baisefanhui);
        button1.setOnClickListener(view -> {
            finish();
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                InputPicture();
            } else {
                Toast.makeText(this, "你拒绝打开此权限，无法进行下一步操作！", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void InputPicture() {
        //Intent.ACTION_PICK 从数据中选择一个项目 (item)，将被选中的项目返回。
        //MediaStore.Images.Media.EXTERNAL_CONTENT_URI 获取外部的URI
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //参数一:对应的数据的URI 参数二:使用该函数表示要查找文件的MIME类型
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            ImageView imageView = findViewById(R.id.image_upload);
            imageView.setImageURI(selectedImage);

            EditText editText1 = findViewById(R.id.shurukuang1);
            String title = editText1.getText().toString().trim();
            System.out.println(title);

            EditText editText2 = findViewById(R.id.shurukuang2);
            String fankuineirong = editText2.getText().toString().trim();











            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.e("TAG", "onActivityResult: " + picturePath );

            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.P)
                @Override
                public void run() {


//                    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//                    Date date = new Date();//获得系统时间.
//                    String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.
//                    Timestamp transfer_timestamp =Timestamp.valueOf(nowTime);//把时间转换
//                    System.out.println(transfer_timestamp);
//

                    File file = new File(picturePath);
                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)//请求类型
                            .addFormDataPart("fankui_title",title )//参数1
                            .addFormDataPart("fankui_describe",fankuineirong)
                            .addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/*"), file)) // 第一个参数传到服务器的字段名，第二个你自己的文件名，第三个MediaType.parse("*/*")数据类型，这个是所有类型的意思,file就是我们之前创建的全局file，里面是创建的图片
                            .build();
                    //2、调用工具类上传图片以及参数
                    HttpUtil.uploadFile(IP_URL+"/testApi/POSTApi/upload", requestBody, new Callback() {

                        //请求失败回调函数
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println("=============");
                            System.out.println("异常：：");
                            e.printStackTrace();
                        }

                        //请求成功响应函数
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            showResponse(response.body().string());//在主线程中显示提示框
                        }

                        //ui操作，提示框
                        private void showResponse(final String response) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // 在这里进行UI操作，将结果显示到界面上
                                    Toast.makeText(XinXiFankuiActivity.this, response, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }).start();

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}