package com.yumi.android;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.yumi.android.demo.BannerActivity;
import com.yumi.android.demo.BannerActivity_MT;
import com.yumi.android.demo.InterstitialActivity;
import com.yumi.android.demo.InterstitialActivity_MT;
import com.yumi.android.demo.MediaActivity;
import com.yumi.android.demo.NativeActivity;
import com.yumi.android.sdk.ads.publish.YumiSettings;
import com.yumimobi.ads.demo.R;

public class MainActivity extends MActivity implements OnClickListener, OnCheckedChangeListener {

    private Button btn_banner_a;
    private Button btn_banner_m;
    private Button btn_interstitial_a;
    private Button btn_interstitial_m;
    private Button btn_media;
    private Button btn_splash;
    private Button btn_native;
    private Button btn_startDebugging;
    private EditText channel;
    private EditText version;
    private EditText banner_slotID, interstitial_slotID, mdia_slotID, splash_slotID, native_slotID;
    private CheckBox cb_isMatchWindowWidth;
    private boolean isMatchWindowWidth;

    private Button anaylsis;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        btn_banner_a = (Button) findViewById(R.id.btn_banner_a);
        btn_banner_m = (Button) findViewById(R.id.btn_banner_m);
        btn_interstitial_a = (Button) findViewById(R.id.btn_interstitial_a);
        btn_interstitial_m = (Button) findViewById(R.id.btn_interstitial_m);
        btn_media = (Button) findViewById(R.id.btn_media_a);
        btn_splash = (Button) findViewById(R.id.btn_splash);
        btn_native = (Button) findViewById(R.id.btn_native);
        btn_startDebugging = (Button) findViewById(R.id.btn_startDebugging);
        anaylsis = (Button) findViewById(R.id.btn_anaylsis);

        banner_slotID = (EditText) findViewById(R.id.banner_slotID);
        banner_slotID.clearFocus();
        interstitial_slotID = (EditText) findViewById(R.id.interstitial_slotID);
        interstitial_slotID.clearFocus();
        mdia_slotID = (EditText) findViewById(R.id.mdia_slotID);
        mdia_slotID.clearFocus();
        splash_slotID = (EditText) findViewById(R.id.splash_slotID);
        splash_slotID.clearFocus();
        native_slotID = (EditText) findViewById(R.id.native_slotID);
        native_slotID.clearFocus();

        channel = (EditText) findViewById(R.id.channel);
        channel.clearFocus();
        version = (EditText) findViewById(R.id.version);
        version.clearFocus();

        cb_isMatchWindowWidth = (CheckBox) findViewById(R.id.cb_isMatchWindowWidth);

        channel.setText(getStringConfig("channel"));
        version.setText(getStringConfig("version"));
        isMatchWindowWidth = getBooleanConfig("isMatchWindowWidth");
        cb_isMatchWindowWidth.setChecked(isMatchWindowWidth);
        YumiSettings.runInCheckPermission(true);

        banner_slotID.setText(getStringConfig("banner_slotID", ""));
        interstitial_slotID.setText(getStringConfig("interstitial_slotID", ""));
        mdia_slotID.setText(getStringConfig("mdia_slotID", ""));
        splash_slotID.setText(getStringConfig("splash_slotID", ""));
        native_slotID.setText(getStringConfig("native_slotID", ""));
    }

    @Override
    public void setListener() {
        btn_banner_a.setOnClickListener(this);
        btn_banner_m.setOnClickListener(this);
        btn_interstitial_a.setOnClickListener(this);
        btn_interstitial_m.setOnClickListener(this);
        btn_media.setOnClickListener(this);
        btn_splash.setOnClickListener(this);
        btn_native.setOnClickListener(this);
        btn_startDebugging.setOnClickListener(this);
        cb_isMatchWindowWidth.setOnCheckedChangeListener(this);

        anaylsis.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton b, boolean flag) {
        if (b == cb_isMatchWindowWidth) {
            sp.edit().putBoolean("isMatchWindowWidth", flag).commit();
            isMatchWindowWidth = flag;
        }
    }

    @Override
    public void onClick(View v) {
        String cha = channel.getText().toString();
        String ver = version.getText().toString();
        String bslotID = banner_slotID.getText().toString();
        String islotID = interstitial_slotID.getText().toString();
        String mslotID = mdia_slotID.getText().toString();
        String sslotID = splash_slotID.getText().toString();
        String nslotID = native_slotID.getText().toString();

        sp.edit().putString("channel", cha).commit();
        sp.edit().putString("version", ver).commit();
        sp.edit().putString("banner_slotID", bslotID).commit();
        sp.edit().putString("interstitial_slotID", islotID).commit();
        sp.edit().putString("mdia_slotID", mslotID).commit();
        sp.edit().putString("splash_slotID", sslotID).commit();
        sp.edit().putString("native_slotID", nslotID).commit();

        if (v.getId() == R.id.btn_startDebugging) {
            YumiSettings.startDebugging(MainActivity.this, bslotID, islotID, mslotID, nslotID,cha, ver);
            return;
        }
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_banner_a:
                intent.setClass(MainActivity.this, BannerActivity.class);
                intent.putExtra("isMatchWindowWidth", isMatchWindowWidth);
                break;
            case R.id.btn_banner_m:
                intent.setClass(MainActivity.this, BannerActivity_MT.class);
                intent.putExtra("isMatchWindowWidth", isMatchWindowWidth);
                break;
            case R.id.btn_interstitial_a:
                intent.setClass(MainActivity.this, InterstitialActivity.class);
                break;
            case R.id.btn_interstitial_m:
                intent.setClass(MainActivity.this, InterstitialActivity_MT.class);
                break;
            case R.id.btn_media_a:
                intent.setClass(MainActivity.this, MediaActivity.class);
                break;
            case R.id.btn_splash:
                intent.setClass(MainActivity.this, SplashTestActivity.class);
                break;
            case R.id.btn_native:
                intent.setClass(MainActivity.this, NativeActivity.class);
                break;
            default:
                break;
        }
        intent.putExtra("channel", cha);
        intent.putExtra("version", ver);
        startActivity(intent);
    }

    @Override
    public void onActivityCreate() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
