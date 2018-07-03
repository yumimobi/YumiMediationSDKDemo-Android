# YumiMediationSDK Android - Mediation List

## Introduction

Before you use mediation , make sure you has integrated YumiMobiSDK by 《YumiMobi Ads SDK- Banner(Interstitial/Reward Videw) Integrate Guide》. Then integrate mediation platform SDK by follow these step.

- Make sure the mediation advertising form is supported.

- Add mediation adapter jar : In adapter folder , find mediation adapter jar and add to your projected .

- Add .so files (If needed) : In adapter folder , if the platform adapter is folder, you need add all the file into your project.

- Add lib projected (If needed) : Some platform need add the adapter jar and need library project. Import library project into your workspace and use it as library.

- Declare mediation permission : Declare mediation necessary permission in your \Assets\plugins\Android\AndroidManifest.xml.

- Declare mediation component : Declare mediation require component in your \Assets\plugins\Android\AndroidManifest.xml.

- Add the mediation proguard directive in your ProGuard configuration file.

- The minSdkVersion which the provider sdk required maybe above the YumiMobi SDK , please use the higher as your project minSdkVersion to avoid the unexpected exception. "-" means the minSdkVersion is same to YumiMobi SDK required.


## Support List


### Adcolony

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_adcolony.jar                               |
| Provider Ver      | 3.0.7                                                        |
| minSdkVersion     | Android 2.3.3  /  API 10                                     |
| GooglePlayService | Require                                                      |
| Ad Form           | Reward Video                                                 |
| .so/lib project   | /armeabi  <br> / arm64-v8a  <br> /armeabi-v7a  <br> /x86 <br> /x86_64 |

**Permission：**
```xml
<uses-permission android:name="android.permission.VIBRATE" />
```

**AndroidManifest.xml Component：**
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


<br>


### Admob

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_admob.jar                                  |
| Provider Ver      | --                                                           |
| GooglePlayService | Require                                                      |
| Ad Form           | Banner, Interstitial                                         |
| .so/lib project   | play-services-ads-10.0.1.aar <br> play-services-ads-lite-10.0.1.aar<br>play-services-base-10.0.1.aar<br>play-services-basement-10.0.1.aar<br>play-services-clearcut-10.0.1.aar<br>play-services-gass-10.0.1.aar<br>play-services-tasks-10.0.1.aar |

**Permission：**
```xml
--
```

**AndroidManifest.xml Component：**
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


<br>


### AppLovin

|                   |                                |
| ----------------- | ------------------------------ |
| Jar Name          | libs/yumi_adapter_applovin.jar |
| Provider Ver      | 8.0.2                          |
| GooglePlayService | Require                        |
| Ad Form           | Interstitial, Reward Video     |
| .so/lib project   | --                             |

**Permission：**
```xml
--
```

**AndroidManifest.xml Component：**
```xml
<activity android:name="com.applovin.adview.AppLovinInterstitialActivity" 
          android:configChanges="orientation|screenSize"/>
<activity android:name="com.applovin.adview.AppLovinConfirmationActivity" />
```

**ProGuard：**
```c
-dontwarn com.applovin.**
-keep public class com.applovin.**.*
```


<br>


### Baidu(百度)

|                   |                             |
| ----------------- | --------------------------- |
| Jar Name          | libs/yumi_adapter_baidu.jar |
| Provider Ver      | 5.6                         |
| GooglePlayService | --                          |
| Ad Form           | Banner, Interstitial        |
| .so/lib project   | --                          |

**Permission：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

**AndroidManifest.xml Component：**
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


<br>


### Centrixlink

|                   |                                   |
| ----------------- | --------------------------------- |
| Jar Name          | libs/yumi_adapter_centrixlink.jar |
| Provider Ver      | 2.4.2                             |
| GooglePlayService | --                                |
| Ad Form           | Reward Video                      |
| .so/lib project   | \assets\                          |

**Permission：**
```xml
-- 
```

**AndroidManifest.xml Component：**
```xml
<activity android:name="com.centrixlink.SDK.FullScreenADActivity"
	android:configChanges="keyboard|keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize"
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
	android:process=":adprocess"
	tools:ignore="InnerclassSeparator" />
<activity android:name="com.centrixlink.SDK.ResizedVideoADActivity"
	android:configChanges="keyboard|keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize"
	android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"
	android:hardwareAccelerated="true"
	android:process=":adprocess"
	tools:ignore="InnerclassSeparator" />
<service
	android:name="com.centrixlink.SDK.service.CentrixlinkService"
	android:exported="false" />
```

**ProGuard：**
```c
-dontwarn com.centrixlink.**
-keep public class com.centrixlink.**  { *; }
```


<br>


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


**AndroidManifest.xml Component：**
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


<br>


### Coconut(椰子视频)

|                   |                                  |
| ----------------- | -------------------------------- |
| Jar Name          | libs/yumi_adapter_coconut.jar    |
| Provider Ver      | 3.2.9                            |
| GooglePlayService | --                               |
| Ad Form           | Reward Video                     |
| .so/lib project   | \res\xml <br> \assets\ay_storage |

