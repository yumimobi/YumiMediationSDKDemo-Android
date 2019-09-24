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

- When developing with AndroidStudio, the adapter version number should be consistent with the main package version number of the corn SDK. To add such, Adapter, for example, you use the corn SDK for version 4.3.0 "com.yumimobi.ads:mediation:4.3.0", you need to add such Adapter 4.3.0 version "com.yumimobi.ads.mediation:such:4.3.0"

## Support List

### Adcolony

|                   |                                                        |
| ----------------- | ------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_adcolony.jar                         |
| Provider Ver      | 3.3.10                                                  |
| minSdkVersion     | Android 2.3.3 / API 10                                 |
| GooglePlayService | Require                                                |
| Ad Form           | Reward Video                                           |
| .so/lib project   | /armeabi  <br /> / arm64-v8a  <br /> /armeabi-v7a  <br /> /x86 <br /> /x86_64 |

**Permission：**
```xml
<!--Optional Permissions-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /> 
<uses-permission android:name="android.permission.VIBRATE" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:adcolony:4.3.0'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity android:name="com.adcolony.sdk.AdColonyInterstitialActivity"
  android:configChanges="keyboardHidden|orientation|screenSize"
  android:hardwareAccelerated="true"/>
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
| Provider Ver      | 17.2.0                         |
| GooglePlayService | Require                     |
| Ad Form           | Banner, Interstitial, Reward Video, Native|
| .so/lib project   | --                          |

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:admob:4.3.0'
}
```

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
| Provider Ver      | 9.7.2                         |
| GooglePlayService | Require                        |
| Ad Form           | Banner, Interstitial, Reward Video     |
| .so/lib project   | --                             |

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:applovin:4.3.0'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity
  android:name="com.applovin.adview.AppLovinInterstitialActivity"
  android:configChanges="orientation|screenSize"
  android:hardwareAccelerated="true" />
<activity
  android:name="com.applovin.impl.adview.AppLovinOrientationAwareInterstitialActivity"
  android:configChanges="orientation|screenSize"
  android:hardwareAccelerated="true"
  android:screenOrientation="behind" />
<activity
  android:name="com.applovin.sdk.AppLovinWebViewActivity"
  android:configChanges="keyboardHidden|orientation|screenSize" />
<activity
  android:name="com.applovin.mediation.MaxDebuggerActivity"
  android:configChanges="keyboardHidden|orientation|screenSize"
  android:theme="@style/com.applovin.mediation.MaxDebuggerActivity.Theme" />
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
| Provider Ver      | 5.8.0                         |
| GooglePlayService | --                          |
| Ad Form           | Banner, Interstitial, Reward Video, Native, Splash        |
| .so/lib project   | --                          |

**Permission：**
```xml
<!--Required Permissions-->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:baidu:4.3.0'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity
	android:name="com.baidu.mobads.AppActivity"
	android:configChanges="keyboard|keyboardHidden|orientation" />
<activity
  android:name="com.baidu.mobads.production.rewardvideo.MobRewardVideoActivity"
  android:configChanges="screenSize|orientation|keyboardHidden"
  android:launchMode="singleTask"
  android:theme="@android:style/Theme.Translucent.NoTitleBar" >
</activity>
<provider
  android:name="com.baidu.mobads.openad.FileProvider"
  android:authorities="${applicationId}.bd.provider"
  android:exported="false"
  android:grantUriPermissions="true">
  <meta-data
      android:name="android.support.FILE_PROVIDER_PATHS"
      android:resource="@xml/bd_file_paths" />
</provider>
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
| Provider Ver      | 7.3.1                            |
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
  implementation 'com.yumimobi.ads.mediation:chartboost:4.3.0'
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
| Provider Ver      | 5.4.1                                                       |
| minSdkVersion     | Android 3.0 / API 11                                         |
| GooglePlayService | Require                                                      |
| Ad Form           | Banner, Interstitial , Reward Video (Videos ads in Audience Network requires the hardware accelerated rendering to be enabled, otherwise you might experience a black screen in the video views), Native |
| .so/lib project   | \res\xml                                                     |

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:facebook:4.3.0'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
  <activity
    android:name="com.facebook.ads.AudienceNetworkActivity"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:exported="false"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
  <activity
    android:name="com.facebook.ads.internal.ipc.RemoteANActivity"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:exported="false"
    android:process=":adnw"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" />

  <service
      android:name="com.facebook.ads.internal.ipc.AdsProcessPriorityService"
      android:exported="false" />
  <service
      android:name="com.facebook.ads.internal.ipc.AdsMessengerService"
      android:exported="false"
      android:process=":adnw" />
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
| Provider Ver      | 4.40.910                   |
| GooglePlayService | --                        |
| Ad Form           | Banner, Interstitial, Reward Video, Native, Splash      |
| .so/lib project   | --                        |

**Permission：**
```xml
<!--Required Permissions-->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" /> 
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:gdt:4.3.0'
}
```
<p><span style="color:red;">Precautions:
If you use AndroidStudio to access the GdtMob (wide point) platform，please make sure that the package:"xxx.xxx.xxx" name in your app's AndroidManifest.xml is consistent with the applicationId "xxx.xxx.xxx" in the build.gradle file.for example：</span></p>
<img src="document\image11.png" alt="img11">
**Using Eclipse**

AndroidManifest.xml Component：

```xml
//Please add android:usesCleartextTraffic="true" to the AndroidManifest.xml application
<application android:usesCleartextTraffic="true" >

