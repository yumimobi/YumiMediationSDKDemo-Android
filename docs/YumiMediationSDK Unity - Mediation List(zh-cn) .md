# YumiMediationSDK Android - Mediation List

## 概述

使用此列表之前, 请确保已经根据《YumiMobi聚合广告SDK-横幅/插屏/视频广告接入说明》完成接入工作, 在需要添加支持的三方SDK时, 根据以下步骤完成接入。

-  添加三方adapter：在SDK的Adapter文件夹下, 找到对应的三方jar 添加到工程的libs 文件下。

-  添加三方.so文件(如果需要): 部分平台需要.so文件, 将Library文件夹内内容添加到工程的libs文件下。

-  引用三方lib工程(如果需要): 部分平台并不是以.jar 形式提供SDK,而是以lib工程形式。 请将lib工程导入到工作空间, 并作为library使用。

-  添加额外的权限: 将额外的权限注册到 \Assets\plugins\Android\AndroidManifest.xml中。

-  添加三方组件: 将三方需要的组件注册到 \Assets\plugins\Android\AndroidManifest.xml中。

-  使用时, 确定支持的广告形式。(Banner, 插屏, 视频)

-  混淆时, 请根据平台, 在混淆文件中加入混淆内容

-  请注意三方SDK最小安卓版本, 可能高于玉米聚合广告SDK所要求的最小安卓版本, 此时您的工程应选用两者中较高的版本作为工程的最小版本, 否则使用三方平台时, 可能造成未知的影响。 "-"意味着和玉米聚合所支持的安卓最小版本版本一致。


## 支持列表

### Adcolony

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar名称           | libs/yumi_adapter_adcolony.jar                               |
| 三方版本          | 3.0.7                                                        |
| 最小安卓版本      | Android 2.3.3  /  API 10                                     |
| GooglePlayService | 需要                                                         |
| 支持广告形式      | 视频                                                         |
| .so/lib工程       | /armeabi  <br> / arm64-v8a  <br> /armeabi-v7a  <br> /x86 <br> /x86_64 |

**额外权限：**
```xml
<uses-permission android:name="android.permission.VIBRATE" />
```

**AndroidManifest.xml注册组件：**
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


<br>


### Admob

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar名称           | libs/yumi_adapter_admob.jar                                  |
| 三方版本          | --                                                           |
| GooglePlayService | 需要                                                         |
| 支持广告形式      | Banner, 插屏                                                 |
| .so/lib工程       | play-services-ads-10.0.1.aar <br> play-services-ads-lite-10.0.1.aar<br>play-services-base-10.0.1.aar<br>play-services-basement-10.0.1.aar<br>play-services-clearcut-10.0.1.aar<br>play-services-gass-10.0.1.aar<br>play-services-tasks-10.0.1.aar |

**额外权限：**
```xml
--
```

**AndroidManifest.xml注册组件：**
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


<br>


### AppLovin

|                   |                                |
| ----------------- | ------------------------------ |
| Jar名称           | libs/yumi_adapter_applovin.jar |
| 三方版本          | 8.0.2                          |
| GooglePlayService | 需要                           |
| 支持广告形式      | 插屏, 视频                     |
| .so/lib工程       | --                             |

**额外权限：**
```xml
--
```

**AndroidManifest.xml注册组件：**
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


<br>


### Baidu(百度)

|                   |                             |
| ----------------- | --------------------------- |
| Jar名称           | libs/yumi_adapter_baidu.jar |
| 三方版本          | 5.6                         |
| GooglePlayService | --                          |
| 支持广告形式      | Banner, 插屏                |
| .so/lib工程       | --                          |

**额外权限：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

**AndroidManifest.xml注册组件：**
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


<br>


### Centrixlink

|                   |                                   |
| ----------------- | --------------------------------- |
| Jar名称           | libs/yumi_adapter_centrixlink.jar |
| 三方版本          | 2.4.2                             |
| GooglePlayService | --                                |
| 支持广告形式      | 视频                              |
| .so/lib工程       | \assets\                          |

**额外权限：**
```xml
-- 
```

**AndroidManifest.xml注册组件：**
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

**混淆：**
```c
-dontwarn com.centrixlink.**
-keep public class com.centrixlink.**  { *; }
```


<br>


### Chartboost

|                   |                                  |
| ----------------- | -------------------------------- |
| Jar名称           | libs/yumi_adapter_chartboost.jar |
| 三方版本          | 6.6.1                            |
| GooglePlayService | 需要                             |
| 支持广告形式      | 插屏, 视频                       |
| .so/lib工程       | --                               |

**额外权限：**
```xml
-- 
```


**AndroidManifest.xml注册组件：**
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


<br>


### Coconut(椰子视频)

|                   |                                  |
| ----------------- | -------------------------------- |
| Jar名称           | libs/yumi_adapter_coconut.jar    |
| 三方版本          | 3.2.9                            |
| GooglePlayService | --                               |
| 支持广告形式      | 视频                             |
| .so/lib工程       | \res\xml <br> \assets\ay_storage |

**额外权限：**
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

**AndroidManifest.xml注册组件：**
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

**混淆：**
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
| Jar名称           | libs/yumi_adapter_facebook.jar                               |
| 三方版本          | 4.26.1                                                       |
| 最小安卓版本      | Android 3.0  /  API 11                                       |
| GooglePlayService | 需要                                                         |
| 支持广告形式      | Banner, 插屏 , 视频 (视频广告需要启用硬件加速功能，否则会导致黑屏) |
| .so/lib工程       | play-services-ads-10.0.1.aar <br> play-services-ads-lite-10.0.1.aar <br> play-services-base-10.0.1.aar <br> play-services-basement-10.0.1.aar <br> play-services-clearcut-10.0.1.aar <br> play-services-gass-10.0.1.aar <br> play-services-tasks-10.0.1.aar <br> android-support-v7-recyclerview.jar <br> android-support-v7-appcompat.jar <br> AudienceNetwork.aar <br> |

