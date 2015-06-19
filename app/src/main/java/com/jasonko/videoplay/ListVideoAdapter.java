package com.jasonko.videoplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jasonko.videolib.ImageLoader;
import com.jasonko.videolib.Video;
import com.jasonko.youtubeplay3.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by kolichung on 6/17/15.
 */
public class ListVideoAdapter extends BaseAdapter {

    private final Activity mActivity;
    private final ArrayList<Video> data;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public ListVideoAdapter(Activity context, ArrayList<Video> d) {
        mActivity = context;
        data = d;
        inflater = (LayoutInflater) mActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = new ImageLoader(mActivity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item_video_list, null);

        TextView textTitle = (TextView) vi.findViewById(R.id.text_news_list);
        ImageView image = (ImageView) vi.findViewById(R.id.image_news_list);
        TextView textDate = (TextView) vi.findViewById(R.id.text_list_date);
        TextView textViews = (TextView) vi.findViewById(R.id.text_list_views);
        TextView textDuration = (TextView) vi.findViewById(R.id.text_list_duration);
        TextView textLikes = (TextView) vi.findViewById(R.id.text_list_like);

        // set title text
        textTitle.setText(data.get(position).getTitle());

        // set views text
        int views = data.get(position).getViewCount();
        textViews.setText(NumberFormat
                .getNumberInstance(Locale.US).format(views)+" "+"觀看次數");

        // set likes text
        textLikes.setText(data.get(position).getLikes()+" "+"喜歡");

        // set date text
        textDate.setText("發佈時間："+data.get(position).getUploadDate());

        // set duration text
        textDuration.setText("長度"+":"+data.get(position).getDuration());

        // set image
        imageLoader.DisplayImage(data.get(position).getThumbnail_small(), image);

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(mActivity, VideoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("video", data.get(position));
                newIntent.putExtras(bundle);
                mActivity.startActivity(newIntent);
            }
        });

        return vi;
    }
}
