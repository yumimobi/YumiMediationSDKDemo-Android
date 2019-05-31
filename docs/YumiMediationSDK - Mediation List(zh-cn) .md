# YumiMediationSDK Android - Mediation List

## 概述

使用此列表之前, 请确保已经根据《YumiMobi聚合广告SDK-横幅/插屏/视频广告接入说明》完成接入工作, 在需要添加支持的三方SDK时, 根据以下步骤完成接入。

-  添加三方adapter：在SDK的Adapter文件夹下, 找到对应的三方jar 添加到工程的libs 文件下。

-  添加三方.so文件(如果需要): 部分平台需要.so文件, 将Library文件夹内内容添加到工程的libs文件下。

-  引用三方lib工程(如果需要): 部分平台并不是以.jar 形式提供SDK,而是以lib工程形式。 请将lib工程导入到工作空间, 并作为library使用。

-  添加额外的权限: 将额外的权限注册到 AndroidManifest.xml中。

-  添加三方组件: 将三方需要的组件注册到 AndroidManifest.xml中。

-  使用时, 确定支持的广告形式。(Banner, 插屏, 视频)

-  混淆时, 请根据平台, 在混淆文件中加入混淆内容

-  请注意三方SDK最小安卓版本, 可能高于玉米聚合广告SDK所要求的最小安卓版本, 此时您的工程应选用两者中较高的版本作为工程的最小版本, 否则使用三方平台时, 可能造成未知的影响。 "-"意味着和玉米聚合所支持的安卓最小版本版本一致。

-  使用AndroidStudio开发时，适配器版本号需跟玉米SDK主包版本号保持一致。以添加Admob Adapter为例：您使用玉米 SDK 为 3.6.2 版本 "com.yumimobi.ads:mediation:3.6.2" 时，需要添加Admob Adapter的3.6.2 版本 ”com.yumimobi.ads.mediation:admob:3.6.2“ 


## 支持列表

### Adcolony

|                   |                                                        |
| ----------------- | ------------------------------------------------------ |
| Jar名称           | libs/yumi_adapter_adcolony.jar                         |
| 三方版本          | 3.3.7                                                  |
| 最小安卓版本      | Android 2.3.3  /  API 10                               |
| GooglePlayService | 需要                                                   |
| 支持广告形式      | 视频                                                   |
| .so/lib工程       | /armeabi  <br /> / arm64-v8a  <br /> /armeabi-v7a  <br /> /x86 <br /> /x86_64 |

**额外权限：**
```xml
<uses-permission android:name="android.permission.VIBRATE" />
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:adcolony:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity android:name="com.adcolony.sdk.AdColonyInterstitialActivity"
  android:configChanges="keyboardHidden|orientation|screenSize"
  android:hardwareAccelerated="true"/>
<activity android:name="com.adcolony.sdk.AdColonyAdViewActivity"
  android:configChanges="keyboardHidden|orientation|screenSize"/>
```

**混淆：**
```c
-keepclassmembers class * { 
    @android.webkit.JavascriptInterface <methods>; 
}
```


<br />


### Admob

|                   |                             |
| ----------------- | --------------------------- |
| Jar名称           | libs/yumi_adapter_admob.jar |
| 三方版本          | 17.1.3                          |
| GooglePlayService | 需要                        |
| 支持广告形式      | Banner, 插屏, 视频, 原生          |
| .so/lib工程       | --                          |

**额外权限：**
```xml
--
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:admob:3.6.2'
}
```


**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity  android:name="com.google.android.gms.ads.AdActivity" 
  android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"   
  android:theme="@android:style/Theme.Translucent" />
<meta-data  android:name="com.google.android.gms.version"
  android:value="@integer/google_play_services_version" />
