package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.bluetooth.le.ScanSettings;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textView;
    private DownloadFilesTask downloadFilesTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_name);
        progressBar = findViewById(R.id.progress_bar);
        downloadFilesTask = new DownloadFilesTask();
        downloadFilesTask.execute();
    }

    private class DownloadFilesTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {//子线程中进行耗时任务
            try {
                int count = 0;
                int length = 1;
                while (count < 99) {
                    count+=length;
                    publishProgress(count);

                }
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {//最先执行，主线程中更新UI
            textView.setText("正在加载");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String aLong) {//返回结果到主线程
            super.onPostExecute(aLong);
            textView.setText("加载完成");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {//更新进度到主线程
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            textView.setText("百分比"+values[0]+"%");

        }

        @Override
        protected void onCancelled() {
            textView.setText("取消");
            progressBar.setProgress(0);
            super.onCancelled();
        }
    }
}
