package com.jasonko.videoplay;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jasonko.videolib.ImageLoader;
import com.jasonko.videolib.Video;

/**
 * Created by kolichung on 6/17/15.
 */
public class VideoActivity extends Activity {

    Video mVideo;
    ImageView vImage;
    TextView vTitle;
    TextView vDesc;
    Button btnShare;
    Button btnToVimeo;
    ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video);
        mVideo = (Video) getIntent().getSerializableExtra("video");

        vImage = (ImageView) findViewById(R.id.video_imageview);
        vTitle = (TextView) findViewById(R.id.video_text_title);
        vDesc = (TextView) findViewById(R.id.video_text_description);
        btnShare = (Button) findViewById(R.id.button_share);
        btnToVimeo = (Button) findViewById(R.id.button_vimeo);

        mImageLoader = new ImageLoader(this);

        mImageLoader.DisplayImage(mVideo.getThumbnail_large(), vImage);
        vTitle.setText(mVideo.getTitle());
        vDesc.setText(Html.fromHtml(mVideo.getDescription()));

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "分享影片給您:" + mVideo.getLink());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        btnToVimeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri youtubeUri = Uri.parse(mVideo.getLink());
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,youtubeUri);
                startActivity(browserIntent);
            }
        });
    }
}
