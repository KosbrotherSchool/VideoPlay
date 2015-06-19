package com.jasonko.videoplay;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.jasonko.videolib.Video;
import com.jasonko.videolib.VideoAPI;
import com.jasonko.youtubeplay3.R;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DownloadVideosTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            myVideos = VideoAPI.getYoutubeVideos("animation");
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            ListVideoAdapter videoAdapter = new ListVideoAdapter(
                    MainActivity.this,myVideos);
            myListView.setAdapter(videoAdapter);
        }
    }

}