```

**混淆：**
```c
-keep class com.google.android.gms.ads.identifier.AdvertisingldClient{
	public *;
  }
  -keep class com.google.android.gms.ads.identifier.AdvertisingldClient$Info{
	public *;
  }
  -keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
  }
  -keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
  }
  -keepnames @com.google.android.gms.common.annotation.KeepName class *
  -keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
  }
  -keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
  }
```


<br />


### AppLovin

|                   |                                |
| ----------------- | ------------------------------ |
| Jar名称           | libs/yumi_adapter_applovin.jar |
| 三方版本          | 8.0.2                          |
| GooglePlayService | 需要                           |
| 支持广告形式      | Banner, 插屏, 视频                     |
| .so/lib工程       | --                             |

**额外权限：**
```xml
--
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:applovin:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity android:name="com.applovin.adview.AppLovinInterstitialActivity" 
          android:configChanges="orientation|screenSize"/>
<activity android:name="com.applovin.adview.AppLovinConfirmationActivity" />
```


**混淆：**
```c
-dontwarn com.applovin.**
-keep public class com.applovin.**.*
```


<br />


### Baidu(百度)

|                   |                             |
| ----------------- | --------------------------- |
| Jar名称           | libs/yumi_adapter_baidu.jar |
| 三方版本          | 5.8.0                         |
| GooglePlayService | --                          |
| 支持广告形式      | Banner, 插屏, 视频, 原生                |
| .so/lib工程       | --                          |

**额外权限：**
```xml
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:baidu:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
	android:name="com.baidu.mobads.AppActivity"
	android:configChanges="keyboard|keyboardHidden|orientation" />
```

**混淆：**
```c
-keep class com.baidu.mobads.** {
  public protected *;
}
```


<br />


### Chartboost

|                   |                                  |
| ----------------- | -------------------------------- |
| Jar名称           | libs/yumi_adapter_chartboost.jar |
| 三方版本          | 7.3.1                            |
| GooglePlayService | 需要                             |
| 支持广告形式      | 插屏, 视频                       |
| .so/lib工程       | --                               |

**额外权限：**
```xml
-- 
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:chartboost:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
    android:name="com.chartboost.sdk.CBImpressionActivity"
    android:excludeFromRecents="true"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
```

**混淆：**
```c
-keep class com.chartboost.** { *; }
```

<br />


### Facebook

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar名称           | libs/yumi_adapter_facebook.jar                               |
| 三方版本          | 5.1.O                                                       |
| 最小安卓版本      | Android 3.0  /  API 11                                       |
| GooglePlayService | 需要                                                         |
| 支持广告形式      | Banner, 插屏 , 视频 (视频广告需要启用硬件加速功能，否则会导致黑屏), 原生 |
| .so/lib工程       | \res\xml                                                     |

**额外权限：**
```xml
--
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:facebook:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
    android:name="com.facebook.ads.AudienceNetworkActivity"
    android:configChanges="keyboardHidden|orientation|screenSize" />
```

**混淆：**
```c
-keep class com.facebook.** { *; }
```


<br />


### GdtMob(广点通)

|                   |                           |
| ----------------- | ------------------------- |
| Jar名称           | libs/yumi_adapter_gdt.jar |
| 三方版本          | 4.40.910                   |
| GooglePlayService | --                        |
| 支持广告形式      | Banner, 插屏, 视频, 原生              |
| .so/lib工程       | --                        |

**额外权限：**
```xml
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />  
<uses-permission android:name="android.permission.ACCESS_COARSE_UPDATES"/>
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:gdt:3.6.2'
}
```
<p><span style="color:red;">注意:
如果你使用AndroidStudio接入GdtMob(广点通)平台，请确保你App的AndroidManifest.xml中的package:"xxx.xxx.xxx"名称和build.gradle文件中的applicationId "xxx.xxx.xxx"保持一致。如下图所示：</span></p>
<img src="document\image11.png" alt="img11">

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<service
    android:name="com.qq.e.comm.DownloadService"
    android:exported="false"></service>
<activity
    android:name="com.qq.e.ads.ADActivity"
    android:configChanges="keyboard|keyboardHidden|orientation|screenSize">
</activity>
<activity
    android:name="com.qq.e.ads.PortraitADActivity"
    android:configChanges="keyboard|keyboardHidden|orientation|screenSize"
    android:screenOrientation="portrait" />
<activity
    android:name="com.qq.e.ads.LandscapeADActivity"
    android:configChanges="keyboard|keyboardHidden|orientation|screenSize"
    android:screenOrientation="landscape" />
```

