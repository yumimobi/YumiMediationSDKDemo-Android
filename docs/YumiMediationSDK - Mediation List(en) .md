# YumiMediationSDK Android - Mediation List

## Introduction

Before you use mediation , make sure you has integrated YumiMobiSDK by 《YumiMobi Ads SDK- Banner(Interstitial/Reward Videw) Integrate Guide》. Then integrate mediation platform SDK by follow these step.

- Make sure the mediation advertising form is supported.

- Add mediation adapter jar : In adapter folder , find mediation adapter jar and add to your projected .

- Add .so files (If needed) : In adapter folder , if the platform adapter is folder, you need add all the file into your project.

- Add lib projected (If needed) : Some platform need add the adapter jar and need library project. Import library project into your workspace and use it as library.

- Declare mediation permission : Declare mediation necessary permission in your AndroidManifest.xml.

- Declare mediation component : Declare mediation require component in your AndroidManifest.xml.

- Add the mediation proguard directive in your ProGuard configuration file.

- The minSdkVersion which the provider sdk required maybe above the YumiMobi SDK , please use the higher as your project minSdkVersion to avoid the unexpected exception. "-" means the minSdkVersion is same to YumiMobi SDK required.

- When developing with AndroidStudio, the adapter version number should be consistent with the main package version number of the corn SDK. To add such, Adapter, for example, you use the corn SDK for version 3.3.6 "com. Yumimobi. Ads: mediation: 3.3.6. +", you need to add such Adapter 3.3.6 version "com. Yumimobi. Ads. Mediation: such: 3.3.6. +"

## Support List

### Adcolony

|                   |                                                        |
| ----------------- | ------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_adcolony.jar                         |
| Provider Ver      | 3.2.1                                                  |
| minSdkVersion     | Android 2.3.3 / API 10                                 |
| GooglePlayService | Require                                                |
| Ad Form           | Reward Video                                           |
| .so/lib project   | /armeabi  <br /> / arm64-v8a  <br /> /armeabi-v7a  <br /> /x86 <br /> /x86_64 |

**Permission：**
```xml
<uses-permission android:name="android.permission.VIBRATE" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:adcolony:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity android:name="com.adcolony.sdk.AdColonyInterstitialActivity"
  android:configChanges="keyboardHidden|orientation|screenSize"
  android:hardwareAccelerated="true"/>
<activity android:name="com.adcolony.sdk.AdColonyAdViewActivity"
  android:configChanges="keyboardHidden|orientation|screenSize"/>
```

**ProGuard：**
```c
-keepclassmembers class * { 
    @android.webkit.JavascriptInterface <methods>; 
}
```


<br />


### Admob

|                   |                             |
| ----------------- | --------------------------- |
| Jar Name          | libs/yumi_adapter_admob.jar |
| Provider Ver      | 10.0.1                          |
| GooglePlayService | Require                     |
| Ad Form           | Banner, Interstitial, Reward Video|
| .so/lib project   | --                          |

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  //play-services-ads is used less than version 15.0.0
  compile 'com.yumimobi.ads.mediation:admob:*.*.*.+'
}
```

>Note: if play-services-ads version used in the project is >= 15<br />
>please use : yumi_adapter_admob_v*.*.*(For GooglePlayService version 15 and above).aar [download link](https://github.com/yumimobi/YumiMediationSDKDemo-Android/blob/master/docs/YumiMediationSDK%20for%20Android%20Download%20Page.md)

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity  android:name="com.google.android.gms.ads.AdActivity" 
  android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"   
  android:theme="@android:style/Theme.Translucent" />
<meta-data  android:name="com.google.android.gms.version"
  android:value="@integer/google_play_services_version" />
```

**ProGuard：**
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
| Jar Name          | libs/yumi_adapter_applovin.jar |
| Provider Ver      | 8.0.2                         |
| GooglePlayService | Require                        |
| Ad Form           | Interstitial, Reward Video     |
| .so/lib project   | --                             |

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:applovin:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity android:name="com.applovin.adview.AppLovinInterstitialActivity" 
          android:configChanges="orientation|screenSize"/>
