package com.yumi.android.demo;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.formats.YumiNativeAdOptions;
import com.yumi.android.sdk.ads.formats.YumiNativeAdView;
import com.yumi.android.sdk.ads.publish.AdError;
import com.yumi.android.sdk.ads.publish.NativeContent;
import com.yumi.android.sdk.ads.publish.YumiNative;
import com.yumi.android.sdk.ads.publish.enumbean.ExpressAdSize;
import com.yumi.android.sdk.ads.publish.listener.IYumiNativeListener;
import com.yumimobi.ads.R;

import java.util.LinkedList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by Administrator on 2017/6/22.
 */
public class NativeActivity extends MActivity implements View.OnClickListener {

    private static final String TAG = "NativeActivity";
    private static final boolean onoff = true;

    private EditText nativeAdCount;
    private Button btnRequest;
    private Button btnShow;
    private Button btnIsExpired;
    private TextView tvAdcount;
    private int adcount;
    private FrameLayout nativeAdContinerView;
    private YumiNative nativeAd;
    private IYumiNativeListener nativeListener;
    private NativeContent content;
    private List<NativeContent> adContentList;

    @Override
    public void initView() {
        setContentView(R.layout.activity_native);
        setAcTitle("Native");
        btnRequest = (Button) findViewById(R.id.btn_request);
        btnShow = (Button) findViewById(R.id.btn_show);
        tvAdcount = (TextView) findViewById(R.id.tv_adcount);
        nativeAdContinerView = (FrameLayout) findViewById(R.id.ll_ad_continer);
        nativeAdCount = (EditText) findViewById(R.id.native_adCount);
        btnIsExpired = (Button) findViewById(R.id.btn_isExpired);
        adContentList = new LinkedList<NativeContent>();
    }

    @Override
    public void setListener() {
        btnRequest.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        btnIsExpired.setOnClickListener(this);

        nativeListener = new IYumiNativeListener() {
            @Override
            public void onLayerPrepared(List<NativeContent> adList) {
                Log.v(TAG, "native ad onLayerPrepared adCount:" + adList.size());
                adContentList.addAll(adList);
                adcount = adContentList.size();
                tvAdcount.setText(String.valueOf(adcount));
            }

            @Override
            public void onLayerFailed(AdError error) {
                Log.v(TAG, "native ad onLayerFailed and Error= " + error);
            }

            @Override
            public void onLayerClick() {
                Log.v(TAG, "native ad onLayerClick");
            }

            @Override
            public void onExpressAdRenderFail(NativeContent content, String errorMsg) {
                Log.v(TAG, "native ad onExpressAdRenderFail" + errorMsg);
            }

            @Override
            public void onExpressAdRenderSuccess(NativeContent content) {
                Log.v(TAG, "native ad onExpressAdRenderSuccess");
            }

            @Override
            public void onExpressAdClosed(NativeContent content) {
                Log.v(TAG, "native ad onExpressAdClosed");
            }
        };
    }

