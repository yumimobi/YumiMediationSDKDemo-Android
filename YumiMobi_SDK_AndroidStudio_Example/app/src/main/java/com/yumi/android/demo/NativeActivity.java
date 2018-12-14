package com.yumi.android.demo;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.publish.NativeContent;
import com.yumi.android.sdk.ads.publish.YumiNative;
import com.yumi.android.sdk.ads.publish.enumbean.LayerErrorCode;
import com.yumi.android.sdk.ads.publish.listener.IYumiNativeListener;
import com.yumimobi.ads.demo.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/6/22.
 */
public class NativeActivity extends MActivity implements View.OnClickListener
{

    private static final String TAG = "NativeActivity";
    private static final boolean onoff = true;

    private Button btn_request;
    private Button btn_show_banner;
    private Button btn_show_interstitial;
    private TextView tv_adcount;
    private int adcount;
    private WebView imgView;
    private TextView titleView;
    private TextView descView;
    private WebView iconView;
    private TextView otherText;
    private TextView priceText;
    private TextView btText;
    private FrameLayout fl_banner_continer;
    private YumiNative nativeAd;
    private IYumiNativeListener nativeListener;
    private Bitmap image;
    private View view;
    private NativeContent content;

    @Override
    public void initView()
    {
        setContentView(R.layout.activity_native);
        setAcTitle("Native");
        btn_request = (Button) findViewById(R.id.btn_request);
        btn_show_banner = (Button) findViewById(R.id.btn_show_banner);
        btn_show_interstitial = (Button) findViewById(R.id.btn_show_interstitial);
        tv_adcount = (TextView) findViewById(R.id.tv_adcount);
        fl_banner_continer = (FrameLayout) findViewById(R.id.ll_ad_continer);
        view = NativeActivity.this.getLayoutInflater().inflate(R.layout.activity_native_material,null);
        imgView = (WebView)view .findViewById(R.id.image);
        titleView = (TextView)view. findViewById(R.id.title);
        iconView = (WebView) view.findViewById(R.id.icon);
        descView = (TextView)view. findViewById(R.id.desc);
        btText = (TextView)view. findViewById(R.id.bt_text);
        otherText = (TextView)view. findViewById(R.id.other);
        priceText = (TextView)view. findViewById(R.id.Price);
    }

    @Override
    public void setListener()
    {
        btn_request.setOnClickListener(this);
        btn_show_banner.setOnClickListener(this);
        btn_show_interstitial.setOnClickListener(this);
        nativeListener=new IYumiNativeListener()
        {
            @Override
            public void onLayerPrepared(int adCount)
            {
                Log.v(TAG, "onLayerPrepared adCount:"+adCount);
                adcount = adCount;
                tv_adcount.setText(String.valueOf(adcount));
                // 请求成功的回调，其中adCount是返回的广告条数
            }

            @Override
            public void onLayerFailed(LayerErrorCode error)
            {
                Log.v(TAG, "onLayerFailed and Error=" + error.getMsg());
                // 请求失败的回调，其中error是请求失败的错误提示
            }

            @Override
            public void onLayerClick() {
                Log.e("sss", "onLayerClick");
            }
        };
    }

    @Override
    public void onActivityCreate() {
        /*
         * Second step :
         * Create YumiMedia instance by activity and your YumiID.
         */
        nativeAd = new YumiNative(NativeActivity.this, getStringConfig("native_slotID"));
        //setMediaEventListener .  (Require)
        nativeAd.setNativeEventListener(nativeListener);
        //setChannelID .  (Recommend)
        nativeAd.setChannelID(channelStr);
        //setVersionName . (Recommend)
        nativeAd.setVersionName("1.0");
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_request:
                if (nativeAd != null)
                {
                    // 请求广告，成功或失败的结果会在回调接口中返回
                    nativeAd.requestYumiNative();
                }else
                {
                    Toast.makeText(NativeActivity.this,"nativeAd is null", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_show_banner:
                showBanner();
                break;
            case R.id.btn_show_interstitial:
//                showInterstitial();
                break;
            default:
                break;
        }

    }


    private void showBanner()
    {
        if (nativeAd != null && nativeAd.getADCount() > 0) // 通过剩余广告条数判断是否存在下一条广告
        {
          content = nativeAd.nextContent();// 提取下一条广告
            adcount = adcount - 1;
            tv_adcount.setText(String.valueOf(adcount));
//            int type = content.getContentType(); // 获取广告类型 1:物料形式 2:布局形式
            fl_banner_continer.removeAllViews();
            if(content.getImg_url()!=null){
               imgView.loadUrl(content.getImg_url());
            }
            if(content.getIcon_url()!=null){
              iconView.loadUrl(content.getIcon_url());
            }
            if(content.getTitle()!=null){
                titleView.setText(content.getTitle());
            }
            if(content.getButtonText()!=null){
                btText.setText(content.getButtonText());
            }
            if(content.getPrice()!=null){
                priceText.setText(content.getPrice());
            }
            if(content.getDesc()!=null){
                descView.setText(content.getDesc());
            }
            if(content.getOther()!=null){
                otherText.setText(content.getOther());
            }

            fl_banner_continer.addView(view);
            fl_banner_continer.setClickable(true);
            content.reportShow(fl_banner_continer,content);
            content.reportClick(fl_banner_continer,content);
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (nativeAd != null)
        {
            nativeAd.onDestroy();
        }
    }

    public Dialog createInterstitial(View view)
    {
        Dialog dialog = buildFullDialog();
        dialog.setContentView(view);
        return dialog;
    }

    public Dialog buildFullDialog()
    {
        boolean isfullScreen = isFullScreen(this);
        Dialog dialog = null;
        if (isfullScreen)
        {
            dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        } else
        {
            dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        }
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private boolean isFullScreen(Activity activity)
    {
        return ((activity.getWindow().getAttributes().flags
                & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0);
    }

}