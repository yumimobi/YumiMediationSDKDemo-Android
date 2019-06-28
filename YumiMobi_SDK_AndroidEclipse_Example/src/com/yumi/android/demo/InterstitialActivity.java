package com.yumi.android.demo;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.publish.AdError;
import com.yumi.android.sdk.ads.publish.YumiInterstitial;
import com.yumi.android.sdk.ads.publish.listener.IYumiInterstitialListener;
import com.yumimobi.ads.R;


public class InterstitialActivity extends MActivity implements OnClickListener {

    private TextView info;
    private Button show;
    private Button showDelay;
    private Button cancel;
    private Button isReady;
    private YumiInterstitial interstitial;
    private IYumiInterstitialListener interstitialListener;

    @Override
    public void initView() {
        setContentView(R.layout.activity_interstitial);
        setAcTitle("Interstitial");
        show = (Button) findViewById(R.id.show);
        show.setOnClickListener(this);
        showDelay = (Button) findViewById(R.id.showDelay);
        showDelay.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        isReady = (Button) findViewById(R.id.isReady);
        isReady.setOnClickListener(this);
        info = (TextView) findViewById(R.id.info);
    }

    @Override
    public void setListener() {
        /*
         * First step:
         * create IYumiBannerListener to get the banner request statue.
         */
        interstitialListener = new IYumiInterstitialListener() {

            @Override
            public void onInterstitialPreparedFailed(AdError error) {
                Log.e("mikoto", "on interstitial prepared failed " + error);
                setInfo("on interstitial prepared failed " + error);
            }

            @Override
            public void onInterstitialPrepared() {
                Log.e("mikoto", "on interstitial prepared ");
                setInfo("on interstitial prepared ");
            }

            @Override
            public void onInterstitialExposure() {
                Log.e("mikoto", "on interstitial exposure ");
                setInfo("on interstitial exposure ");
            }

            @Override
            public void onInterstitialClosed() {
                Log.e("mikoto", "on interstitial closed  ");
                setInfo("on interstitial closed ");
            }

            @Override
            public void onInterstitialExposureFailed(AdError adError) {
                Log.e("mikoto", "on interstitial exposure failed  " + adError);
                setInfo("on interstitial exposure failed " + adError);
            }

            @Override
            public void onInterstitialClicked() {
                Log.e("mikoto", "on interstitial clicked ");
                setInfo("on interstitial clicked ");
            }

            @Override
            public void onInterstitialStartPlaying() {
                Log.e("mikoto", "on interstitial start playing ");
                setInfo("on interstitial start playing ");
            }

        };
    }

    @Override
    public void onActivityCreate() {
        /*
         * Second step :
         * create YumiInterstiital instance by activity and your YumiID , and invoke SDK method by this instance.
         */
        interstitial = new YumiInterstitial(InterstitialActivity.this, getStringConfig("interstitial_slotID"), true);
        //setChannelID . (Recommend)
        interstitial.setChannelID(channelStr);
        //setVersionName . (Recommend)
        interstitial.setVersionName(versionStr);
        //setInterstitialEventListner. (Recommend)
//		interstitial.setDefaultChannelAndVersion(getApplicationContext());
        interstitial.setInterstitialEventListener(interstitialListener);
        //requestYumiInterstitial. (Require)
        interstitial.requestYumiInterstitial();
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
                        interstitial.showInterstitial(false);
                    }
                }
                break;
            case R.id.showDelay:
                if (interstitial != null) {
                    if (interstitial.isReady()) {
                        interstitial.showInterstitial(true);
                    }
                }
                break;
            case R.id.cancel:
                if (interstitial != null) {
                    interstitial.cancelInterstitialDelayShown();
                }
                break;
            case R.id.isReady:
                if (interstitial != null) {
                    if (interstitial.isReady()) {
                        Log.e("mikoto", "interstitial prepared ");
                        setInfo("interstitial prepared");
                    } else {
                        Log.e("mikoto", "interstitial prepared failed");
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
        if (interstitial != null) {
            interstitial.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (interstitial.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    private void setInfo(final String msg) {
        Log.e("mikoto", "set info  thread " + Thread.currentThread().getId());
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
