package com.example.liveproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TXCloudVideoView video_view;
    TXLivePlayer mLivePlayer;
    private Button btn_start;
    private Button btn_tui;
    private TXLivePusher mLivePusher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //创建 player 对象
        mLivePlayer = new TXLivePlayer(this);
        //关键 player 对象与界面 view
        mLivePusher = new TXLivePusher(this);//推流对象
        TXLivePushConfig mLivePushConfig  = new TXLivePushConfig();
        // 一般情况下不需要修改 config 的默认配置
        mLivePlayer.setPlayerView(video_view);
//        mLivePusher.startCameraPreview(video_view);//将流预览到videoview
    }

    private void initView() {
        video_view = (TXCloudVideoView) findViewById(R.id.texview);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_tui = (Button) findViewById(R.id.btn_tui);
        btn_start.setOnClickListener(this);
        btn_tui.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:

                String flvUrl = "https://v-ngb.qf.56.com/live/5460201_1577174284786_400.flv";
                mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV); //推荐 FLV
                break;
            case R.id.btn_tui:
                String rtmpURL = "rtmp://62431.livepush.myqcloud.com/live/jkl?txSecret=e821a9ab3613f2618d2260fab6d4f2ba&txTime=5E0235FF"; //此处填写您的 rtmp 推流地址
                int ret = mLivePusher.startPusher(rtmpURL.trim());
                if (ret == -5) {
                    Toast.makeText(this, "License 校验失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        video_view.onDestroy();
    }

}
