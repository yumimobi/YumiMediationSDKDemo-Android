package com.yumi.android.demo;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yumi.android.MActivity;
import com.yumi.android.sdk.ads.publish.YumiBanner;
import com.yumi.android.sdk.ads.publish.enumbean.AdSize;
import com.yumi.android.sdk.ads.publish.enumbean.LayerErrorCode;
import com.yumi.android.sdk.ads.publish.listener.IYumiBannerListener;
import com.yumimobi.ads.demo.R;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class BannerActivity_MT extends MActivity implements OnClickListener {

	private FrameLayout bannerContainer;
	private YumiBanner banner;
	private IYumiBannerListener bannerListener;
	private TextView text;
	private Button request;
	
	@Override
	public void initView() {
		setContentView(R.layout.activity_banner_m);
		setAcTitle("Banner_Code");
		text = (TextView) findViewById(R.id.textView2);
		request = (Button) findViewById(R.id.request);
		request.setOnClickListener(this);
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
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.request:
			if (banner!=null)
			{
				banner.requestYumiBanner();
			}
			break;

		default:
			break;
		}
	}
	
	@Override
	public void setListener() {
		/*
		 * Second step:
		 * create IYumiBannerListener to get the banner request statue.
		 */
		bannerListener = new IYumiBannerListener() {

			@Override
			public void onBannerPreparedFailed(LayerErrorCode errorCode) {
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
		/*
		 * Thrid step :
		 * create YumiBanner instance by activity and your YumiID.
		 */
		banner = new YumiBanner(BannerActivity_MT.this, getStringConfig("banner_slotID"), false);
		//setBannerContainer
		banner.setBannerContainer(bannerContainer, AdSize.BANNER_SIZE_AUTO,isMatchWindowWidth);
		//setChannelID . (Recommend)
		banner.setChannelID(channelStr);
		banner.setDefaultChannelAndVersion(getApplicationContext());
		//setVersionName . (Recommend)
		banner.setVersionName(versionStr);
		//setBannerEventListener. (Recommend)
		banner.setBannerEventListener(bannerListener);
		//requestYumiBanner. (Require)
		banner.requestYumiBanner();
		
		Log.e("mikoto", "Local ip address is " + getLocalIpAddress());
		
	}

	
	@Override
	protected void onDestroy() {
		if (banner != null) {
			banner.onDestroy();
		}
		super.onDestroy();
	}
	
	private void setInfo(final String msg){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if (text != null) {
					text.append(msg + "\n");
				}
				
			}
		});
	}
	
	public String getLocalIpAddress() {
	     try {
	          for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
	en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
	enumIpAddr.hasMoreElements();) {
	InetAddress inetAddress = enumIpAddr.nextElement();
	if (!inetAddress.isLoopbackAddress()) {
	   return inetAddress.getHostAddress();
	}
	}
	   }
	} catch (SocketException ex) {
	   Log.e("mikoto", ex.toString());
	}
	return null;
	}

}
