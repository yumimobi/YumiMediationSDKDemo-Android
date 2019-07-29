package com.yumi.android.demo;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.publish.AdError;
import com.yumi.android.sdk.ads.publish.YumiSplash;
import com.yumi.android.sdk.ads.publish.listener.IYumiSplashListener;
import com.yumimobi.ads.R;

import static com.yumi.android.sdk.ads.publish.YumiSplash.DEFAULT_FETCH_SECONDS;

/**
 * Description:
 * <p>
 * Created by lgd on 2019-05-30.
 */
public class SplashActivity extends MActivity {
    private static final String TAG = "SplashActivity";
    private YumiSplash mYumiSplash;
    private View mHackerView;
    private EditText mFetchTime;
    private TextView mLogView;
    private boolean canBack = true;

    @Override
    public void onActivityCreate() {
        setContentView(R.layout.activity_splash);
        mLogView = findViewById(R.id.splash_log_text_view);
        mFetchTime = findViewById(R.id.fetch_time_edit_text);
        mFetchTime.setText(String.valueOf(DEFAULT_FETCH_SECONDS));
        FrameLayout splashContainer = findViewById(R.id.splash_container);

        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        String key = sp.getString("splash_slotID", "");
        if (TextUtils.isEmpty(key)) {
            Toast.makeText(this, "not input appID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        mYumiSplash = new YumiSplash(this, splashContainer, key);
        mYumiSplash.setChannelID(channelStr);
        mYumiSplash.setVersionName(versionStr);
        mYumiSplash.setLaunchImage(getResources().getDrawable(R.drawable.splash_drawable));
        mYumiSplash.setSplashListener(new IYumiSplashListener() {
            @Override
            public void onSplashAdSuccessToShow() {
                addLog("onSplashAdSuccessToShow");
            }

            @Override
            public void onSplashAdFailToShow(AdError error) {
                addLog("onSplashAdFailToShow: " + error);
                launchMainActivity();
                canBack = true;
            }

            @Override
            public void onSplashAdClicked() {
                addLog("onSplashAdClicked");
            }

            @Override
            public void onSplashAdClosed() {
                addLog("onSplashAdClosed");
                canBack = true;
                launchMainActivity();
            }
        });
    }

    @Override
    public void initView() {
    }

    @Override
    public void setListener() {
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
        Log.d(TAG, "addContentView: " + view);
        mHackerView = view;
    }

    private void launchMainActivity() {
        if (mHackerView != null && mHackerView.getParent() instanceof ViewGroup) {
            ((ViewGroup) mHackerView.getParent()).removeView(mHackerView);
        }
    }

    public void loadAd(View view) {
        canBack = false;
        mYumiSplash.setFetchTime(getFetchSeconds());
        mYumiSplash.loadAdAndShowInWindow();
    }

    private int getFetchSeconds() {
        if (!TextUtils.isEmpty(mFetchTime.getText().toString().trim())) {
            return Integer.valueOf(mFetchTime.getText().toString().trim());
        }
        return DEFAULT_FETCH_SECONDS;
    }

    private void addLog(String msg) {
        Log.d(TAG, "addLog: " + msg);
        mLogView.setText(String.format("%s\n%s", mLogView.getText(), msg));
    }

    @Override
    public void onBackPressed() {
        if (canBack) {
            super.onBackPressed();
        }
    }
}