    @Override
    public void onActivityCreate() {
        /*
         * Second step :
         * Create YumiMedia instance by activity and your YumiID.
         */
        YumiNativeAdOptions nativeAdOptions = new YumiNativeAdOptions.Builder()
                .setIsDownloadImage(true)
                .setAdChoicesPosition(YumiNativeAdOptions.POSITION_TOP_RIGHT)
                .setAdAttributionPosition(YumiNativeAdOptions.POSITION_TOP_LEFT)
                .setAdAttributionText("Ad")
                .setAdAttributionTextColor(Color.argb(255, 255, 255, 255))
                .setAdAttributionBackgroundColor(Color.argb(90, 0, 0, 0))
                .setAdAttributionTextSize(10)
                .setHideAdAttribution(false)
                .setExpressAdSize(new ExpressAdSize(400, 300))
                .build();
        nativeAd = new YumiNative(NativeActivity.this, getStringConfig("native_slotID"), nativeAdOptions);
        //setMediaEventListener .  (Require)
        nativeAd.setNativeEventListener(nativeListener);
        //setChannelID .  (Recommend)
        nativeAd.setChannelID(channelStr);
        //setVersionName . (Recommend)
        nativeAd.setVersionName("1.0");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request:
                if (nativeAd != null) {
                    int adCount = nativeAdCount.getText().toString().equals("") ? 0 : Integer.valueOf(nativeAdCount.getText().toString());
                    nativeAd.requestYumiNative(adCount);
                } else {
                    Toast.makeText(NativeActivity.this, "nativeAd is null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_show:
                showNativeAd();
                break;
            case R.id.btn_isExpired:
                if (content == null) {
                    Toast.makeText(this, "please request NativeAd first", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "Native content.isExpired : " + content.isExpired(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }


    private void showNativeAd() {
        if (adContentList != null && adContentList.size() > 0)
        {
            if (content != null) {
                content.destroy();
            }

            content = adContentList.get(0);
            adContentList.remove(0);
            adcount = adContentList.size();
            tvAdcount.setText(String.valueOf(adcount));

            if (content.isExpired()) {
                Toast.makeText(this, "show NativeAd failed, because NativeAD content isExpired =" + content.isExpired(), Toast.LENGTH_SHORT).show();
                return;
            }
            nativeAdContinerView.removeAllViews();
            if (content.isExpressAdView()) {
                YumiNativeAdView adView = (YumiNativeAdView) getLayoutInflater()
                        .inflate(R.layout.activity_native_material, null);
                adView.removeAllViews();

                FrameLayout.LayoutParams videoViewLayout = new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
                videoViewLayout.gravity = Gravity.CENTER;

                adView.addView(content.getExpressAdView(), videoViewLayout);
                adView.setNativeAd(content);
                nativeAdContinerView.setClickable(true);
                nativeAdContinerView.addView(adView);
            } else {
                YumiNativeAdView adView = (YumiNativeAdView) getLayoutInflater()
                        .inflate(R.layout.activity_native_material, null);

                adView.setTitleView((TextView) adView.findViewById(R.id.headline));
                adView.setIconView((ImageView) adView.findViewById(R.id.app_icon));
                adView.setDescView((TextView) adView.findViewById(R.id.body));
                adView.setCoverImageView((ImageView) adView.findViewById(R.id.image));
                adView.setCallToActionView((Button) adView.findViewById(R.id.call_to_action));
                adView.setPriceView((TextView) adView.findViewById(R.id.price));
                adView.setStarRatingView((RatingBar) adView.findViewById(R.id.stars));
                adView.setMediaLayout((FrameLayout) adView.findViewById(R.id.media_content));

                if (content.getCoverImage() != null) {
                    Log.v(TAG, "content.getCoverImage().getDrawable()" + content.getCoverImage().getDrawable());
                    ((ImageView) adView.getCoverImageView()).setImageDrawable(content.getCoverImage().getDrawable());
                }
                if (content.getIcon() != null) {
                    Log.v(TAG, "content.getIcon().getDrawable()" + content.getIcon().getDrawable());
                    ((ImageView) adView.getIconView()).setImageDrawable(content.getIcon().getDrawable());
                }
                if (content.getTitle() != null) {
                    ((TextView) adView.getTitleView()).setText(content.getTitle());
                }
                if (content.getCallToAction() != null) {
                    ((Button) adView.getCallToActionView()).setText(content.getCallToAction());
                } else {
                    ((Button) adView.getCallToActionView()).setVisibility(View.INVISIBLE);
                }
                if (content.getPrice() != null) {
                    ((TextView) adView.getPriceView()).setText(content.getPrice());
                }

                if (content.getDesc() != null) {
                    ((TextView) adView.getDescView()).setText(content.getDesc());
                }

                if (content.getStarRating() == null) {
                    adView.getStarRatingView().setVisibility(View.INVISIBLE);
                } else {
                    ((RatingBar) adView.getStarRatingView())
                            .setRating(content.getStarRating().floatValue());
                    adView.getStarRatingView().setVisibility(View.VISIBLE);
                }

                adView.setNativeAd(content);
                nativeAdContinerView.setClickable(true);
                nativeAdContinerView.addView(adView);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (nativeAd != null) {
            nativeAd.destroy();
        }
    }

    public Dialog createInterstitial(View view) {
        Dialog dialog = buildFullDialog();
        dialog.setContentView(view);
        return dialog;
    }

    public Dialog buildFullDialog() {
        boolean isfullScreen = isFullScreen(this);
        Dialog dialog = null;
        if (isfullScreen) {
            dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        } else {
            dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        }
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private boolean isFullScreen(Activity activity) {
        return ((activity.getWindow().getAttributes().flags
                & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0);
    }

}