<activity android:name="com.applovin.adview.AppLovinConfirmationActivity" />
```

>**banner ads，Add the following line into your AndroidManifest.xml.This needs to go inside the application tag：**
```
<meta-data android:name="applovin.sdk.key"
       android:value="YOUR_SDK_KEY"  />
```

**ProGuard：**
```c
-dontwarn com.applovin.**
-keep public class com.applovin.**.*
```


<br />


### Baidu(百度)

|                   |                             |
| ----------------- | --------------------------- |
| Jar Name          | libs/yumi_adapter_baidu.jar |
| Provider Ver      | 5.7                         |
| GooglePlayService | --                          |
| Ad Form           | Banner, Interstitial        |
| .so/lib project   | --                          |

**Permission：**
```xml
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:baidu:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity
	android:name="com.baidu.mobads.AppActivity"
	android:configChanges="keyboard|keyboardHidden|orientation" />
```

**ProGuard：**
```c
-keep class com.baidu.mobads.** {
  public protected *;
}
```


<br />



### Chartboost

|                   |                                  |
| ----------------- | -------------------------------- |
| Jar Name          | libs/yumi_adapter_chartboost.jar |
| Provider Ver      | 6.6.1                            |
| GooglePlayService | Require                          |
| Ad Form           | Interstitial, Reward Video       |
| .so/lib project   | --                               |

**Permission：**
```xml
-- 
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:chartboost:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity
    android:name="com.chartboost.sdk.CBImpressionActivity"
    android:excludeFromRecents="true"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
```

**ProGuard：**
```c
-keep class com.chartboost.** { *; }
```

<br />


### Facebook

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_facebook.jar                               |
| Provider Ver      | 4.99.3                                                       |
| minSdkVersion     | Android 3.0 / API 11                                         |
| GooglePlayService | Require                                                      |
| Ad Form           | Banner, Interstitial , Reward Video (Videos ads in Audience Network requires the hardware accelerated rendering to be enabled, otherwise you might experience a black screen in the video views) |
| .so/lib project   | \res\xml                                                     |

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:facebook:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity
    android:name="com.facebook.ads.AudienceNetworkActivity"
    android:configChanges="keyboardHidden|orientation|screenSize" />
```

**ProGuard：**
```c
-keep class com.facebook.** { *; }
```


<br />


### GdtMob(广点通)

|                   |                           |
| ----------------- | ------------------------- |
| Jar Name          | libs/yumi_adapter_gdt.jar |
| Provider Ver      | 4.9.544                   |
| GooglePlayService | --                        |
| Ad Form           | Banner, Interstitial      |
| .so/lib project   | --                        |

**Permission：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />  
<uses-permission android:name="android.permission.ACCESS_COARSE_UPDATES"/>
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:gdt:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<service  android:name="com.qq.e.comm.DownloadService" android:exported="false" >
</service>
<activity
	android:name="com.qq.e.ads.ADActivity"
	android:configChanges="keyboard|keyboardHidden|orientation|screenSize" >
