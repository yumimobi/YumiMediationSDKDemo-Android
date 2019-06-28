package com.yumi.android.demo;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.publish.AdError;
import com.yumi.android.sdk.ads.publish.YumiMedia;
import com.yumi.android.sdk.ads.publish.enumbean.MediaStatus;
import com.yumi.android.sdk.ads.publish.listener.IYumiMediaListener;
import com.yumimobi.ads.R;

public class MediaActivity extends MActivity implements OnClickListener {

    private TextView info;
    private Button show, isPrepared, remain;
    private YumiMedia media;
    private IYumiMediaListener mediaListener;

    @Override
    public void initView() {
        setContentView(R.layout.activity_media);
        setAcTitle("Meida");
        show = (Button) findViewById(R.id.showMedia);
        show.setOnClickListener(this);
        isPrepared = (Button) findViewById(R.id.isPrepared);
        isPrepared.setOnClickListener(this);
        remain = (Button) findViewById(R.id.remain);
        remain.setOnClickListener(this);
        info = (TextView) findViewById(R.id.mediainfo);

    }

    @Override
    public void setListener() {
        /*
         * First step :
         * Create IYumiMediaListener to get media request statue.
         */
        mediaListener = new IYumiMediaListener() {

            @Override
            public void onMediaRewarded() {
                Log.e("mikoto", "on media rewarded ");
                setInfo("on media rewarded ");
            }

            @Override
            public void onMediaPrepared() {
                Log.e("mikoto", "on media  prepared ");
                setInfo("on media prepared ");
            }

            @Override
            public void onMediaPreparedFailed(AdError errorCode) {
                Log.e("mikoto", "on media preparedFailed ");
                setInfo("on media preparedFailed " + errorCode);
            }

            @Override
            public void onMediaExposure() {
                Log.e("mikoto", "on media exposure ");
                setInfo("on media exposure ");
            }

            @Override
            public void onMediaExposureFailed(AdError adError) {
                Log.e("mikoto", "on media exposure failed: " + adError);
                setInfo("on media exposure failed");
            }

            @Override
            public void onMediaClosed(boolean isRewarded) {
                Log.e("mikoto", "on media closed， isRewarded ：" + isRewarded);
                setInfo("on media closed， isRewarded ：" + isRewarded);
            }

            @Override
            public void onMediaClicked() {
                Log.e("mikoto", "on media clicked ");
                setInfo("on media clicked ");
            }

            @Override
            public void onMediaStartPlaying() {
                Log.e("mikoto", "on media start playing ");
                setInfo("on media start playing ");
            }
        };
    }

    @Override
    public void onActivityCreate() {
        /*
         * Second step :
         * Create YumiMedia instance by activity and your YumiID.
         */
        media = new YumiMedia(MediaActivity.this, getStringConfig("mdia_slotID"));
        //setMediaEventListener .  (Require)
        media.setMediaEventListener(mediaListener);
        //setChannelID .  (Recommend)
        media.setChannelID(channelStr);
        //setVersionName . (Recommend)
        media.setVersionName(versionStr);
        //requestYumiMedia . (Require)
        media.requestYumiMedia();
    }

    /*
     * Third step :
     * Invoke showMedia method when you want exposure media ads.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showMedia) {
            if (media != null) {
                if (media.isReady()) {
                    MediaStatus showMedia = media.showMedia();
                    Toast.makeText(MediaActivity.this, "media show status " + showMedia.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v.getId() == R.id.isPrepared) {
            if (media != null) {
                if (media.isReady()) {
                    Log.e("mikoto", "media prepared ");
                    setInfo("media prepared");
                } else {
                    Log.e("mikoto", "media prepared failed");
                    setInfo("media prepared failed");
                }
            }
        } else if (v.getId() == R.id.remain) {
            if (media != null) {
                Log.e("mikoto", "media MediaRemainRewards is " + media.getMediaRemainRewards());
                setInfo("media MediaRemainRewards is " + media.getMediaRemainRewards());
            }
        }
    }


    @Override
    protected void onDestroy() {
        Log.e("mikoto", "media activity destroy");
        if (media != null) {
            media.destroy();
        }
        super.onDestroy();
    }

    private void setInfo(final String msg) {
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