**混淆：**
```c
-keep class com.qq.e.** {
public protected *;
}
-keep class com.tencent.gdt.**{
public protected *;
}
```


<br />


### Inmobi

|                   |                              |
| ----------------- | ---------------------------- |
| Jar名称           | libs/yumi_adapter_inmobi.jar |
| 三方版本          | 7.2.7                       |
| 最小安卓版本      | Android 2.3 / API 9;  Android 4.0 / API 14 (视频广告) |
| GooglePlayService | 需要                         |
| 支持广告形式      | Banner, 插屏, 视频           |
| .so/lib工程       | --                           |

**额外权限：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="com.google.android.gms.permission.ACTIVITY_RECOGNITION" />
<uses-permission android:name="android.permission.READ_CALENDAR" />
<uses-permission android:name="android.permission.WRITE_CALENDAR" />
<uses-permission android:name="android.permission.GET_TASKS" />
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:inmobi:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
	android:name="com.inmobi.rendering.InMobiAdActivity"
	android:configChanges="keyboardHidden|orientation|keyboard|smallestScreenSize|screenSize"
	android:hardwareAccelerated="true"
	android:theme="@android:style/Theme.Translucent.NoTitleBar" />
<receiver
	android:name="com.inmobi.commons.core.utilities.uid.ImIdShareBroadCastReceiver"
	android:enabled="true"
	android:exported="true" >
	<intent-filter>
	  <action android:name="com.inmobi.share.id" />
	</intent-filter>
</receiver>
<service
	android:name="com.inmobi.commons.internal.ActivityRecognitionManager"
	android:enabled="true" />
