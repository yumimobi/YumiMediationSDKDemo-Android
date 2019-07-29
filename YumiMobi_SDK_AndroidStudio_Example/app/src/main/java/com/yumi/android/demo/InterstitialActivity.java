package com.yumi.android.demo;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.publish.AdError;
import com.yumi.android.sdk.ads.publish.YumiInterstitial;
import com.yumi.android.sdk.ads.publish.listener.IYumiInterstitialListener;
import com.yumimobi.ads.R;


public class InterstitialActivity extends MActivity implements OnClickListener {
    private static final String TAG = "InterstitialActivity";

    private TextView info;
    private YumiInterstitial interstitial;
    private IYumiInterstitialListener interstitialListener;

    @Override
    public void initView() {
        setContentView(R.layout.activity_interstitial);
        setAcTitle("Interstitial");
        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.isReady).setOnClickListener(this);

        info = findViewById(R.id.info);
        info.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                info.setText("");
                return true;
            }
        });
    }

    @Override
    public void setListener() {
        /*
         * First step:
         * create IYumiInterstitialListener to get the banner request statue.
         */
        interstitialListener = new IYumiInterstitialListener() {

            @Override
            public void onInterstitialPreparedFailed(AdError error) {
                Log.e(TAG, "on interstitial prepared failed " + error);
                setInfo("on interstitial prepared failed " + error);
            }

            @Override
            public void onInterstitialPrepared() {
                Log.e(TAG, "on interstitial prepared ");
                setInfo("on interstitial prepared ");
            }

            @Override
            public void onInterstitialExposure() {
                Log.e(TAG, "on interstitial exposure ");
                setInfo("on interstitial exposure ");
            }

            @Override
            public void onInterstitialClosed() {
                Log.e(TAG, "on interstitial closed  ");
                setInfo("on interstitial closed ");
            }

            @Override
            public void onInterstitialExposureFailed(AdError adError) {
                Log.e(TAG, "on interstitial exposure failed  " + adError);
                setInfo("on interstitial exposure failed " + adError);
            }

            @Override
            public void onInterstitialClicked() {
                Log.e(TAG, "on interstitial clicked ");
                setInfo("on interstitial clicked ");
            }

            @Override
            public void onInterstitialStartPlaying() {
                Log.e(TAG, "on interstitial start playing ");
                setInfo("on interstitial start playing ");
            }

        };
    }

    @Override
    public void onActivityCreate() {
        /*
         * Second step :
         * create YumiInterstitial instance by activity and your YumiID , and invoke SDK method by this instance.
         */
        interstitial = new YumiInterstitial(InterstitialActivity.this, getStringConfig("interstitial_slotID"), true);
        // setChannelID (optional)
        interstitial.setChannelID(channelStr);
        // setVersionName (optional)
        interstitial.setVersionName(versionStr);
        // setInterstitialEventListener (optional)
        interstitial.setInterstitialEventListener(interstitialListener);
        // requestYumiInterstitial (optional)
        interstitial.requestYumiInterstitial();

        setInfo("load Interstitial");
    }

    /*
     * Third step :
     * Invoke showInterstitial method when you want exposure interstitial ads.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                if (interstitial != null) {
                    if (interstitial.isReady()) {
                        interstitial.showInterstitial();
                    }
                }
                break;
            case R.id.isReady:
                if (interstitial != null) {
                    if (interstitial.isReady()) {
                        Log.e(TAG, "interstitial prepared ");
                        setInfo("interstitial prepared");
                    } else {
                        Log.e(TAG, "interstitial prepared failed");
                        setInfo("interstitial prepared failed");
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (interstitial != null) {
            interstitial.destroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (interstitial.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    private void setInfo(final String msg) {
        Log.e(TAG, "set info  thread " + Thread.currentThread().getId());
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (info != null) {
                    info.append(msg + "\n");
                }
            }
        });
    }
}
