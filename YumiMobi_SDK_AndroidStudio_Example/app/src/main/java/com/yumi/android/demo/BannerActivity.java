package com.yumi.android.demo;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.publish.AdError;
import com.yumi.android.sdk.ads.publish.YumiBanner;
import com.yumi.android.sdk.ads.publish.enumbean.AdSize;
import com.yumi.android.sdk.ads.publish.listener.IYumiBannerListener;
import com.yumi.android.sdk.ads.utils.views.AdContainer;
import com.yumimobi.ads.R;

public class BannerActivity extends MActivity {
    private static final String TAG = "BannerActivity";

    private FrameLayout bannerContainer;
    private YumiBanner banner;
    private IYumiBannerListener bannerListener;
    private TextView text;
    private AdSize mAdSize = AdSize.BANNER_SIZE_AUTO;
    private boolean isMatchWindowWidth;
    private boolean isAutoLoad = true;
    private boolean isContainerFirst = true;

    private CheckBox mMatchParentCheckBox;
    private CheckBox mAutoloadCheckBox;
    private CheckBox mContainerFirstCheckBox;

    @Override
    public void initView() {
        setContentView(R.layout.activity_banner);
        setAcTitle("Banner_Code");
        text = findViewById(R.id.textView2);
        bannerContainer = findViewById(R.id.banner_container);
        text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                text.setText("");
                return true;
            }
        });

        mMatchParentCheckBox = findViewById(R.id.match_parent_check_box);
        mMatchParentCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isMatchWindowWidth = isChecked;
            }
        });

        mAutoloadCheckBox = findViewById(R.id.auto_load_next_banner_checkbox);
        mAutoloadCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAutoLoad = isChecked;
            }
        });

        mContainerFirstCheckBox = findViewById(R.id.container_first_checkbox);
        mContainerFirstCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isContainerFirst = isChecked;
            }
        });
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
                Log.e(TAG, "on banner prepared failed " + errorCode);
                addLog("on banner prepared failed " + errorCode);
            }

            @Override
            public void onBannerPrepared() {
                Log.e(TAG, "on banner prepared");
                addLog("on banner prepared");
                if (!isContainerFirst) {
                    bannerContainer.removeAllViews();
                    AdContainer adContainer = banner.getBannerView();
                    bannerContainer.addView(adContainer);
                }
            }

            @Override
            public void onBannerExposure() {
                Log.e(TAG, "on banner exposure");
                addLog("on banner exposure");
            }

            @Override
            public void onBannerClosed() {
                Log.e(TAG, "on banner close ");

                addLog("on banner close");
            }

            @Override
            public void onBannerClicked() {
                Log.e(TAG, "on banner clicked ");
                addLog("on banner clicked");
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
        banner = new YumiBanner(BannerActivity.this, getStringConfig("banner_slotID"), isAutoLoad);
        //setBannerContainer
        if (isContainerFirst) {
            banner.setBannerContainer(bannerContainer, mAdSize, isMatchWindowWidth);
        } else {
            banner.setBannerContainer(null, mAdSize, isMatchWindowWidth);
        }
        //setChannelID . (Recommend)
        banner.setChannelID(channelStr);
        // set channel and version (optional)
        banner.setDefaultChannelAndVersion(getApplicationContext());
        //setVersionName . (Recommend)
        banner.setVersionName(versionStr);
        //setBannerEventListener. (Recommend)
        banner.setBannerEventListener(bannerListener);

        mAutoloadCheckBox.setEnabled(false);
        mMatchParentCheckBox.setEnabled(false);
        mContainerFirstCheckBox.setEnabled(false);
    }


    @Override
    protected void onDestroy() {
        if (banner != null) {
            banner.onDestroy();
            banner = null;
        }
        super.onDestroy();
    }

    private void addLog(final String msg) {
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
        addLog("load banner");
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

        mContainerFirstCheckBox.setEnabled(true);
        mMatchParentCheckBox.setEnabled(true);
        mAutoloadCheckBox.setEnabled(true);
    }

    public void dismissBanner(View view) {
        if (banner != null) {
            banner.dismissBanner();
        }
    }

    public void resumeBanner(View view) {
        if (banner != null) {
            banner.resumeBanner();
        }
    }
}