//Please add the following configuration in the application
<meta-data
  android:name="com.tencent.rdm.uuid"
  android:value="3f34c50c-1bd6-44cf-9f47-c5d6adf9bee7" />
<uses-library
  android:name="org.apache.http.legacy"
  android:required="false" />
<service
  android:name="com.qq.e.comm.DownloadService"
  android:exported="false" />
<activity
  android:name="com.qq.e.ads.ADActivity"
  android:configChanges="keyboard|keyboardHidden|orientation|screenSize" />
<activity
  android:name="com.qq.e.ads.PortraitADActivity"
  android:configChanges="keyboard|keyboardHidden|orientation|screenSize"
  android:screenOrientation="portrait" />
<activity
  android:name="com.qq.e.ads.LandscapeADActivity"
  android:configChanges="keyboard|keyboardHidden|orientation|screenSize"
  android:screenOrientation="landscape" />
```

**ProGuard：**
```c
-keep class com.qq.e.** { 
    public protected *; 
}
-keep class MTT.ThirdAppInfoNew { 
    *; 
}
-keep class com.tencent.** { 
    *;
} 
-dontwarn dalvik.**
-dontwarn com.tencent.smtt.**  
```
<br />


### Inmobi

|                   |                                    |
| ----------------- | ---------------------------------- |
| Jar Name          | libs/yumi_adapter_inmobi.jar       |
| Provider Ver      | 8.0.9                              |
| minSdkVersion     | Android 2.3 / API 9;  Android 4.0 / API 14 (For Reward Video)|
| GooglePlayService | Require                            |
| Ad Form           | Banner, Interstitial, Reward Video |
| .so/lib project   | --                                 |

**Permission：**
```xml
<!--Optional Permissions-->
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
  implementation 'com.yumimobi.ads.mediation:inmobi:4.3.0'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity
    android:name="com.inmobi.ads.rendering.InMobiAdActivity"
    android:configChanges="keyboardHidden|orientation|keyboard|smallestScreenSize|screenSize|screenLayout|locale|fontScale"
    android:hardwareAccelerated="true"
    android:resizeableActivity="false"
    android:theme="@android:style/Theme.NoTitleBar"
    tools:ignore="UnusedAttribute" />
```

**ProGuard：**
```c
-keepattributes SourceFile,LineNumberTable
-keep class com.inmobi.** { *; }
-dontwarn com.inmobi.**
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-dontwarn com.squareup.picasso.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{public *;}
#skip the Picasso library classes
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**
#skip Moat classes
-keep class com.moat.** {*;}
-dontwarn com.moat.**
#skip AVID classes
-keep class com.integralads.avid.library.** {*;}
```


<br />


### Mintegral

|                   |                                                              |
| ----------------- | ------------------------------------------------------------ |
| Jar Name          | libs/yumi_adapter_mintegral.jar                               |
| Provider Ver      | 9.13.3                                                        |
| GooglePlayService | --                                                           |
| Ad Form           | Interstitial, Reward Video                                                 |
| .so/lib project   | \res\ anim <br /> \res\drawable <br /> \res\drawable-hdpi <br /> \res\layout <br /> \res\values  |

**Permission：**
```xml
<!--Optional Permissions-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
<!-- mintegral-china SDK Required Permissions-->
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  //GooglePlay release or release outside China market channel
  implementation 'com.yumimobi.ads.mediation:mintegral:4.3.0'
  //China market channel release
  implementation 'com.yumimobi.ads.mediation:mintegral-china:4.3.0'
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
  android:exported="true"
  android:screenOrientation="portrait"
  android:theme="@android:style/Theme.Translucent.NoTitleBar" />
<activity
  android:name="com.mintegral.msdk.mtgjscommon.authority.activity.MTGAuthorityActivity"
  android:configChanges="keyboardHidden|orientation|screenSize" />
