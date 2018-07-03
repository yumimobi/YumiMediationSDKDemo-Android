# YumiMediationSDK Android

1. [Overview](#overview)
2. [Development Environment Configuration](#development-environment-configuration)
	1. [Using Android-studio](#using-android-studio)
	2. [Using Eclipse](#using-eclipse)
	3. [Optional permission](#optional-permission)
3. [Integration](#integration)
	1. [Banner](#banner)
	2. [Interstitial](#interstitial)
	3. [Rewarded Video](#rewarded-video)
	4. [Splash](#splash)
	5. [Native](#native)
4. [Test Mode](#test-mode)
5. [Advanced Features](#advanced-features)
	1. [Banner](#banner-1)
	2. [Interstitial](#interstitial-1)
	3. [Rewarded Video](#rewarded-video-1)
	4. [Splash](#splash-1)
	5. [Proguard](#proguard)
6. [Reminder](#reminder)
	1. [Permissions for Android 6.0 and newer versions](#permissions-for-android-60-and-newer-versions)
	
## Overview

1. Target Readers 

   This document is for Android developers who want to integrate YUMIMOBI advertising SDK into their product.  

2. Development Environment

   OS：  Windows， Mac， Linux <br>
   Android SDK：&ensp;&gt;&ensp;4.4&ensp;(API level 19)<br>
   IDE： Eclipse with ADT (ADT version 23.0.4)&ensp;&ensp;OR&ensp;&ensp;Android-studio<br>
   Java：&ensp;&gt;&ensp;JDK 7

## Development Environment Configuration

### Using Android-studio

**Add the library**

```java
// ensure whether jcenter is supported in build.gradle under Project root directory of android studio 
buildscript {
    repositories {
   	 jcenter()
    }
}
allprojets {
    repositories {
    	jcenter()
    }
}
//Add dependency in module build. Gradle
dependencies {
    //(*.*.*.+) Please replace it with the latest SDK version number, example ：3.3.6.+
    compile 'com.yumimobi.ads:mediation:*.*.*.+'
    compile 'com.yumimobi.ads.mediation:mraid:*.*.*.+' // Optional : We hope to support mraid advertising
｝
```

<a href="https://github.com/yumimobi/YumiMediationSDKDemo-Android#Latest&nbsp;Version">Please check the latest version number</a>

<a href="https://www.iab.com/guidelines/mobile-rich-media-ad-interface-definitions-mraid/">MRAID, or “Mobile Rich Media Ad Interface Definitions,” is the common API (Application Programming Interface) for mobile rich media ads that will run in mobile apps.</a>

### Using Eclipse

**Add lib file**

All lib files are placed in lib in the SDK:

- YumiMobi_Android_vX.X.X.jar

- Yumi_Adapter_Mraid_vX.X.X.jar

- android-support-v4.jar

- android-support-v7-appcompat.jar

- google_play_service的lib工程

Create libs folder under the root directory of your project,add YumiMobi_Android_vX.X.X.jar into libs.

<img src="document\image01.jpg" alt="img1">

you can choose to or not to add android-support-v4.jar and/or android-support-v7-appcompat.jar and/or Yumi_Adapter_Mraid_vX.X.X.jar into libs according to your needs. You must use the jar file provided by YUMIMOBI when you need to use v4.jar or v7.jar.

<spen style="color:red;">
About google_play_service project:  
google_play_service is not mandatory, while some ad platforms need it. YUMIMOBI does not need google_play_service. You need use it as a library  and import it into your project. Also, add the ollowing code in tab &lt;application&gt; of your manifest.xml.
</spen>

```xml
<meta-data 
     android：name="com.google.android.gms.version"
     class="kix-line-break"
     android：value="@integer/google_play_services_version" />
```

**Add permission**

Add the following permissions in manifest.xml of your project:


```xml
<uses-permission android:name="android.permission.INTERNET"/>
<!--The Googleplay app can be unloaded-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

**Registered components**

Add following in manifest.xml of your project:

```xml
<receiver android:name="com.yumi.android.sdk.ads.self.module.receiver.ADReceiver" >
    <intent-filter>
        <action android:name="android.intent.action.DOWNLOAD_COMPLETE" />
    </intent-filter>
    <intent-filter>
        <action android:name="android.intent.action.PACKAGE_ADDED" />
        <data android:scheme="package" />
    </intent-filter>
</receiver>
<service  android:name="com.yumi.android.sdk.ads.service.YumiAdsEventService" />
<activity android:name="com.yumi.android.sdk.ads.self.activity.YumiFullScreenActivity"
          android:configChanges="keyboardHidden|orientation|screenSize"
          android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
<activity android:name="com.playableads.activity.PlayableADActivity"
          android:configChanges="orientation|screenSize|keyboardHidden"
          android:hardwareAccelerated="true"
          android:screenOrientation="portrait"
          android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
<receiver android:name="com.playableads.PlayableReceiver" >
	<intent-filter>
	     <action android:name="android.intent.action.DOWNLOAD_COMPLETE" />
	</intent-filter>
</receiver>
<!—Debugging Activity -->
<activity android:name="com.yumi.android.sdk.ads.mediation.activity.MediationTestActivity" ></activity>
```


### Optional permission

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
<!--Below are the permissions required for MRAID advertising-->
<uses-permission android:name="android.permission.CALL_PHONE"/>
<uses-permission android:name="android.permission.SEND_SMS"/>
<uses-permission android:name="android.permission.WRITE_CALENDAR"/>
```

## Integration

### Banner
**Creat a ViewGroup as banner container, and add it in Activity. Then call the code below:**

```java
//create YumiBanner object. Activity is the activity which you need to display banner ad. You need to create a SlotID on YUMIMOBI. Auto indicates if the mode is automatic. 
//auto==true  Banner ads automatic rotation
//auto==false  Banner ads manual rotation，call banner.requestYumiBanner() repeatedly to rotate
// If you are using YUMI mediation alone, please enable YUMI ad rotation, set the field as “true”; if you are using YUMI ads in other mediations, to ensure ad performance, please disable YUMI ad rotation, set the field as “false”.
banner = new YumiBanner(activity， "YOUR_SLOT_ID"， auto);
//Set ViewGroup as banner container, set it along with size
// bannerContainer  Your ad container
// AdSize.BANNER_SIZE_AUTO  SDK automatically sets screen size as 320*50 or 728*90
// isMatchWindowWidth ==true Banner width equals screen width
banner.setBannerContainer(bannerContainer， AdSize.BANNER_SIZE_AUTO， isMatchWindowWidth);
//Set channel according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
banner.setChannelID(channelStr);
// Set version name according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
banner.setVersionName(versionStr);
//Start requesting ads, auto==true  The method needs to be called only once
banner.requestYumiBanner();
```

isMatchWindowWidth description：[Banner ads automatically adapt to screen size](#isMatchWindowWidth)  </br>

<spen style="color:red;">
Note: ChannelID refers to the channel labeling of the application, and the YUMI platform can carry out data statistics and effect analysis according to the ChannelID. A Popstar! For example, when the game is released to the SamSung channel, setChannelID(channelStr) needs to be set to setChannelID(' SamSung ').
The channelID is labeled as the YUMI platform to generate the information and cannot be modified at will：
</spen>

| **Channel name** | **ChannelID** |
| ------------ | ------------- |
| SamSung      | SamSung       |

**Implement in Activity lifecycle:**

```java
@Override
protected void onDestroy() {
    if (banner != null) {
        banner.onDestory();
    }
	super.onDestroy();
}
```



### Interstitial
**Add the following code in onCreate method of Activity:**

```java
//Create YumiInterstitial object. Activity is the one you use to show interstitials, You need to create a SlotID on YUMIMOBI. Auto indicates if the mode is automatic.
//auto==true  Automatically request the next ad, auto mode recommended to ensure ad performance
//auto==false  Auto request disabled, to request please repeatedly call interstitial.requestYumiInterstitial()
// If you are using YUMI Ads, please enable YUMI ad rotation, set the field as “true”.
interstitial = new YumiInterstitial(activity， "YOUR_SLOT_ID"， auto);
// Set channel according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
interstitial.setChannelID(channelStr);
// Set version name according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
interstitial.setVersionName(versionStr);
//Start requesting ads, auto==true  The method needs to be called only once
interstitial.requestYumiInterstitial();
```
<spen style="color:red;">
Note: ChannelID refers to the channel labeling of the application, and the YUMI platform can carry out data statistics and effect analysis according to the ChannelID. A Popstar! For example, when the game is released to the SamSung channel, setChannelID(channelStr) needs to be set to setChannelID(' SamSung ').
The channelID is labeled as the YUMI platform to generate the information and cannot be modified at will：
</spen>

| **Channel name** | **ChannelID** |
| ------------ | ------------- |
| SamSung      | SamSung       |

**Call the following code when you need to show interstitial ads:**

```java
//Show immediately, if there is a pre-cached interstitial, immediately show the pop-up interstitial (Speed to pop up varies on different devices, there might be visual lag), if there isn’t a pre-cached interstitial, show no ads and drop the impression opportunity until the next call.
if (interstitial != null) {
	interstitial.showInterstitial(false);
}
//Delayed display  If when you intend to show interstitial, it’s still pre-caching, then delayed display allows SDK to wait for the pre-cach to complete, after completion it will show interstitial. Time waited is indefinite.
if (interstitial != null) {
	interstitial.showInterstitial(true);
}
//Cancel delayed display   If you need to do other operations when delayed display hasn’t started, you need to call the following method to cancel this delayed display. After cancellation, interstitial won’t be shown until the next call.
if (interstitial != null) {
	interstitial.cancelInterstitialDelayShown();
}
```

**Implement in Activity lifecycle:**

```java
@Override
protected void onDestroy() {
    if (interstitial != null) {
    	interstitial .onDestory();
    }
    super.onDestroy();
}
```

As different platforms have different pop-up displays, you need to add the following code in onBackPressed()of Activity: 

```java
@Override
public void onBackPressed() {
	if (interstitial.onBackPressed()) {
		return;
	}
	super.onBackPressed();
}
```

<p><spen style="color:red;">Note: In order not to confuse the logic of back key, please make sure to add this method when using interstitial</spen><p>



### Rewarded Video

**Add following code in onCreate method of Activity:**

```java
// Create YumiInterstitial object. Activity is the one you use to show interstitials, SlotID is the app ID which is assigned to you by the platform.
media = new YumiMedia(activity， "YOUR_SLOT_ID");
// Set channel according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
media.setChannelID(channelStr);
// Set version name according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
media.setVersionName(versionStr);
//Start requesting ads.
media.requestYumiMedia();
```
<spen style="color:red;">
Note: ChannelID refers to the channel labeling of the application, and the YUMI platform can carry out data statistics and effect analysis according to the ChannelID. A Popstar! For example, when the game is released to the SamSung channel, setChannelID(channelStr) needs to be set to setChannelID(' SamSung ').
The channelID is labeled as the YUMI platform to generate the information and cannot be modified at will：
</spen>

| **Channel name** | **ChannelID** |
| ------------ | ------------- |
| SamSung      | SamSung       |

**To check if video is available, call the following code:**

```java
if (media != null) {
	media.isMediaPrepared ();
}
```

<p><spen style="color:red;">Note: It is recommended to request every five seconds.</spen><p>

**When you need to show rewarded video, call the following code:**

```java
if (media != null) {
	media.showMedia();
}
```

<p><spen style="color:red;">Note:</spen><p>
<p><spen style="color:red;">1.Rewarded video is available after the above integration， but reward callbacks are still unavailable. To get rewards callbacks, please add listener to get rewards callback according to status listener section in Advanced Features.</spen><p>
<p><spen style="color:red;">2.A new request for the next ad will be sent after the previous one has been closed or previous request has failed.</spen><p>
<p><spen style="color:red;">3.Method media.requestYumiMedia() needs to be called only once at the beginning.</spen><p>

**Implement in Activity lifecycle:**

```java
@Override
protected void onDestroy() {
    if (media != null) {
    	media.onDestory();
    }
    super.onDestroy();
}
```

 

### Splash

**Add the following code in method onCreate of Activity:**

```java
//Create splash object. 
//activity used to show splash
//SlotID AppID assigned by the platform
// container:ad container
// width/height:width and height of ad container
// SplashADListener:ad callback listener
splashAD = new SplashAD(activity， SlotID， container， adwidth， adheight， SplashADListener); 
```

**Implement in Activity lifecycle:**

 ```
@Override
protected void onDestroy() {
    if (splashAD != null) {
    	splashAD.onDestory();
    }
    super.onDestroy();
}
 ```



### Native 

**Add the following code in method onCreate of Activity:**

```java
// Create a native ad, yid is Yumi ID for Yumi background
YumiNative nativeAd = new YumiNative(this, yid);
// set Channel ID 
nativeAd.setChannelID(channelStr);
//set version
nativeAd.setVersionName(versionStr);
// set callback interface of native ad.
nativeAd.setNativeEventListener(new IYumiNativeListener()
{
@Override
public void onLayerPrepared(int adCount) 
{
        // callback of request success, adCount refers to returned ad quantity
}
@Override
public void onLayerFailed(LayerErrorCode error)
{
        // callback of request error, the error is notification of request failure
}
@Override
public void onLayerClick() {
    // callback of  AD Click
}
});
// request ad, the result of success or error will be returned in callback interface
nativeAd.requestYumiNative(); 
```
<spen style="color:red;">
Note: ChannelID refers to the channel labeling of the application, and the YUMI platform can carry out data statistics and effect analysis according to the ChannelID. A Popstar! For example, when the game is released to the SamSung channel, setChannelID(channelStr) needs to be set to setChannelID(' SamSung ').
The channelID is labeled as the YUMI platform to generate the information and cannot be modified at will：
</spen>

| **Channel name** | **ChannelID** |
| ------------ | ------------- |
| SamSung      | SamSung       |



**display native ads, please refer to the following method:**

```java
if (nativeAd.getADCount() > 0))// determine whether the next ad is available through the quantity of remaining ads
{
	final NativeContent content = nativeAd.nextContent();//call next ad
	int type = content.getContentType();//obtain ad types 1. material form 2. layout form
	content.getDesc();//obtain detail description
	content.getIcon_url();//obtain icon url
	content.getImg_url();//obtain image url
	content.getImg_height();//obtain the width of image, default 0 when unavailable
	content.getImg_width();//obtain the height of image, default 0 when unavailable
	content.getJumpUrl();//obtain click jump url
	content.getTitle();//obtain title
	content.getButtonText();//The action language (View details/downloads)
	content.getPrice();//Price (free/$6.3)
	content.getOther();//Other (2017-09-18 update)
}
To ensure your revenue, please call the relevant reporting method at corresponding place.(Important) 
content.reportShow(container,content); // report when impression happens (container refers to parent layout)
content.reportClick(container,content); // report when impression happens (container refers to parent layout)
```


 **Implement in Activity lifecycle:**

```java
@Override
protected void onDestroy()
{
	super.onDestroy();
	if (nativeAd != null)
	{
		nativeAd.onDestroy();
	}
}
```


## Test Mode 

**YUMI SDK provideds a test mode to test your 3rd-party Integrations.** 

<img src="document\image03.png" alt="img3">

**Use Steps:** 

1、Call method to open test page :

YumiSettings.startDebugging(Activity,BannerSlotID,InterstitialSlotID,MediaSlotID); 

If you set the version, channel, according to your need to set the channel in the platform configuration, version call method to open the debug page:

YumiSettings.startDebugging (Activity, BannerSlotID,InterstitialSlotID,MediaSlotID, channelID, versionName);

2、The maize SDK will get the configuration and display the list of the three platforms, and go to the debug page:

  1）&nbsp;The page is displayed as Searching for third party ADnetwork adapters: indicates that no configuration has been made，Please check the configuration of different advertising forms in the application. If the problem is still unsolved, please contact us by email : support@yumimobi.com

<img src="document\image04.jpg" alt="img4" width="200" height="355">

  2）&nbsp;After the advertisement is configured, the normal display configuration platform will be red when it enters the left side for the first time. When a certain platform is properly connected and successfully displayed, the left side will be green.

<img src="document\image05.jpg" alt="img4" width="200" height="355">

3、No matter the status of the left status bar, you can choose a platform to click:

  1）When SDK Available is green, it means that the three-party platform adapter has been added. When it is red, it indicates that the three-party platform adapter has not been added. Go back to Add lib file to check whether the platform adapter has been added

  2）When the Configuration present is green, the three-party platform adapter Manifest is registered. When it is red, it means that the Manifest of the three-party platform adapter component is not registered, which can be returned to Add permission to check whether the platform adapter component has been added

  3）Failed SDK to start or No_fill is green to indicate that the advertisement has been shown successfully. When it is red, it means that the advertisement has not been shown successfully. You can proceed to the next step. If all the steps are not red after completion, please email us at : support@yumimobi.com

<img src="document\image06.jpg" alt="img4" width="200" height="355">

4、Demand Available? Click Fetch to request ad. A fetch will generate an ad download.  You will get a text response confirming the ad download, or instead an error.

5、Ad Displayed? Click Show to display an ad.  When ad shows properly, all test elements will turn green, which indicates this platform has been integrated successfully, at least for that ad type.

Below is a sample screen of some banner ads that were fetched from Baidu ad network and displayed.  The HIDE button simple allows the developer to test the SDK’s hide feature.

<img src="document\image07.jpg" alt="img4" width="200" height="355">

6、The debugging mode must be completed before releasing the App.



## Advanced Features 

### Banner

**Set ad status listener**

If you need to listen to the lifecycle of banner ads, please call the following method after you create YumiBanner object:

```java
// Set ad status listener.
banner.setBannerEventListener(bannerListener);
```

Regarding ad listener, you can instantiate an IYumiBannerListener, and add your own logic according to the callback. The callbacks are shown below: 

| method | explain |
| ------------------------------------------------ | ------------------------------------------------------------ |
| onBannerPreparedFailed(LayerErrorCode errorCode) | Callback when caching fails. Reasons can be found through errorCode.getMsg()|
| onBannerPrepared()                               | Callback when caching succeeds.                                     |
| onBannerExposure()                               | Callback when impression succeeds.                                         |
| onBannerClosed()                                 | Callback when Banner is closed.                                             |
| onBannerClicked()                                | Callback when Banner is clicked.                                             |

**Sample**

```java
// Created banner ad status listener.
bannerListener = new IYumiBannerListener() {
    @Override
    public void onBannerPreparedFailed(LayerErrorCode errorCode) {
        // Callback when caching fails. Reasons can be found through errorCode.getMsg()
    }
    @Override
    public void onBannerPrepared() {
        //Callback when caching succeeds.
    }
    @Override
    public void onBannerExposure() {
        //Callback when impression succeeds.
    }
    @Override
    public void onBannerClosed() {
        //Callback when Banner is closed.
    }
    @Override
    public void onBannerClicked() {
        //Callback when Banner is clicked.
    }
};
```



 **Show and hide banner**

```java
//Hide banner, pause rotation at the same time.
banner.dismissBanner();
//Resume to show banner, resume rotation at the same time.
banner.resumeBanner();
```

**<span id="isMatchWindowWidth">Banner ads automatically adapt to screen size</span>**

<img src="document\image02.png" alt="img2">

When you set banner ad container, you can use isMatchWindowWidth, a parameter of boolean type provided by YUMI SDK. This parameter indicates if banner width has populated full screen. When it’s true, banner width equals the screen width.



### Interstitial 

**Interstitial**

If you need to listen to the lifecycle of interstitial ads, please call the following method after you create YumiBanner object:

```java
// Set ad status listener.
interstitial.setInterstitialEventListener(interstitialListener);
```

Regarding ad listener, you can instantiate an IYumiInterstitialListener, and add your own logic according to the callback. The callbacks are shown below: 

| method | explain |
| ------------------------------------------------ | ------------------------------------------------------------ |
| onInterstitialPreparedFailed(LayerErrorCode error) | Callback when caching fails. Reason can be found through errorCode.getMsg()|
| onInterstitialPrepared()                           | Callback when loading succeeds. Note: please do not call show interstitial method in this callback. |
| onInterstitialExposure()                           | Callback when impression succeeds.                                         |
| onInterstitialExposureFailed()                     | Callback when impression fails.                                         |
| onInterstitialClosed()                             | Callback when interstitial is closed.                                             |
| onInterstitialClicked()                            | Callback when interstitial is clicked.                                             |

**Sample**

```java
// Set ad status listener.
interstitialListener = new IYumiInterstititalListener() {
    @Override
    public void onInterstitialPreparedFailed(LayerErrorCode error) {
        // Callback when loading fails. Reason can be found through errorCode.getMsg()
    }
    @Override
    public void onInterstitialPrepared() {
        // Callback when loading succeeds.
    }
    @Override
    public void onInterstitialExposure() {
        // Callback when impression succeeds.
    }
    @Override
    public void onInterstitialExposureFailed () {
        // Callback when impression failed.			
    }
    @Override
    public void onInterstitialClosed() {
        // Callback when interstitial is closed.
    }
    @Override
    public void onInterstitialClicked() {
        //Callback when interstitial is clicked.
    }
};
```

 

### Rewarded Video

**Set ad status listener**

If you need to listen to the lifecycle of video ads, please call the following method after you create YumiBanner object:

```java
// Set ad status listener.
media.setMediaEventListner(mediaListener);
```

Regarding ad listener, you can instantiate an IYumiMediaListener, and add your own logic according to the callback. The callbacks are shown below:

| method | explain |
| ------------------------------------------------ | ------------------------------------------------------------ |
| onMediaExposure()   | Callback when impression succeeds                                     |
| onMediaClosed()     | Callback when rewarded video is closed                                         |
| onMediaClicked()    | Callback when rewarded video is clicked. Note: this does not guarantee 100% callback due to platform differences.                                         |
| onMediaIncentived() | Callback for rewards after rewarded video has been played completely. Note: if the video has not been played completely, this callback won’t be used. In addition, this method is always triggered before onInterstitialClosed(). |

**Sample**

```java
// Set ad status listener.
mediaListener = new IYumiMediaListener() {
    @Override
    public void onMediaIncentived() {
        //Callback when reward is obtained
    }
    @Override
    public void onMediaExposure() {
        // Callback when impression succeeds.
    }
    @Override
    public void onMediaClosed() {
        //Callback when interstitial is closed.					
    }
    @Override
    public void onMediaClicked() {
        //Callback when interstitial is clicked.
    }
};
```



### Splash

**Set ad status listener**

Regarding ad listener, you can instantiate a SplashADListener, and add your own logic according to the callback. The callbacks are shown below:

| method | explain |
| ------------------------------------------------ | ------------------------------------------------------------ |
| onSplashShow()   | Callback when splash is shown     |
| onSplashClose()  | Callback when splash is closed     |
| onSplashClick()  | Callback when splash is clicked     |
| onSplashFailed() | Callback when loading fails |

**Sample**

```java
// Set ad status listener.
splashListener = new SplashADListener () {
    @Override
    public void onSplashShow () {
        //Callback when splash is shown	
    }
    @Override
    public void onSplashFailed () {
        //Callback when caching failed
    }
    @Override
    public void onSplashClose () {
        //Callback when splash is closed			
    }
    @Override
    public void onSplashClick () {
        //Callback when splash is clicked
    }
};
```


### Proguard

If you are using Proguard add the following to your Proguard config file: 

```c
-keepattributes Exceptions，InnerClasses，Signature，Deprecated，SourceFile，LineNumberTable，*Annotation*，Synthetic，EnclosingMethod
-keep class com.yumi.android.sdk.ads.** { *;}
-keep class com.yumi.android.sdk.ads.self.**{*;}
-keep class com.yumi.android.sdk.ads.selfmedia.**{*;}
```

## Reminder

### Permissions for Android 6.0 and newer versions

When the targetSdkVersion of your app is 23 or above, you can choose the following method to check permission and prompt for user authorization.
<p><spen style="color:red;">Note: The default setting for this method is false, it won’t prompt for user authorisation or causing crash. If set as true, it will check permission and prompt for user authorisation with popups. This method should be called before the instantiated ads and android-support-v4.jar needs to be added.</spen></p>

```java
YumiSettings.runInCheckPermission(true);
```
