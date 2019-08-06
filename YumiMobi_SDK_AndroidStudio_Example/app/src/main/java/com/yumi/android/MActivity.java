package com.yumi.android;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.yumimobi.ads.R;


public abstract class MActivity extends Activity {

    protected String channelStr;
    protected String versionStr;
    protected SharedPreferences sp;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        channelStr = getIntent().getStringExtra("channel");
        versionStr = getIntent().getStringExtra("version");
        Log.i("MaActivity", channelStr + " " + versionStr);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        initView();
        setListener();
        onActivityCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public String getStringConfig(String key) {
        return sp.getString(key, "");
    }

    public String getStringConfig(String key, String defaul) {
        return sp.getString(key, defaul);
    }

    public boolean getBooleanConfig(String key) {
        return sp.getBoolean(key, false);
    }


    public abstract void onActivityCreate();

    public abstract void initView();

    public abstract void setListener();

    public void setAcTitle(String title) {
        TextView tv = (TextView) findViewById(R.id.tv_title);
        if (tv != null) {
            tv.setText(title);
            tv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