**Permission：**
```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```

**AndroidManifest.xml Component：**
```xml
<provider
    android:name="com.afk.client.ads.AdFileProvider"
    android:authorities="${applicationId}.fileprovider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/provider_paths" />
</provider>
<activity
    android:name="com.afk.client.ads.AdActivity"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:screenOrientation="sensor"
    android:theme="@android:style/Theme.Light.NoTitleBar.Fullscreen" />
<activity
    android:name="com.afk.permission.RequestPermissionActivity"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:screenOrientation="sensor"
    android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
<service
    android:name="com.afk.client.ads.DownloadService"
    android:exported="false" />
```

**ProGuard：**
```c
-keep class com.afk.** {*;} 
-keep class com.google.protobuf.** {*;}
-keepattributes *Annotation* 
-keepattributes *JavascriptInterface*
```


<br>


### Facebook

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_facebook.jar                               |
| Provider Ver      | 4.26.1                                                       |
| minSdkVersion     | Android 3.0  /  API 11                                       |
| GooglePlayService | Require                                                      |
| Ad Form           | Banner, Interstitial , Reward Video (Videos ads in Audience Network requires the hardware accelerated rendering to be enabled, otherwise you might experience a black screen in the video views) |
| .so/lib project   | play-services-ads-10.0.1.aar <br> play-services-ads-lite-10.0.1.aar <br> play-services-base-10.0.1.aar <br> play-services-basement-10.0.1.aar <br> play-services-clearcut-10.0.1.aar <br> play-services-gass-10.0.1.aar <br> play-services-tasks-10.0.1.aar <br> android-support-v7-recyclerview.jar <br> android-support-v7-appcompat.jar <br> AudienceNetwork.aar <br> |

**Permission：**
```xml
--
```

**AndroidManifest.xml Component：**
```xml
<activity
    android:name="com.facebook.ads.AudienceNetworkActivity"
    android:configChanges="keyboardHidden|orientation|screenSize" />
```

**ProGuard：**
```c
-keep class com.facebook.** { *; }
```


<br>


### GdtMob(广点通)

|                   |                           |
| ----------------- | ------------------------- |
| Jar Name          | libs/yumi_adapter_gdt.jar |
| Provider Ver      | 4.9.544                   |
| GooglePlayService | --                        |
| Ad Form           | Banner, Interstitial      |
| .so/lib project   | \assets\gdt_plugin        |

**Permission：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />  
<uses-permission android:name="android.permission.ACCESS_COARSE_UPDATES"/>
```

**AndroidManifest.xml Component：**
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


<br>


### Inmobi

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_inmobi.jar                                 |
| Provider Ver      | 6.0.4                                                        |
| minSdkVersion     | Android 2.3 / API 9;  Android 4.0 / API 14 (Reward Video广告) |
| GooglePlayService | Require                                                      |
| Ad Form           | Banner, Interstitial, Reward Video                           |
| .so/lib project   | --                                                           |

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

**AndroidManifest.xml Component：**
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


<br>


### Mobvista

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_mobvista.jar                               |
| Provider Ver      | 8.7.4                                                        |
| GooglePlayService | --                                                           |
| Ad Form           | Reward Video                                                 |
| .so/lib project   | \res\ anim <br> \res\drawable <br> \res\drawable-hdpi <br> \res\layout <br> \res\values |

**Permission：**
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

**AndroidManifest.xml Component：**
```xml
<activity
	android:name="com.mobvista.msdk.reward.player.MVRewardVideoActivity"
	android:configChanges="orientation|keyboardHidden|screenSize"
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
<activity
	android:name="com.mobvista.msdk.activity.MVCommonActivity"
	android:configChanges="keyboard|orientation"
	android:screenOrientation="portrait"
	android:theme="@android:style/Theme.NoTitleBar" >
</activity>
<service android:name="com.mobvista.msdk.shell.MVService" >
	<intent-filter>
		<action android:name="com.mobvista.msdk.download.action" />
	</intent-filter>
</service>
```

**ProGuard：**
```c
-keepattributes Signature   
-keepattributes *Annotation*   
-keep class com.mobvista.** {*; }  
-keep interface com.mobvista.** {*; }  
-keep class android.support.v4.** { *; }  
-dontwarn com.mobvista.**   
-keep class **.R$* { public static final int mobvista*; }
-keep class com.alphab.** {*; }
-keep interface com.alphab.** {*; }
```


<br>


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

**AndroidManifest.xml Component：**
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


<br>


### StartApp

|                   |                                    |
| ----------------- | ---------------------------------- |
| Jar Name          | libs/yumi_adapter_startapp.jar     |
| Provider Ver      | 3.5.4                              |
| GooglePlayService | --                                 |
| Ad Form           | Banner, Interstitial, Reward Video |
| .so/lib project   | --                                 |

**Permission：**
```xml
--
```

**AndroidManifest.xml Component：**
```xml
<activity android:name="com.startapp.android.publish.ads.list3d.List3DActivity"
	android:theme="@android:style/Theme" />