**额外权限：**
```xml
--
```

**AndroidManifest.xml注册组件：**
```xml
<activity
    android:name="com.facebook.ads.AudienceNetworkActivity"
    android:configChanges="keyboardHidden|orientation|screenSize" />
```

**混淆：**
```c
-keep class com.facebook.** { *; }
```


<br>


### GdtMob(广点通)

|                   |                           |
| ----------------- | ------------------------- |
| Jar名称           | libs/yumi_adapter_gdt.jar |
| 三方版本          | 4.9.544                   |
| GooglePlayService | --                        |
| 支持广告形式      | Banner, 插屏              |
| .so/lib工程       | \assets\gdt_plugin        |

**额外权限：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />  
<uses-permission android:name="android.permission.ACCESS_COARSE_UPDATES"/>
```

**AndroidManifest.xml注册组件：**
```xml
<service  android:name="com.qq.e.comm.DownloadService" android:exported="false" >
</service>
<activity
	android:name="com.qq.e.ads.ADActivity"
	android:configChanges="keyboard|keyboardHidden|orientation|screenSize" >
</activity>
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


<br>


### Inmobi

|                   |                                                       |
| ----------------- | ----------------------------------------------------- |
| Jar名称           | libs/yumi_adapter_inmobi.jar                          |
| 三方版本          | 6.0.4                                                 |
| 最小安卓版本      | Android 2.3 / API 9;  Android 4.0 / API 14 (视频广告) |
| GooglePlayService | 需要                                                  |
| 支持广告形式      | Banner, 插屏, 视频                                    |
| .so/lib工程       | --                                                    |

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

**AndroidManifest.xml注册组件：**
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


<br>


### Mobvista

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar名称           | libs/yumi_adapter_mobvista.jar                               |
| 三方版本          | 8.7.4                                                        |
| GooglePlayService | --                                                           |
| 支持广告形式      | 视频                                                         |
| .so/lib工程       | \res\ anim <br> \res\drawable <br> \res\drawable-hdpi <br> \res\layout <br> \res\values |

**额外权限：**
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

**AndroidManifest.xml注册组件：**
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

**混淆：**
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

**AndroidManifest.xml注册组件：**
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


<br>


### StartApp

|                   |                                |
| ----------------- | ------------------------------ |
| Jar名称           | libs/yumi_adapter_startapp.jar |
| 三方版本          | 3.5.4                          |
| GooglePlayService | --                             |
| 支持广告形式      | Banner, 插屏, 视频             |
| .so/lib工程       | --                             |

**额外权限：**
```xml
--
```

**AndroidManifest.xml注册组件：**
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

**混淆：**
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
| Jar名称           | libs/yumi_adapter_unity.jar |
| 三方版本          | 2.1.0                       |
| GooglePlayService | 需要                        |
| 支持广告形式      | 插屏,视频                   |
| .so/lib工程       | --                          |

**额外权限：**
```xml
--
```

**AndroidManifest.xml注册组件：**
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


<br>


### Vungle

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar名称           | libs/yumi_adapter_vungle.jar                                 |
| 三方版本          | 5.3.2                                                        |
| GooglePlayService | 需要                                                         |
| 支持广告形式      | 插屏, 视频                                                   |
| .so/lib工程       | adapter-rxjava-2.2.0.jar  <br>  converter-gson-2.2.0.jar  <br>  dagger-2.7.jar  <br>  eventbus-2.2.1.jar  <br>  gson-2.7.jar  <br>  javax.inject-1.jar  <br>  okhttp-3.6.0.jar  <br>  okio-1.11.0.jar  <br>  retrofit-2.2.0.jar  <br>  rxjava-1.2.0.jar |

**额外权限：**
```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

**AndroidManifest.xml注册组件：**
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

**混淆：**
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


### 小米(Xiaomi)

|                   |                                                    |
| ----------------- | -------------------------------------------------- |
| Jar名称           | libs/yumi_adapter_xiaomi.jar                       |
| 三方版本          | 20022                                              |
| GooglePlayService | --                                                 |
| 支持广告形式      | 插屏                                               |
| .so/lib工程       | assets/AdServer.apk <br> assets/analytics_core.apk |

**额外权限：**
```xml
<uses-permission android:name="android.permission.GET_TASKS"/>
```

**AndroidManifest.xml注册组件：**
```xml
<activity android:name="com.xiaomi.ad.AdActivity"
	android:configChanges="keyboard|keyboardHidden|orientation|screenSize"
	android:exported="false"
	android:screenOrientation="behind"
	android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen">
</activity>
```

**混淆：**
```c
-keep class com.xiaomi.ad.**{*;}
-keep class com.miui.analytics.**{*;}
-keep class com.xiaomi.analytics.*{public protected *;}
```


<br>


### ZPLAYAds

|                   |                                   |
| ----------------- | --------------------------------- |
| Jar名称           | libs/yumi_adapter_playableads.jar |
| 三方版本          | 2.0.6                             |
| GooglePlayService | --                                |
| 支持广告形式      | 插屏, 视频                        |
| .so/lib工程       | --                                |

**额外权限：**
```xml
--
```

**AndroidManifest.xml注册组件：**
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