<service android:name="com.mintegral.msdk.shell.MTGService" >
  <intent-filter>
      <action android:name="com.mintegral.msdk.download.action" />
  </intent-filter>
</service>
<receiver android:name="com.mintegral.msdk.click.AppReceiver" >
  <intent-filter>
      <action android:name="android.intent.action.PACKAGE_ADDED" />
      <data android:scheme="package" />
  </intent-filter>
</receiver>
<provider
  android:name="com.mintegral.msdk.base.utils.MTGFileProvider"
  android:authorities="${applicationId}.mtgFileProvider"
  android:exported="false"
  android:grantUriPermissions="true" >
  <meta-data
      android:name="android.support.FILE_PROVIDER_PATHS"
      android:resource="@xml/mtg_provider_paths" />
</provider>
<activity
  android:name="com.mintegral.msdk.interstitial.view.MTGInterstitialActivity"
  android:configChanges="orientation|screenSize"
  android:screenOrientation="portrait" />
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
| Provider Ver      | 2.3.4                        |
| GooglePlayService | --                           |
| Ad Form           | Reward Video                 |
| .so/lib project   | --                           |

**Permission：**
```xml
<!--Optional Permissions-->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:oneway:4.3.0'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
<activity
  android:name="mobi.oneway.export.AdShowActivity"
  android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"
  android:hardwareAccelerated="true"
  android:launchMode="singleTask"
  android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
<provider
  android:name="mobi.oneway.export.OWProvider"
  android:authorities="${applicationId}.provider"
  android:exported="false"
  android:grantUriPermissions="true" >
  <meta-data
      android:name="android.support.FILE_PROVIDER_PATHS"
      android:resource="@xml/ow_file_paths" />
</provider>
<provider
  android:name="mobi.oneway.common.OwGFileProvider"
  android:authorities="${applicationId}.fileprovider"
  android:exported="false"
  android:grantUriPermissions="true" >
  <meta-data
      android:name="android.support.FILE_PROVIDER_PATHS"
      android:resource="@xml/ow_g_file_path" />
</provider>
<provider
  android:name="mobi.oneway.common.OwBFileProvider"
  android:authorities="${applicationId}.bd.provider"
  android:exported="false"
  android:grantUriPermissions="true" >
  <meta-data
      android:name="android.support.FILE_PROVIDER_PATHS"
      android:resource="@xml/ow_b_file_paths" />
</provider>
```

**ProGuard：**
```c
-keep class android.support.v4.app.NotificationCompat$*{*;}
-keep class android.support.v4.content.FileProvider{*;}
-keep class android.support.v4.app.Fragment{*;}
-keepattributes *Annotation*
-keep enum mobi.oneway.export.* {*;}
-keep class mobi.oneway.export.** {*;}
-dontwarn com.liulishuo.okdownload.**
# com.liulishuo.okdownload 
-keep class com.liulishuo.okdownload.**{*;}
# okhttp https://github.com/square/okhttp/#proguard
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-keep class com.didi.virtualapk.** { *; }
-dontwarn com.didi.virtualapk.**
-dontwarn android.**
-keep class android.** { *; }
```


<br />

### Pubnative

|                   |                              |
| ----------------- | ---------------------------- |
| Jar Name           | -- |
| Provider Ver          | 0.6.1  |
| GooglePlayService | --        |
| Ad Form       | Banner, Interstitial, Native    |
| .so/lib project       | --|

**Permission：**
```xml
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:pubnative:4.3.0'
}
```

**ProGuard：**
```c
-keepattributes Signature
-keep class net.pubnative.** { *; }
```

<br />

### Unity

|                   |                             |
| ----------------- | --------------------------- |
| Jar Name          | libs/yumi_adapter_unity.jar |
| Provider Ver      | 3.1.0                       |
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
  implementation 'com.yumimobi.ads.mediation:unity:4.3.0'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
```xml
  <activity
      android:name="com.unity3d.services.ads.adunit.AdUnitActivity"
      android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"
      android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
      android:hardwareAccelerated="true" />
  <activity
      android:name="com.unity3d.services.ads.adunit.AdUnitTransparentActivity"
      android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"
      android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"
      android:hardwareAccelerated="true" />
  <activity
      android:name="com.unity3d.services.ads.adunit.AdUnitTransparentSoftwareActivity"
      android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"
      android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"
      android:hardwareAccelerated="false" />
  <activity
      android:name="com.unity3d.services.ads.adunit.AdUnitSoftwareActivity"
      android:configChanges="fontScale|keyboard|keyboardHidden|locale|mnc|mcc|navigation|orientation|screenLayout|screenSize|smallestScreenSize|uiMode|touchscreen"
      android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
      android:hardwareAccelerated="false" />
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
# Keep all classes in Unity Services package
-keep class com.unity3d.services.** {
   *;
}
-dontwarn com.google.ar.core.**
```