<activity android:name="com.startapp.android.publish.adsCommon.activities.OverlayActivity"
	android:configChanges="orientation|keyboardHidden|screenSize"
	android:theme="@android:style/Theme.Translucent" />
<activity android:name="com.startapp.android.publish.adsCommon.activities.FullScreenActivity"
	android:configChanges="orientation|keyboardHidden|screenSize"
	android:theme="@android:style/Theme" />
<service android:name="com.startapp.android.publish.common.metaData.PeriodicMetaDataService" />
<service android:name="com.startapp.android.publish.common.metaData.InfoEventService" />
<receiver android:name="com.startapp.android.publish.common.metaData.BootCompleteListener" >
<intent-filter>
    <action android:name="android.intent.action.BOOT_COMPLETED" />
</intent-filter>
</receiver>
```

**ProGuard：**
```c
-keep class com.startapp.** { *;}
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile,
LineNumberTable, *Annotation*, EnclosingMethod
-dontwarn android.webkit.JavascriptInterface
-dontwarn com.startapp.**
```


<br>


### Unity

|                   |                             |
| ----------------- | --------------------------- |
| Jar Name          | libs/yumi_adapter_unity.jar |
| Provider Ver      | 2.1.0                       |
| GooglePlayService | Require                     |
| Ad Form           | Interstitial,Reward Video   |
| .so/lib project   | --                          |

**Permission：**
```xml
--
```

**AndroidManifest.xml Component：**
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


<br>


### Vungle

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_vungle.jar                                 |
| Provider Ver      | 5.3.2                                                        |
| GooglePlayService | Require                                                      |
| Ad Form           | Interstitial, Reward Video                                   |
| .so/lib project   | adapter-rxjava-2.2.0.jar  <br>  converter-gson-2.2.0.jar  <br>  dagger-2.7.jar  <br>  eventbus-2.2.1.jar  <br>  gson-2.7.jar  <br>  javax.inject-1.jar  <br>  okhttp-3.6.0.jar  <br>  okio-1.11.0.jar  <br>  retrofit-2.2.0.jar  <br>  rxjava-1.2.0.jar |

**Permission：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

**AndroidManifest.xml Component：**
```xml
<activity android:name="com.vungle.publisher.VideoFullScreenAdActivity"            
	android:configChanges="keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize"  
	android:theme="@android:style/Theme.NoTitleBar.Fullscreen"/>
<activity android:name="com.vungle.publisher.MraidFullScreenAdActivity"            
	android:configChanges="keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize"  
	android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"/>
<activity android:name="com.vungle.publisher.FlexViewAdActivity"            
	android:configChanges="keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize"  
	android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"/>
```

**ProGuard：**
```c
# Vungle
-dontwarn com.vungle.**
-dontnote com.vungle.**
-keep class com.vungle.** { *; }
-keep class javax.inject.*
# GreenRobot
-dontwarn de.greenrobot.event.util.**
# RxJava
-dontwarn rx.internal.util.unsafe.**
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
-keep class rx.schedulers.Schedulers { public static <methods>; }
-keep class rx.schedulers.ImmediateScheduler { public <methods>; }
-keep class rx.schedulers.TestScheduler { public <methods>; }
-keep class rx.schedulers.Schedulers { public static ** test(); }
# MOAT
-dontwarn com.moat.**
-keep class com.moat.** { public protected private *; }
# Retrofit
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
```


<br>


### Xiaomi(小米)

|                   |                                                    |
| ----------------- | -------------------------------------------------- |
| Jar Name          | libs/yumi_adapter_xiaomi.jar                       |
| Provider Ver      | 20022                                              |
| GooglePlayService | --                                                 |
| Ad Form           | Interstitial                                       |
| .so/lib project   | assets/AdServer.apk <br> assets/analytics_core.apk |

**Permission：**
```xml
<uses-permission android:name="android.permission.GET_TASKS"/>
```

**AndroidManifest.xml Component：**
```xml
<activity android:name="com.xiaomi.ad.AdActivity"
	android:configChanges="keyboard|keyboardHidden|orientation|screenSize"
	android:exported="false"
	android:screenOrientation="behind"
	android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen">
</activity>
```

**ProGuard：**
```c
-keep class com.xiaomi.ad.**{*;}
-keep class com.miui.analytics.**{*;}
-keep class com.xiaomi.analytics.*{public protected *;}
```


<br>


### ZPLAYAds

|                   |                                   |
| ----------------- | --------------------------------- |
| Jar Name          | libs/yumi_adapter_playableads.jar |
| Provider Ver      | 2.0.6                             |
| GooglePlayService | --                                |
| Ad Form           | Interstitial, Reward Video        |
| .so/lib project   | --                                |

**Permission：**
```xml
--
```

**AndroidManifest.xml Component：**
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