package com.abohomol.videolap;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startPlayback();
        initViewPager();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        stopPlayback();
    }

    private void initViewPager() {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TransparentLapView view = new TransparentLapView(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            view.setContentToDisplay(String.valueOf(i));
            views.add(view);
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(views));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.dotsLayout);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private void startPlayback() {
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        videoView.start();
    }

    private void stopPlayback() {
        if (videoView != null)
            videoView.stopPlayback();
    }
}
