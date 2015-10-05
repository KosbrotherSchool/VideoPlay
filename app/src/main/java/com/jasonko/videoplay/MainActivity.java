package com.jasonko.videoplay;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.jasonko.videolib.Video;
import com.jasonko.videolib.VideoAPI;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ArrayList<Video> myVideos = new ArrayList<Video>();
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.listView);

        new DownloadVideosTask().execute();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private class DownloadVideosTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            // 從網路上拿資料回來
            myVideos = VideoAPI.getYoutubeVideos("animation");
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {

            // 處理網路上拿回來的資料, 並產生一個 adapter
            ListVideoAdapter videoAdapter = new ListVideoAdapter(MainActivity.this,myVideos);

            // 利用 adapter 將資料顯示在 ListView 上
            myListView.setAdapter(videoAdapter);
        }
    }

}