<br />


### Vungle

|                   |                              |
| ----------------- | ---------------------------- |
| Jar Name          | libs/yumi_adapter_vungle.jar |
| Provider Ver      | 6.4.10                       |
| GooglePlayService | Require                      |
| Ad Form           | Interstitial, Reward Video   |
| .so/lib project   | converter-gson-2.2.0.jar  <br />  fetch-1.1.5.jar  <br />  gson-2.7.jar  <br />  logging-interceptor-3.7.0.jar  <br />  okhttp-3.7.0.jar  <br />  okio-1.12.0.jar  <br />  retrofit-2.2.0.jar  <br />  VNG-moat-mobile-app-kit-2.2.0.jar |

**Permission：**
```xml
<!--Required Permissions-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="18" />
<!--Optional Permissions-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:vungle:4.3.0'
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
<receiver
    android:name="com.vungle.warren.NetworkProviderReceiver"
    android:enabled="false" >
    <intent-filter>
        <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</receiver>
```

**ProGuard：**
```c
-keep class com.vungle.warren.** { *; }
-dontwarn com.vungle.warren.error.VungleError$ErrorCode
-keep class com.vungle.warren.downloader.DownloadRequest
-dontwarn com.vungle.warren.downloader.DownloadRequest$Status
# Google
-keep class com.google.android.gms.internal.** { *; }
-dontwarn com.google.android.gms.ads.identifier.**
# Moat SDK
-keep class com.moat.** { *; }
-dontwarn com.moat.**
# Retrofit
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
## Retrofit2
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod
# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**
-dontwarn kotlin.reflect.jvm.internal.**
-keep class kotlin.reflect.jvm.internal.** { *; }
# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit
# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
```


<br />


### ZPLAYAds

|                   |                                   |
| ----------------- | --------------------------------- |
| Jar Name          | libs/yumi_adapter_playableads.jar |
| Provider Ver      | 2.4.0                             |
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
  implementation 'com.yumimobi.ads.mediation:playableads:4.3.0'
}
```

**Using Eclipse**

AndroidManifest.xml Component：
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
<!--Optional Permissions-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:ksyun:4.3.0'
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
  android:grantUriPermissions="true" >
  <meta-data
      android:name="android.support.FILE_PROVIDER_PATHS"
      android:resource="@xml/ksyun_file_paths" />
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
  implementation 'com.yumimobi.ads.mediation:ironsource:4.3.0'
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
-dontwarn com.ironsource.mediationsdk.**
-dontwarn com.ironsource.adapters.**
-dontwarn com.moat.**
-keep class com.moat.** { public protected private *; }
```

### Iqzone

|                   |                              |
| ----------------- | ---------------------------- |
| Jar Name           | libs/yumi_adapter_iqzone.jar |
| Provider Ver          | 2.3.2111                        |
| GooglePlayService | Require                         |
| Ad Form       | Banner, Interstitial, Reward Video                   |
| .so/lib project       | --|

**Permission：**
```xml
--
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
   implementation 'com.yumimobi.ads.mediation:iqzone:4.3.0'
}
```


**ProGuard：**
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

<br />

### Bytedance

|                   |                              |
| ----------------- | ---------------------------- |
| Jar Name           | -- |
| Provider Ver          | 1.9.9.5  |
| GooglePlayService | --        |
| Ad Form       | Banner, Interstitial, Reward Video, Splash     |
| .so/lib project       | --|

**Permission：**
```xml
<!--Required Permissions-->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
<uses-permission android:name="android.permission.GET_TASKS"/>
<!--Optional Permissions-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:bytedance:4.3.0'
}
```

**ProGuard：**
```c
-keep class com.bytedance.sdk.openadsdk.** { *; }
-keep class com.androidquery.callback.** {*;}
-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}
-keep class com.ss.sys.ces.* {*;}
```

<br />

### Tapjoy

|                   |                              |
| ----------------- | ---------------------------- |
| Jar Name           | -- |
| Provider Ver          | 12.3.1  |
| GooglePlayService | --        |
| Ad Form       | Interstitial, Reward Video     |
| .so/lib project       | --|

**Permission：**
```xml
```

**Using AndroidStudio**

build.gradle add：
```c
dependencies {
  implementation 'com.yumimobi.ads.mediation:tapjoy:4.3.0'
}
```

**ProGuard：**
```c
-keep class com.tapjoy.** { *; }
-keep class com.moat.** { *; }
-keepattributes JavascriptInterface
-keepattributes *Annotation*
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
-keep class com.google.android.gms.ads.identifier.** { *; }
-dontwarn com.tapjoy.**
```

<br />

