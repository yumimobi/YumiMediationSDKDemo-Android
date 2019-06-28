package com.yumi.android.demo;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.publish.AdError;
import com.yumi.android.sdk.ads.publish.YumiBanner;
import com.yumi.android.sdk.ads.publish.enumbean.AdSize;
import com.yumi.android.sdk.ads.publish.listener.IYumiBannerListener;
import com.yumimobi.ads.R;

public class BannerActivity extends MActivity {

    private FrameLayout bannerContainer;
    private YumiBanner banner;
    private IYumiBannerListener bannerListener;
    private TextView text;
    private AdSize mAdSize = AdSize.BANNER_SIZE_AUTO;

    @Override
    public void initView() {
        setContentView(R.layout.activity_banner);
        setAcTitle("Banner_Code");
        text = (TextView) findViewById(R.id.textView2);

        /*
         * First step:
         *  create banner container , this container is a viewgroup, and add the container into your activity content view.
         */
        bannerContainer = new FrameLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        addContentView(bannerContainer, params);
    }

    @Override
    public void setListener() {
        /*
         * Second step:
         * create IYumiBannerListener to get the banner request statue.
         */
        bannerListener = new IYumiBannerListener() {

            @Override
            public void onBannerPreparedFailed(AdError errorCode) {
                Log.e("mikoto", "on banner prepared failed " + errorCode);
                setInfo("on banner prepared failed " + errorCode);
            }

            @Override
            public void onBannerPrepared() {
                Log.e("mikoto", "on banner prepared");
                setInfo("on banner prepared");
            }

            @Override
            public void onBannerExposure() {
                Log.e("mikoto", "on banner exposure");
                setInfo("on banner exposure");
            }

            @Override
            public void onBannerClosed() {
                Log.e("mikoto", "on banner close ");

                setInfo("on banner close");
            }

            @Override
            public void onBannerClicked() {
                Log.e("mikoto", "on banner clicked ");
                setInfo("on banner clicked");
            }
        };

    }

    @Override
    public void onActivityCreate() {
    }

    private void createBanner() {
        /*
         * Thrid step :
         * create YumiBanner instance by activity and your YumiID.
         */
        banner = new YumiBanner(BannerActivity.this, getStringConfig("banner_slotID"), true);
        //setBannerContainer
        banner.setBannerContainer(bannerContainer, mAdSize, isMatchWindowWidth);
        //setChannelID . (Recommend)
        banner.setChannelID(channelStr);
        // set channel and version (optional)
        banner.setDefaultChannelAndVersion(getApplicationContext());
        //setVersionName . (Recommend)
        banner.setVersionName(versionStr);
        //setBannerEventListener. (Recommend)
        banner.setBannerEventListener(bannerListener);
    }


    @Override
    protected void onDestroy() {
        if (banner != null) {
            banner.onDestroy();
            banner = null;
        }
        super.onDestroy();
    }

    private void setInfo(final String msg) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (text != null) {
                    text.append(msg + "\n");
                }

            }
        });
    }

    public void loadAd(View view) {
        if (banner == null) {
            createBanner();
        }
        banner.requestYumiBanner();
    }

    public void onAutoModelClicked(View view) {
        mAdSize = AdSize.BANNER_SIZE_AUTO;
    }

    public void on320x50ModelClicked(View view) {
        mAdSize = AdSize.BANNER_SIZE_320X50;
    }

    public void on728x90ModelClicked(View view) {
        mAdSize = AdSize.BANNER_SIZE_728X90;
    }

    public void onSmartModelClicked(View view) {
        mAdSize = AdSize.BANNER_SIZE_SMART;
    }

    public void onDestroyBannerClicked(View view) {
        if (banner == null) {
            Toast.makeText(this, "Do not have Banner yet.", Toast.LENGTH_SHORT).show();
            return;
        }
        banner.destroy();
        banner = null;
    }
}
