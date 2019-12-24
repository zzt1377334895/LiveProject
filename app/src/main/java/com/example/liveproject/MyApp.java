package com.example.liveproject;

import android.app.Application;

import com.tencent.rtmp.TXLiveBase;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String licenceURL = "http://license.vod2.myqcloud.com/license/v1/5c1b9230c0f4cbfd6d7d3ee0fd65f1c6/TXLiveSDK.licence"; // 获取到的 licence url
        String licenceKey = "45a52ff3827b11de5217d3f65b97befb"; // 获取到的 licence key
        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);
    }
}
