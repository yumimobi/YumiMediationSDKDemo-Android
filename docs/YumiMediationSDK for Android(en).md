   * [YumiMediationSDK Android](#yumimediationsdk-android)
      * [1. Overview](#1-overview)
      * [2. Development Environment Configuration](#2-development-environment-configuration)
         * [Using Android-studio](#using-android-studio)
         * [Using Eclipse](#using-eclipse)
         * [Optional permission](#optional-permission)
      * [3. Integration](#3-integration)
         * [Banner](#banner)
         * [Interstitial](#interstitial)
         * [Rewarded Video](#rewarded-video)
         * [Splash](#splash)
         * [Native](#native)
      * [4. Test Mode](#4-test-mode)
      * [5. Advanced Features](#5-advanced-features)
         * [Banner](#banner-1)
         * [Interstitial](#interstitial-1)
         * [Rewarded Video](#rewarded-video-1)
         * [Splash](#splash-1)
         * [Proguard](#proguard)
      * [6. Precautions](#6-precautions)
         * [1. Permissions for Android 6.0 and newer versions](#1-permissions-for-android-60-and-newer-versions)
         * [2. Google Play Server 17.0.0 or higher version configuration](#2-google-play-server-1700-or-higher-version-configuration)
         * [3. Android9.0 compatibility considerations](#3-android90-compatibility-considerations)
      * [7. Test Slot ID](#7-test-slot-id)

# YumiMediationSDK Android
	
## 1. Overview

**1.1 Target Readers**

   This document is for Android developers who want to integrate YUMIMOBI advertising SDK into their product.  

**1.2 Development Environment**

   OS：  Windows， Mac， Linux <br />
   Android SDK：&ensp;&gt;&ensp;4.4&ensp;(API level 19)<br />
   IDE： Eclipse with ADT (ADT version 23.0.4)&ensp;&ensp;OR&ensp;&ensp;Android-studio<br />
   Java：&ensp;&gt;&ensp;JDK 7

## 2. Development Environment Configuration


- ### Using Android-studio

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
        maven {
            url 'https://maven.google.com/'
            name 'Google'
        }//Optional,It is required when you import SDKs related to Google Server.
        maven() {
            url "https://dl.bintray.com/yumimobi/thirdparty/"
        }//Optional,If you do not need the ksyun SDK, you can remove the maven url.
        maven {
            url 'http://ad-sdk.oss-cn-beijing.aliyuncs.com/Android'
        }////Optional,If you do not need the Iqzone SDK, you can remove the maven url.
    }
}
//Add dependency in module build. Gradle
dependencies {
    //(*.*.*) Please replace it with the latest SDK version number, example ：3.6.2
    implementation 'com.yumimobi.ads:mediation:*.*.*'
    
｝
```

>[Click here](https://github.com/yumimobi/YumiMediationSDKDemo-Android#Latest&nbsp;Version) get latest version number
> 

- ### Using Eclipse

**Step 1. Add lib file**

>[SDK Download](https://github.com/yumimobi/YumiMediationSDKDemo-Android/blob/master/docs/YumiMediationSDK%20for%20Android%20Download%20Page.md)

All lib files are placed in ..\YumiMobi_SDK_AndroidEclipse_Example\lib in the SDK:

- YumiMobi_Android_vX.X.X.jar

- android-support-v4.jar

- android-support-v7-appcompat.jar

- google_play_service的lib工程

Create libs folder under the root directory of your project,add YumiMobi_Android_vX.X.X.jar into libs.

<img src="document\image01.jpg" alt="img1">

you can choose to or not to add android-support-v4.jar and/or android-support-v7-appcompat.jar into libs according to your needs. You must use the jar file provided by YUMIMOBI when you need to use v4.jar or v7.jar.

<span style="color:red;">
About google_play_service project:  
google_play_service is not mandatory, while some ad platforms need it. YUMIMOBI does not need google_play_service. You need use it as a library  and import it into your project. Also, add the ollowing code in tab &lt;application&gt; of your manifest.xml.
</span>

```xml
<meta-data 
     android：name="com.google.android.gms.version"
     class="kix-line-break"
     android：value="@integer/google_play_services_version" />
```

**Step 2. Add permission**

Add the following permissions in manifest.xml of your project:


```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!--The Googleplay app can be unloaded-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

**Step 3. Registered components**

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
<!—Debugging Activity -->
<activity android:name="com.yumi.android.sdk.ads.mediation.activity.MediationTestActivity" ></activity>
```


- ### Optional permission

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
```

## 3. Integration

- ### Banner
**Creat a ViewGroup as banner container, and add it in Activity. Then call the code below:**

```java
//create YumiBanner object. Activity is the activity which you need to display banner ad. You need to create a SlotID on YUMIMOBI. Auto indicates if the mode is automatic. 
//auto==true  Banner ads automatic rotation
//auto==false  Banner ads manual rotation，call banner.requestYumiBanner() repeatedly to rotate
// If you are using YUMI mediation alone, please enable YUMI ad rotation, set the field as “true”; if you are using YUMI ads in other mediations, to ensure ad performance, please disable YUMI ad rotation, set the field as “false”.
YumiBanner banner = new YumiBanner(activity， "YOUR_SLOT_ID"， auto);
//Set ViewGroup as banner container, set it along with size
// bannerContainer  Your ad container
// AdSize.BANNER_SIZE_AUTO  SDK automatically sets screen size as 320*50 or 728*90
// isMatchWindowWidth Set to false
banner.setBannerContainer(bannerContainer， AdSize.BANNER_SIZE_AUTO， isMatchWindowWidth);
//Set channel according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
banner.setChannelID(channelStr);
// Set version name according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
banner.setVersionName(versionStr);
//Start requesting ads, auto==true  The method needs to be called only once
banner.requestYumiBanner();
```



<span style="color:red;">
Note: ChannelID refers to the channel labeling of the application, and the YUMI platform can carry out data statistics and effect analysis according to the ChannelID. A Popstar! For example, when the game is released to the SamSung channel, setChannelID(channelStr) needs to be set to setChannelID(' SamSung ').
The channelID is labeled as the YUMI platform to generate the information and cannot be modified at will：
</span>

| **Channel name** | **ChannelID** |
| ---------------- | ------------- |
| SamSung          | SamSung       |

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



- ### Interstitial
**Add the following code in onCreate method of Activity:**

```java
//Create YumiInterstitial object. Activity is the one you use to show interstitials, You need to create a SlotID on YUMIMOBI. Auto indicates if the mode is automatic.
//auto==true  Automatically request the next ad, auto mode recommended to ensure ad performance
//auto==false  Auto request disabled, to request please repeatedly call interstitial.requestYumiInterstitial()
// If you are using YUMI Ads, please enable YUMI ad rotation, set the field as “true”.
YumiInterstitial interstitial = new YumiInterstitial(activity， "YOUR_SLOT_ID"， auto);
// Set channel according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
interstitial.setChannelID(channelStr);
// Set version name according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
interstitial.setVersionName(versionStr);
//Start requesting ads, auto==true  The method needs to be called only once
interstitial.requestYumiInterstitial();
```
<span style="color:red;">
Note: ChannelID refers to the channel labeling of the application, and the YUMI platform can carry out data statistics and effect analysis according to the ChannelID. A Popstar! For example, when the game is released to the SamSung channel, setChannelID(channelStr) needs to be set to setChannelID(' SamSung ').
The channelID is labeled as the YUMI platform to generate the information and cannot be modified at will：
</span>

| **Channel name** | **ChannelID** |
| ------------------------ | ------------- |
| SamSung                  | SamSung       |

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

<p><span style="color:red;">Note: In order not to confuse the logic of back key, please make sure to add this method when using interstitial</span></p>



- ### Rewarded Video

**Add following code in onCreate method of Activity:**

```java
// Create YumiInterstitial object. Activity is the one you use to show interstitials, SlotID is the app ID which is assigned to you by the platform.
YumiMedia media = new YumiMedia(activity， "YOUR_SLOT_ID");
// Set channel according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
media.setChannelID(channelStr);
// Set version name according to your settings on the platform, you only need to set it once. Repeated calls are based on the last time you call.
media.setVersionName(versionStr);
//Start requesting ads.
media.requestYumiMedia();
```
<span style="color:red;">
Note: ChannelID refers to the channel labeling of the application, and the YUMI platform can carry out data statistics and effect analysis according to the ChannelID. A Popstar! For example, when the game is released to the SamSung channel, setChannelID(channelStr) needs to be set to setChannelID(' SamSung ').
The channelID is labeled as the YUMI platform to generate the information and cannot be modified at will：
</span>

| **Channel name** | **ChannelID** |
| ------------ | ------------- |
| SamSung      | SamSung       |

**To check if video is available, call the following code:**

```java
if (media != null) {
	media.isMediaPrepared ();
}
```

<p><span style="color:red;">Note: It is recommended to request every five seconds.</span></p>

**When you need to show rewarded video, call the following code:**

```java
if (media != null) {
	media.showMedia();
}
```

<p><span style="color:red;">Note:</span></p>
<p><span style="color:red;">1.Rewarded video is available after the above integration， but reward callbacks are still unavailable. To get rewards callbacks, please add listener to get rewards callback according to status listener section in Advanced Features.</span></p>
<p><span style="color:red;">2.A new request for the next ad will be sent after the previous one has been closed or previous request has failed.</span></p>
<p><span style="color:red;">3.Method media.requestYumiMedia() needs to be called only once at the beginning.</span></p>

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

 

- ### Splash

**Add the following code in method onCreate of Activity:**

```java
//Create splash object. 
//activity used to show splash
//SlotID AppID assigned by the platform
// container:ad container
// width/height:width and height of ad container
// SplashADListener:ad callback listener
SplashAD splashAD = new SplashAD(activity， SlotID， container， adwidth， adheight， SplashADListener); 
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



- ### Native 

**Add the following code in method onCreate of Activity:**

```java
// Create a YumiNativeAdOptions to make additional customizations using the NativeAdOptions object
YumiNativeAdOptions nativeAdOptions = new YumiNativeAdOptions.Builder().build();
// Create a native ad, yid is Yumi ID for Yumi background,nativeAdOptions is make additional customizations
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
// request ad, adCount is request ad number,the result of success or error will be returned in callback interface
int adCount = 1;
nativeAd.requestYumiNative(adCount); 
```
<span style="color:red;">
Note: ChannelID refers to the channel labeling of the application, and the YUMI platform can carry out data statistics and effect analysis according to the ChannelID. A Popstar! For example, when the game is released to the SamSung channel, setChannelID(channelStr) needs to be set to setChannelID(' SamSung ').
The channelID is labeled as the YUMI platform to generate the information and cannot be modified at will：
</span>

| **Channel name** | **ChannelID** |
| ------------ | ------------- |
| SamSung      | SamSung       |

**YumiNativeAdOptions ：**

Native ads allow you to make additional customizations using the YumiNativeAdOptions object. This guide shows you how to use YumiNativeAdOptions.

Setting options:
```java
YumiNativeAdOptions nativeAdOptions = new YumiNativeAdOptions.Builder()
                .setIsDownloadImage(true)// set whether the SDK download image resources
                .setAdChoicesPosition(YumiNativeAdOptions.POSITION_TOP_RIGHT)// set AdChoices view position
                .setAdAttributionPosition(YumiNativeAdOptions.POSITION_TOP_LEFT)// set AdAttribution view position
                .setAdAttributionText("Ad")// set AdAttribution view text
                .setAdAttributionTextColor(Color.argb(255, 255, 255, 255))// set AdAttribution view text color
                .setAdAttributionBackgroundColor(Color.argb(90, 0, 0, 0))// set AdAttribution view background color
                .setAdAttributionTextSize(10)// set AdAttribution view text size
                .setHideAdAttribution(false)// set whether to hide AdAttribution
                .build();
```
* **setIsDownloadImage** Image assets for native ads are returned via instances of NativeContent.Image, which holds a Drawable and a Url. If this option is set to true, the SDK fetches image assets automatically and populates both the Drawable and the Uri for you. If it's set to false, however, the SDK instead populates just the Url field, allowing you to download the actual images at your discretion.Default is true.
* **setAdChoicesPosition** use this property to specify where the AdChoicesView should be placed. Default is YumiNativeAdOptions.POSITION_TOP_RIGHT.
* **setAdAttributionPosition** use this property to specify where the Ad text view should be placed. Default is YumiNativeAdOptions.POSITION_TOP_LEFT.
* **setAdAttributionText** use this property to specify the Ad text. Default is “Ad”.
* **setAdAttributionTextColor** use this property to specify the Ad text color. Default is white.
* **setAdAttributionBackgroundColor** use this property to specify the Ad text background color。Default is gray.
* **setAdAttributionTextSize** use this property to specify the Ad text font size. Default is 10.
* **setHideAdAttribution** use this property to hide the Ad text. Default is display.

Default options：
```java
YumiNativeAdOptions nativeAdOptions = new YumiNativeAdOptions.Builder().build();
```
**Native Ads Show：**

* YumiNativeAdView class：

For the YumiNativeAdView format, there is the corresponding YumiNativeAdView class. This class is a ViewGroup that publishers should use as the root for the YumiNativeAdView. A single YumiNativeAdView corresponds to a single unified native ad. Each view used to display that ad's assets should be a child of the YumiNativeAdView object.

1、The view hierarchy for a unified native ad that uses a LinearLayout to display its asset views might look like this：

```java
<?xml version="1.0" encoding="utf-8"?>
<com.yumi.android.sdk.ads.formats.YumiNativeAdView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:background="#FFFFFF"
    android:minHeight="50dp"
    android:orientation="vertical">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:paddingLeft="20dp"
        android:paddingRight="20dp"
        android:paddingTop="12dp">

        <LinearLayout
            android:orientation="horizontal"
            ...>
            <ImageView
                android:id="@+id/app_icon"
                .../>
            <LinearLayout
                android:orientation="vertical"
                ...>
                <TextView
                    android:id="@+id/headline"
                    .../>
                <RatingBar
                    android:id="@+id/stars"
                     .../>
            </LinearLayout>
        </LinearLayout>
        // Other assets such as image or media view, call to action, etc follow.
        ...
    </LinearLayout>
</LinearLayout>
</com.yumi.android.sdk.ads.formats.YumiNativeAdView>
```
2、Here is an example code snippet that creates a YumiNativeAdView and populates it with a NativeContent：

```java
private void showNativeAd() {
        if (adContentList != null && adContentList.size() > 0) // determine if the ad returned by the native callback onLayerPrepared() interface is empty
        {
            NativeContent content = adContentList.get(0);// git one native ad

            if(content.isExpired()){
                // determine this Native Ad is expired，true :  expired；false ：not expired.
                // if expired，please not show this Native Ad, request new Native Ad
                return;
            }

            // creater native ad Continer view，use to show Native ad
            FrameLayout nativeAdContinerView = (FrameLayout) findViewById(R.id.ll_ad_continer);
            
            // // Assumes that your ad layout is in a file call activity_native_material.xml
            // in the res/layout folder
            YumiNativeAdView adView = (YumiNativeAdView) getLayoutInflater()
                    .inflate(R.layout.activity_native_material, null);

            // Locate the view that will hold the title, set its text, and call the YumiNativeAdView's setTitleViewmethod to register it.
            adView.setTitleView((TextView) adView.findViewById(R.id.headline));

            ...
            // Repeat the above process for the other assets in the YumiNativeAdView using additional view objects (Buttons, ImageViews, etc).
            ...

            // If you want to display a video ad, please register the container（FrameLayout）that displays the video  
           adView.setMediaLayout((FrameLayout) adView.findViewById(R.id.media_content));

           
            // fill the title view using the string asset provided by NativeContent
            if (content.getTitle() != null) {
                ((TextView) adView.getHeadlineView()).setText(content.getTitle());
            }
           
            ...
            // Please follow the above method to fill the content of Icon, Large Picture, Call to Action, etc.
            ...

            // Call the YumiNativeAdView's setNativeAd method to register the NativeContent.

            adView.setNativeAd(content);

            // clean nativeAdContinerView
            nativeAdContinerView.removeAllViews();
            // add adView to nativeAdContinerView
            nativeAdContinerView.addView(adView);
        }
    }
```
3、Let's take a look at the individual tasks：

* before you show your native ads, please determine if native ads are expired：
```java
content.isExpired()
```
| return code | explain     | remarks                                                                             |
| ----------- | ----------- | ----------------------------------------------------------------------------------- |
| true        | expired     | this native ad has expired, showing ads that have expired will not generate revenue |
| false       | not expired | this native ads are valid                                                           |

* Inflate the layout

```java
// Inflate XML layout，Its outermost node is YumiNativeAdView
YumiNativeAdView adView = (YumiNativeAdView) getLayoutInflater()
            .inflate(R.layout.activity_native_material, null);
```

In this example, we're inflating an XML layout that contains views for displaying a unified native ad and then locating a reference to the YumiNativeAdView. 。

* Populate and register the asset views

This sample code locates the view used to display the headline, sets its text using the string asset provided by the ad object, and registers it with the YumiNativeAdView object：

```java
// get title view
TextView title = (TextView) adView.findViewById(R.id.headline)
// Locate the view that will hold the title, set its text, and call the YumiNativeAdView's setTitleViewmethod to register it.
adView.setTitleView(title);
if (content.getTitle() != null) {
// fill the title view using the string asset provided by NativeContent
   ((TextView) adView.getHeadlineView()).setText(content.getTitle());
}
```
This process of locating the view, setting its value, and registering it with the ad view class should be repeated for each of the assets provided by the native ad object that the app will display.

 * Register the native ad object：

This final step registers the native ad object with the view that's responsible for displaying it：

```java
adView.setNativeAd(content);
```

**Native video**
 
 1、If you want to show a video in a native ad, just add the layout of the video container (FrameLayout) in the view when you register the view and pass the container to the SDK.
 
 MediaContent（FrameLayout） can be defined in an XML layout or constructed dynamically. It should be placed within the view hierarchy of a YumiNativeAdView. Apps using a MediaContent don't need to populate it with an asset, but must register it with the YumiNativeAdView like this：
```java
 FrameLayout mediacontent = (FrameLayout) adView.findViewById(R.id.media_content);
 adView.setMediaLayout(mediacontent);
```
The MediaContent is a special View designed to display the main media asset. It has the following behavior：

* If the loaded ad has a video asset, the video is buffered and starts playing inside the mediacontent.

2、The following NativeContent interface can be used to determine whether the current NativeContent object has video material：

```java
content.getHasVideoContent()
```

**YumiNativeAdVideoController**

1、The YumiNativeAdVideoController class is used to retrieve information about video assets. Apps can get a reference to the controller from a NativeContent by calling the getNativeAdVideoController() method：

```java
YumiNativeAdVideoController nativeAdVideoController = content.getNativeAdVideoController();
```
This method always returns a YumiNativeAdVideoController object, even when no video asset is present in the ad。

YumiNativeAdVideoController offers these methods for querying video state：
 *  getAspectRatio() - Returns the aspect ratio of the video (width/height), or zero if no video asset is present。

2、Apps can also use the YumiNativeAdVideoController.YumiVideoLifecycleCallbacks class to get notifications when events occur in the lifecycle of a video asset：

```java
nativeAdVideoController.setVideoLifecycleCallbacks(new YumiNativeAdVideoController.YumiVideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
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


## 4. Test Mode 

**YUMI SDK provideds a test mode to test your 3rd-party Integrations.** 

<img src="document\image10.png" alt="img3">

**Use Steps:** 

1、Call method to open test page :

YumiSettings.startDebugging(Activity,BannerSlotID,InterstitialSlotID,MediaSlotID,NativeSloatID); 

If you set the version, channel, according to your need to set the channel in the platform configuration, version call method to open the debug page:

YumiSettings.startDebugging (Activity, BannerSlotID,InterstitialSlotID,MediaSlotID,NativeSloatID, channelID, versionName);

2、The Yumi SDK will detect the platform accessed in the application and display the acquired platform in the platform list, and go to the debug page:

  1）&nbsp;debug page：

<img src="document\image08.png" alt="img4" width="200" height="355">
 
 debug page explain:

 * If the platform name is not displayed in the platform list，explain that the developer no add  this 
platform

 * If the platform name is green,the Yumi server have configured this platform
 * If the platform name is gray,the Yumi server not have configured this platform

3、If the platform name is gray，click this platform,will show warning：

<img src="document\image09.png" alt="img4" width="200" height="355">

4、If the platform name is green, you can click on this platform to debug:

  1）When SDK Available is green, it means that the three-party platform adapter has been added. When it is red, it indicates that the three-party platform adapter has not been added. Go back to Add lib file to check whether the platform adapter has been added

  2）When the Configuration present is green, the three-party platform adapter Manifest is registered. When it is red, it means that the Manifest of the three-party platform adapter component is not registered, which can be returned to Add permission to check whether the platform adapter component has been added

  3）Failed SDK to start or No_fill is green to indicate that the advertisement has been shown successfully. When it is red, it means that the advertisement has not been shown successfully. You can proceed to the next step. If all the steps are not red after completion, please email us at : support@yumimobi.com

<img src="document\image06.jpg" alt="img4" width="200" height="355">

5、Demand Available? Click Fetch to request ad. A fetch will generate an ad download.  You will get a text response confirming the ad download, or instead an error.

6、Ad Displayed? Click Show to display an ad.  When ad shows properly, all test elements will turn green, which indicates this platform has been integrated successfully, at least for that ad type.

Below is a sample screen of some banner ads that were fetched from Baidu ad network and displayed.  The HIDE button simple allows the developer to test the SDK’s hide feature.

<img src="document\image07.jpg" alt="img4" width="200" height="355">

7、The debugging mode must be completed before releasing the App.



## 5. Advanced Features 

- ### Banner

**Set ad status listener**

If you need to listen to the lifecycle of banner ads, please call the following method after you create YumiBanner object:

```java
// Set ad status listener.
banner.setBannerEventListener(bannerListener);
```

Regarding ad listener, you can instantiate an IYumiBannerListener, and add your own logic according to the callback. The callbacks are shown below: 

| method                                           | explain                                                                      |
| ------------------------------------------------ | ---------------------------------------------------------------------------- |
| onBannerPreparedFailed(LayerErrorCode errorCode) | Callback when caching fails. Reasons can be found through errorCode.getMsg() |
| onBannerPrepared()                               | Callback when caching succeeds.                                              |
| onBannerExposure()                               | Callback when impression succeeds.                                           |
| onBannerClosed()                                 | Callback when Banner is closed.                                              |
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



- ### Interstitial 

**Set ad status listener**

If you need to listen to the lifecycle of interstitial ads, please call the following method after you create YumiBanner object:

```java
// Set ad status listener.
interstitial.setInterstitialEventListener(interstitialListener);
```

Regarding ad listener, you can instantiate an IYumiInterstitialListener, and add your own logic according to the callback. The callbacks are shown below: 

| method                                             | explain                                                                                             |
| -------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| onInterstitialPreparedFailed(LayerErrorCode error) | Callback when caching fails. Reason can be found through errorCode.getMsg()                         |
| onInterstitialPrepared()                           | Callback when loading succeeds. Note: please do not call show interstitial method in this callback. |
| onInterstitialExposure()                           | Callback when impression succeeds.                                                                  |
| onInterstitialExposureFailed()                     | Callback when impression fails.                                                                     |
| onInterstitialClosed()                             | Callback when interstitial is closed.                                                               |
| onInterstitialClicked()                            | Callback when interstitial is clicked.                                                              |

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

 

- ### Rewarded Video

**Set ad status listener**

If you need to listen to the lifecycle of video ads, please call the following method after you create YumiBanner object:

```java
// Set ad status listener.
media.setMediaEventListner(mediaListener);
```

Regarding ad listener, you can instantiate an IYumiMediaListener, and add your own logic according to the callback. The callbacks are shown below:

| method              | explain                                                                                                                                                                                                                            |
| ------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| onMediaExposure()   | Callback when impression succeeds                                                                                                                                                                                                  |
| onMediaClosed()     | Callback when rewarded video is closed                                                                                                                                                                                             |
| onMediaClicked()    | Callback when rewarded video is clicked. Note: this does not guarantee 100% callback due to platform differences.                                                                                                                  |
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



- ### Splash

**Set ad status listener**

Regarding ad listener, you can instantiate a SplashADListener, and add your own logic according to the callback. The callbacks are shown below:

| method           | explain                         |
| ---------------- | ------------------------------- |
| onSplashShow()   | Callback when splash is shown   |
| onSplashClose()  | Callback when splash is closed  |
| onSplashClick()  | Callback when splash is clicked |
| onSplashFailed() | Callback when loading fails     |

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


- ### Proguard

If you are using Proguard add the following to your Proguard config file: 

```c
-keepattributes Exceptions，InnerClasses，Signature，Deprecated，SourceFile，LineNumberTable，*Annotation*，Synthetic，EnclosingMethod
-keep class com.yumi.android.sdk.ads.** { *;}
-keep class com.yumi.android.sdk.ads.self.**{*;}
-keep class com.yumi.android.sdk.ads.selfmedia.**{*;}
-keep class com.playableads.**{*;}
```

## 6. Precautions

### 1. Permissions for Android 6.0 and newer versions

When the targetSdkVersion of your app is 23 or above, you can choose the following method to check permission and prompt for user authorization.
<p><span style="color:red;">Note: The default setting for this method is false, it won’t prompt for user authorisation or causing crash. If set as true, it will check permission and prompt for user authorisation with popups. This method should be called before the instantiated ads and android-support-v4.jar needs to be added.</span></p>

```java
YumiSettings.runInCheckPermission(true);
```


### 2. Google Play Server 17.0.0 or higher version configuration
YumiMediationSDK will use the play-services-ads:17.1.3 to obtain the advertising_Id. You need to add the following configuration to avoid the program crash. The following content is quoted from [Google Developers](https://developers.google.com/ad-manager/mobile-ads-sdk/android/quick-start#update_your_androidmanifestxml)：

Declare that your app is an Ad Manager app by adding the following <meta-data> tag in your AndroidManifest.xml.

```java
<meta-data
     android:name="com.google.android.gms.ads.AD_MANAGER_APP"
     android:value="true" />
```
Important: This step is required as of Google Mobile Ads SDK version 17.0.0. Failure to add this <meta-data> tag results in a crash with the message: "The Google Mobile Ads SDK was initialized incorrectly."

### 3. Android9.0 compatibility considerations 
At present, Mintegral platform the Android SDK does not support Android9.0 or above. If the app crashes above Android9.0, you can solve by the ways below.

1. Set targaetSDKveriosn to 27 or less



## 7. Test Slot ID
 
| Formats              | Slot(Placement) ID                                                                                                                         | Note                                                                                                                                 |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------- |
| Banner                 | uz852t89                                                                                                                                   | Using this test ID, you are able to get test ads which are from YUMI, AdMob, AppLovin, Baidu, IQzone                                                  |
| Interstitial | 56ubk22h | Using this test ID, you are able to get test ads which are form YUMI, AdMob, AppLovin, Baidu, IronSource, InMobi, IQzone, Unity Ads, Vungle, ZPLAYAds |
| Rewarded Video         | ew9hyvl4                                                                                                                                   | Using this test ID, you can get test ads which are from YUMI, AdMob, AppLovin, GDTMob, IronSource, InMobi, IQzone, Unity Ads, Vungle, ZPLAYAds |
| Native                 | dt62rndy                                                                                                                                   | You can get test ads which are from YUMI, AdMob, Baidu, GDTMob, Facebook by using this test ID                                        |
| Splash                 | vv7snvc5                                                                                                                                   | For now, Only YUMI platform returns test ads by use this test ID                                                                             |