</activity>
```

**ProGuard：**
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

|                   |                                    |
| ----------------- | ---------------------------------- |
| Jar Name          | libs/yumi_adapter_inmobi.jar       |
| Provider Ver      | 6.0.4                              |
| minSdkVersion     | Android 2.3 / API 9;  Android 4.0 / API 14 (For Reward Video)|
| GooglePlayService | Require                            |
| Ad Form           | Banner, Interstitial, Reward Video |
| .so/lib project   | --                                 |

**Permission：**
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

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:inmobi:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
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

**ProGuard：**
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
| Jar Name          | libs/yumi_adapter_mintegral.jar                               |
| Provider Ver      | 8.13.0                                                        |
| GooglePlayService | --                                                           |
| Ad Form           | Reward Video                                                 |
| .so/lib project   | \res\ anim <br /> \res\drawable <br /> \res\drawable-hdpi <br /> \res\layout <br /> \res\values  |

**Permission：**
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  //GooglePlay release or release outside China market channel
  compile 'com.yumimobi.ads.mediation:mintegral:*.*.*.+'
  //China market channel release
  compile 'com.yumimobi.ads.mediation:mintegral-china:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
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

**ProGuard：**
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
| Jar Name          | libs/yumi_adapter_oneway.jar |
| Provider Ver      | 1.3.5                        |
| GooglePlayService | --                           |
| Ad Form           | Reward Video                 |
| .so/lib project   | --                           |

**Permission：**
```xml
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:oneway:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity  android:name="mobi.oneway.sdk.AdShowActivity"  
	android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen" 
	android:hardwareAccelerated="true" 
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
```

**ProGuard：**
```c
--
```


<br />



### Unity

|                   |                             |
| ----------------- | --------------------------- |
| Jar Name          | libs/yumi_adapter_unity.jar |
| Provider Ver      | 2.3.0                       |
| GooglePlayService | Require                     |
| Ad Form           | Interstitial,Reward Video   |
| .so/lib project   | --                          |

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  //GooglePlay release or release outside China market channel
  compile 'com.yumimobi.ads.mediation:unity:*.*.*.+'
  //China market channel release
  compile 'com.yumimobi.ads.mediation:unity-china:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity android:name="com.unity3d.ads.adunit.AdUnitActivity"
	android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"    
	android:hardwareAccelerated="true" android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
<activity android:name="com.unity3d.ads.adunit.AdUnitSoftwareActivity"    
	android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"
	android:hardwareAccelerated="false"   
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
```

**ProGuard：**
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
| Jar Name          | libs/yumi_adapter_vungle.jar |
| Provider Ver      | 6.3.17                       |
| GooglePlayService | Require                      |
| Ad Form           | Interstitial, Reward Video   |
| .so/lib project   | converter-gson-2.2.0.jar  <br />  fetch-1.1.5.jar  <br />  gson-2.7.jar  <br />  logging-interceptor-3.7.0.jar  <br />  okhttp-3.7.0.jar  <br />  okio-1.12.0.jar  <br />  retrofit-2.2.0.jar  <br />  VNG-moat-mobile-app-kit-2.2.0.jar |

**Permission：**
```xml
<!--Required Permissions-->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="18" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!--Optional Permissions-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:vungle:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
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

**ProGuard：**
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
| Jar Name          | libs/yumi_adapter_playableads.jar |
| Provider Ver      | 2.0.7                             |
| GooglePlayService | --                                |
| Ad Form           | Interstitial, Reward Video        |
| .so/lib project   | --                                |

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:playableads:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity
	android:name="com.playableads.activity.PlayableADActivity"
	android:configChanges="orientation|screenSize|keyboardHidden"
	android:hardwareAccelerated="true"
	android:screenOrientation="portrait"
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />

<receiver android:name="com.playableads.PlayableReceiver" >
	<intent-filter>
		<action android:name="android.intent.action.DOWNLOAD_COMPLETE" />
	</intent-filter>
</receiver>
```

**ProGuard：**
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
| Jar Name           | libs/yumi_adapter_ksyun.jar |
| Provider Ver          | 4.0.3                        |
| GooglePlayService | --                         |
| Ad Form       | Reward Video                   |
| .so/lib project       |  \res\xml\file_paths.xml|

**Permission：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:ksyun:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
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

**ProGuard：**
```c
-keep class com.ksc.ad.sdk.**{ *;}
-dontwarn com.ksc.ad.sdk.**
```


<br />


### IronSource

|                   |                              |
| ----------------- | ---------------------------- |
| Jar Name           | libs/yumi_adapter_ironsource.jar |
| Provider Ver          | 6.7.10                        |
| GooglePlayService | Require                         |
| Ad Form       | Interstitial, Reward Video                   |
| .so/lib project       | --|

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  compile 'com.yumimobi.ads.mediation:ironsource:*.*.*.+'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
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

**ProGuard：**
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