```

**混淆：**
```c
-keep class com.google.android.gms.common.api.GoogleApiClient { public *; }
-keep class com.google.android.gms.common.api.GoogleApiClient$* {public *;}
-keep class com.google.android.gms.location.LocationServices {public *;}
-keep class com.google.android.gms.location.FusedLocationProviderApi {public *;}
-keep class com.google.android.gms.location.ActivityRecognition {public *;}
-keep class com.google.android.gms.location.ActivityRecognitionApi {public *;}
-keep class com.google.android.gms.location.ActivityRecognitionResult {public *;}
-keep class com.google.android.gms.location.DetectedActivity {public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{public *;}
-keepattributes SourceFile,LineNumberTable,InnerClasses
-keep class com.inmobi.** { *; }
-dontwarn com.inmobi.**
-dontwarn com.google.android.gms**
```



<br />


### Mintegral

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar名称           | libs/yumi_adapter_mintegral.jar                               |
| 三方版本          | 8.13.0                                                        |
| GooglePlayService | --                                                           |
| 支持广告形式      | 视频                                                         |
| .so/lib工程       | \res\ anim <br /> \res\drawable <br /> \res\drawable-hdpi <br /> \res\layout <br /> \res\values |

**额外权限：**
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  //GooglePlay发布或中国以外市场渠道发布
  implementation 'com.yumimobi.ads.mediation:mintegral:3.6.2'
  //中国市场渠道发布
  implementation 'com.yumimobi.ads.mediation:mintegral-china:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
	android:name="com.mintegral.msdk.reward.player.MTGRewardVideoActivity"
	android:configChanges="orientation|keyboardHidden|screenSize"
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
<activity
	android:name="com.mintegral.msdk.activity.MTGCommonActivity"
	android:configChanges="keyboard|orientation"
	android:screenOrientation="portrait"
	android:exported="true"
	android:theme="@android:style/Theme.Translucent.NoTitleBar">
</activity>
<service android:name="com.mintegral.msdk.shell.MTGService" >
	<intent-filter>
		<action android:name="com.mintegral.msdk.download.action" />
	</intent-filter>
</service>
```

**混淆：**
```c
-keepattributes Signature   
-keepattributes *Annotation*   
-keep class com.mintegral.** {*; }  
-keep interface com.mintegral.** {*; }  
-keep class android.support.v4.** { *; }  
-dontwarn com.mintegral.**   
-keep class **.R$* { public static final int mintegral*; }
-keep class com.alphab.** {*; }
-keep interface com.alphab.** {*; }
```


<br />


### Oneway

|                   |                              |
| ----------------- | ---------------------------- |
| Jar名称           | libs/yumi_adapter_oneway.jar |
| 三方版本          | 1.3.5                        |
| GooglePlayService | --                           |
| 支持广告形式      | 视频                         |
| .so/lib工程       | --                           |

**额外权限：**
```xml
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:oneway:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity  android:name="mobi.oneway.sdk.AdShowActivity"  
	android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen" 
	android:hardwareAccelerated="true" 
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
```

**混淆：**
```c
--
```


<br />


### Unity

|                   |                             |
| ----------------- | --------------------------- |
| Jar名称           | libs/yumi_adapter_unity.jar |
| 三方版本          | 2.3.0                       |
| GooglePlayService | 需要                        |
| 支持广告形式      | 插屏,视频                   |
| .so/lib工程       | --                          |

**额外权限：**
```xml
--
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  //GooglePlay发布或中国以外市场渠道发布
  implementation 'com.yumimobi.ads.mediation:unity:3.6.2'
  //中国市场渠道发布
  implementation 'com.yumimobi.ads.mediation:unity-china:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity android:name="com.unity3d.ads.adunit.AdUnitActivity"
	android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"    
	android:hardwareAccelerated="true" android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
<activity android:name="com.unity3d.ads.adunit.AdUnitSoftwareActivity"    
	android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"
	android:hardwareAccelerated="false"   
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
```

**混淆：**
```c
# Keep filenames and line numbers for stack traces
-keepattributes SourceFile,LineNumberTable
# Keep JavascriptInterface for WebView bridge
-keepattributes JavascriptInterface
# Sometimes keepattributes is not enough to keep annotations
-keep class android.webkit.JavascriptInterface {
   *;
}
# Keep all classes in Unity Ads package
-keep class com.unity3d.ads.** {
   *;
}
```


<br />


### Vungle

|                   |                              |
| ----------------- | ---------------------------- |
| Jar名称           | libs/yumi_adapter_vungle.jar |
| 三方版本          | 6.3.17                       |
| GooglePlayService | 需要                         |
| 支持广告形式      | 插屏, 视频                   |
| .so/lib工程       | converter-gson-2.2.0.jar  <br />  fetch-1.1.5.jar  <br />  gson-2.7.jar  <br />  logging-interceptor-3.7.0.jar  <br />  okhttp-3.7.0.jar  <br />  okio-1.12.0.jar  <br />  retrofit-2.2.0.jar  <br />  VNG-moat-mobile-app-kit-2.2.0.jar |

**额外权限：**
```xml
<!--Required Permissions-->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="18" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!--Optional Permissions-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:vungle:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
	android:name="com.vungle.warren.ui.VungleActivity"
	android:configChanges="keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize"
	android:launchMode="singleTop"
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
<activity
	android:name="com.vungle.warren.ui.VungleFlexViewActivity"
	android:configChanges="keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize"
	android:hardwareAccelerated="true"
	android:launchMode="singleTop"
	android:theme="@android:style/Theme.Translucent.NoTitleBar" />
```

**混淆：**
```c
# Vungle
-keep class com.vungle.warren.** { *; }
-dontwarn com.vungle.warren.error.VungleError$ErrorCode
# Moat SDK
-keep class com.moat.** { *; }
-dontwarn com.moat.**
# Okio
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# Retrofit
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
# Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# Google Android Advertising ID
-keep class com.google.android.gms.internal.** { *; }
-dontwarn com.google.android.gms.ads.identifier.**
```


<br />



### ZPLAYAds

|                   |                                   |
| ----------------- | --------------------------------- |
| Jar名称           | libs/yumi_adapter_playableads.jar |
| 三方版本          | 2.4.0                             |
| GooglePlayService | --                                |
| 支持广告形式      | 插屏, 视频                        |
| .so/lib工程       | --                                |

**额外权限：**
```xml
--
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:playableads:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
    android:name="com.playableads.presenter.PlayableADActivity"
    android:configChanges="orientation|screenSize|keyboardHidden"
    android:hardwareAccelerated="true"
    android:screenOrientation="portrait"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />

<activity
    android:name="com.playableads.presenter.NativeAdLandingPageActivity"
    android:configChanges="orientation|screenSize|keyboardHidden"
    android:hardwareAccelerated="true"
    android:screenOrientation="portrait"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />

<receiver android:name="com.playableads.PlayableReceiver">
    <intent-filter>
        <action android:name="android.intent.action.DOWNLOAD_COMPLETE" />
    </intent-filter>
</receiver>
```

**混淆：**
```c
-keep class com.playableads.PlayPreloadingListener {*;}
-keep class com.playableads.PlayLoadingListener {*;}
-keep class * implements com.playableads.PlayPreloadingListener {*;}
-keep class * implements com.playableads.PlayLoadingListener {*;}
-keep class com.playableads.PlayableReceiver {*;}
-keep class com.playableads.constants.StatusCode {*;}
-keep class com.playableads.MultiPlayLoadingListener {*;}
-keep class com.playableads.MultiPlayPreloadingListener {*;}
-keep class * implements com.playableads.MultiPlayLoadingListener {*;}
-keep class * implements com.playableads.MultiPlayPreloadingListener {*;}
-keep class com.playableads.PlayableAds {
    public void onDestroy();
    public static com.playableads.PlayableAds getInstance();
    public void requestPlayableAds(com.playableads.PlayPreloadingListener, java.lang.String);
    public void requestPlayableAds(java.lang.String, com.playableads.PlayPreloadingListener);
    public synchronized static com.playableads.PlayableAds init(android.content.Context, java.lang.String);
    public void presentPlayableAD(java.lang.String, com.playableads.PlayLoadingListener);
    public void presentPlayableAd(com.playableads.PlayLoadingListener);
    public boolean canPresentAd(java.lang.String);
    public void setMultiLoadingListener(com.playableads.MultiPlayLoadingListener);
    public void setMultiPreloadingListener(com.playableads.MultiPlayPreloadingListener);
    public void setCacheCountPerUnitId(int);
    public void setAutoLoadAd(boolean);
}
```


<br />


### Ksyun(金山云)

|                   |                              |
| ----------------- | ---------------------------- |
| Jar名称           | libs/yumi_adapter_ksyun.jar |
| 三方版本          | 4.0.3                        |
| GooglePlayService | --                         |
| 支持广告形式      | 视频                   |
| .so/lib工程       |  \res\xml\file_paths.xml|

**额外权限：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:ksyun:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
	android:name="com.ksc.ad.sdk.ui.AdProxyActivity"
	android:configChanges="keyboardHidden|orientation|screenSize"
	android:hardwareAccelerated="true"
	android:theme="@android:style/Theme.Black.NoTitleBar.Fullscreen" />
<activity
	android:name="com.ksc.ad.sdk.ui.AdPermissionProxyActivity"
	android:configChanges="keyboardHidden|orientation|screenSize"
	android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
<service android:name="com.ksc.ad.sdk.service.AdProxyService" />
<provider
	android:name="com.ksc.ad.sdk.util.KsyunFileProvider"
	android:authorities="${applicationId}.fileprovider"
	android:exported="false"
	android:grantUriPermissions="true">
	<meta-data
		android:name="android.support.FILE_PROVIDER_PATHS"
		android:resource="@xml/file_paths" />
</provider>
```

**混淆：**
```c
-keep class com.ksc.ad.sdk.**{ *;}
-dontwarn com.ksc.ad.sdk.**
```


<br />


### IronSource

|                   |                              |
| ----------------- | ---------------------------- |
| Jar名称           | libs/yumi_adapter_ironsource.jar |
| 三方版本          | 6.7.10                        |
| GooglePlayService | 需要                         |
| 支持广告形式      | 插屏,视频                   |
| .so/lib工程       | --|

**额外权限：**
```xml
--
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:ironsource:3.6.2'
}
```

**Eclipse开发**

AndroidManifest.xml注册组件：
```xml
<activity
	android:name="com.ironsource.sdk.controller.ControllerActivity"
	android:configChanges="orientation|screenSize"
	android:hardwareAccelerated="true" />
