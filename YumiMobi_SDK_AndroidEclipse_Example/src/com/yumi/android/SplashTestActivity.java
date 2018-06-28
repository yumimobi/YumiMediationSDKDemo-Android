package com.yumi.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.yumi.android.sdk.ads.publish.YumiSettings;
import com.yumi.android.sdk.ads.self.ads.s.SplashAD;
import com.yumi.android.sdk.ads.self.ads.s.SplashADListener;

public class SplashTestActivity extends Activity
{

	private SplashAD splashAD;
	private String key;
	private FrameLayout container;
	private int adwidth;
	private int adheight;
	int logodip = 40;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout parent = new LinearLayout(this);
		parent.setOrientation(LinearLayout.VERTICAL);
		setContentView(parent);
		YumiSettings.runInCheckPermission(true);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		{
			container = new FrameLayout(this);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
			params.weight = 1;
			parent.addView(container, params);
		}
		int logopx = dip2px(this, logodip);
		{
			TextView tv_flag = new TextView(this);
			tv_flag.setTextSize(26);
			tv_flag.setText("应用logo 高度"+logodip+"dip");
			tv_flag.setGravity(Gravity.CENTER);
			tv_flag.setTextColor(0xffffffff);
			tv_flag.setBackgroundColor(0xff2180a3);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, logopx);
			parent.addView(tv_flag, params);
		}

		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		adwidth = displayMetrics.widthPixels;
		int s_height = displayMetrics.heightPixels;
		adheight = s_height-logopx;
		key = sp.getString("splash_slotID", "");
		if (key != null && !"".equals(key))
		{
			startAD();
		}else{
			Toast.makeText(SplashTestActivity.this, "没有填入应用ID", Toast.LENGTH_SHORT).show();
			finish();
		}

	}

	private void startAD()
	{
		splashAD = new SplashAD(this, key, container, adwidth, adheight, new SplashADListener()
		{

			@Override
			public void onSplashShow()
			{
				Toast.makeText(SplashTestActivity.this, "开屏展示", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSplashFailed(String msg)
			{
				Toast.makeText(SplashTestActivity.this, "开屏失败:"+msg, Toast.LENGTH_SHORT).show();
				finish();
			}

			@Override
			public void onSplashClose()
			{
				Toast.makeText(SplashTestActivity.this, "开屏关闭", Toast.LENGTH_SHORT).show();
				finish();
			}

			@Override
			public void onSplashClick()
			{
				Toast.makeText(SplashTestActivity.this, "开屏点击", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (splashAD!=null)
		{
			splashAD.destory();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
	}
	
	public static int dip2px(Context context, int dp){
		float scale = context.getResources().getDisplayMetrics().density;
		return ((int)(dp * scale +0.5f));
	}

}
