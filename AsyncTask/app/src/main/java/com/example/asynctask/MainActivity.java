package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private class DownloadFilesTask extends AsyncTask<URL, Integer, Bitmap>{
         URL url;
         HttpURLConnection connection = null;
         Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(URL... urls) {//子线程中进行耗时任务
            try {
                url=urls[0];
                connection=(HttpURLConnection)url.openConnection();
                connection.setConnectTimeout(6000);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                InputStream inputStream=connection.getInputStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {//最先执行，主线程中更新UI
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap aLong) {//返回结果到主线程
            super.onPostExecute(aLong);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {//更新进度到主线程
            super.onProgressUpdate(values);
        }
    }
}