<activity
	android:name="com.ironsource.sdk.controller.InterstitialActivity"
	android:configChanges="orientation|screenSize"
	android:hardwareAccelerated="true"
	android:theme="@android:style/Theme.Translucent" />
<activity
	android:name="com.ironsource.sdk.controller.OpenUrlActivity"
	android:configChanges="orientation|screenSize"
	android:hardwareAccelerated="true"
	android:theme="@android:style/Theme.Translucent" />
```

**混淆：**
```c
-keepclassmembers class com.ironsource.sdk.controller.IronSourceWebView$JSInterface {
    public *;
}
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep class com.ironsource.adapters.** { *;
}
-dontwarn com.moat.**
-keep class com.moat.** { public protected private *; }
```
### Iqzone

|                   |                              |
| ----------------- | ---------------------------- |
| Jar名称           | libs/yumi_adapter_iqzone.jar |
| 三方版本          | 2.3.2111                        |
| GooglePlayService | 需要                         |
| 支持广告形式      | Banner,插屏,视频                   |
| .so/lib工程       | --|

**额外权限：**
```xml
--
```

**AndroidStudio开发**

build.gradle添加：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:iqzone:3.6.2'
}
```

**混淆：**
```c
-dontwarn com.applovin.**
-keep class com.applovin.* { *; }
-keep public class com.google.android.gms.**
-keep public class com.google.android.gms.ads.*
-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}
-keep class com.google.android.gms.ads.identifier.** { *; }
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{ public *; }
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{ public *; }
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}
-keep class com.appnext.* { *; }
-keep class com.hyprmx.** {*;}
-keep class okhttp3.hyprmx.* { *; }
-keep class com.immersion.** { *; }
-keep class com.inmobi.** { *; }
-keep class com.integralads.avid.library.* {*;}
-keep class com.jirbo.** { *; }
-keep class com.moat.** {*;}
-keep class com.mologiq.** { *; }
-keep class com.squareup.picasso.** {*;}
-keep class com.tapjoy.** { *; }
-keep class com.vungle.** { *; }
-keep class javax.inject.*
-keep class com.yume.** { *; }
-keep class rx.schedulers.Schedulers { public static <methods>; }
-keep class rx.schedulers.ImmediateScheduler { public <methods>; }
-keep class rx.schedulers.TestScheduler { public <methods>; }
-keep class rx.schedulers.Schedulers { public static ** test(); }
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-keep interface okhttp3.hyprmx.* { *; }
-dontwarn android.app.Activity
-dontwarn com.appnext.**
-dontwarn com.google.android.gms.**
-dontwarn com.google.android.gms.ads.identifier.** -dontwarn com.inmobi.**
-dontwarn com.moat.**
-dontwarn okhttp3.hyprmx.**
-dontwarn okio.hyprmx.**
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.tapjoy.**
-dontwarn com.vungle.**
-dontnote com.vungle.**
-dontwarn de.greenrobot.event.util.**
-dontwarn org.w3c.dom.**
-dontwarn rx.internal.util.unsafe.**
-keep class com.playableads.PlayPreloadingListener {*;}
-keep class com.playableads.PlayLoadingListener {*;}
-keep class implements com.playableads.PlayPreloadingListener { *; } 
-keep class implements com.playableads.PlayLoadingListener { *; }
-keep class com.playableads.PlayableReceiver { *; }
-keep class com.playableads.constants.StatusCode { *; }
-keep class com.playableads.MultiPlayLoadingListener { *; }
-keep class com.playableads.MultiPlayPreloadingListener { *; }
-keep class implements com.playableads.MultiPlayLoadingListener { *; } 
-keep class implements com.playableads.MultiPlayPreloadingListener { *; }
-keep class com.playableads.PlayableAds {
    public static com.playableads.PlayableAds getInstance();
    public synchronized static com.playableads.PlayableAds init(android.content.Context, java.lang.String);
    public <methods>;
}
-keep public class com.iqzone.activities.InterstitialActivity { public *; }
-keep public class com.iqzone.imd.MraidInterface { public *; }
-keep public class com.iqzone.imd.JSWebViewInterface { public *; }
-keepclassmembers class com.iqzone.imd.JSWebViewInterface { public *; }
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream); java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepattributes *Annotation*
-keepattributes Annotation
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-keepattributes JavascriptInterface
-keepattributes Signature
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}
-keepnames class * implements android.os.Parcelable {
    ublic static final ** CREATOR;
}
-keepclassmembers class com.ironsource.sdk.controller.IronSourceWebView$JSInterface { public *; }
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep public class com.google.android.gms.ads.** { public *; }
-keep class com.ironsource.adapters.* { *; }
-dontwarn com.moat.**
-keep class com.moat.* { public protected private *; }
# MoPub Proguard Config
# NOTE: You should also include the Android Proguard config found with the build tools: # $ANDROID_HOME/tools/proguard/proguard-android.txt
# Keep public classes and methods.
-keepclassmembers class com.mopub.* { public *; }
-keep public class com.mopub.**
-keep public class android.webkit.JavascriptInterface {}
-keep public class com.mopub.mobileads.MoPubRewardedVideoManager { public *; }
-keepclassmembers class com.mopub.mobileads.MoPubRewardedVideoManager { public *;}
# Explicitly keep any custom event classes in any package.
-keep class * extends com.mopub.mobileads.CustomEventBanner {}
-keep class * extends com.mopub.mobileads.CustomEventInterstitial {}
-keep class * extends com.mopub.nativeads.CustomEventNative {}
-keep class * extends com.mopub.nativeads.CustomEventRewardedAd {}
# Keep methods that are accessed via reflection
-keepclassmembers class * {
    @com.mopub.common.util.ReflectionTarget *;
}

# Viewability support
-keepclassmembers class com.integralads.avid.library.mopub.* { public *; }
-keep public class com.integralads.avid.library.mopub.**
-keepclassmembers class com.moat.analytics.mobile.mpub.* { public *; }
-keep public class com.moat.analytics.mobile.mpub.**

# Support for Android Advertiser ID.
-keep class com.google.android.gms.common.GooglePlayServicesUtil {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {*;}
# Support for Google Play Services
# http://developer.android.com/google/play-services/setup.html
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}
-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}
-keepnames @com.google.android.gms.common.annotation.KeepName class * -keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
-dontwarn com.smaato.soma.SomaUnityPlugin* -dontwarn com.millennialmedia**
-dontwarn com.facebook.**
-dontwarn org.codehause.**
```