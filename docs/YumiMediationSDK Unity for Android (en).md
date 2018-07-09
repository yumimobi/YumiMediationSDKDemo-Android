# YumiMediationSDK Unity for Android 

1. [OVERVIEW](#overview)
    1. [Introduction](#OVERVIEW)
	2. [Development Environment](#development-environment)
2. [DOWNLOADS REQUIRED](#downloads-required)
	1. [SDK Download](#sdk-download)
	2. [Third-Party SDK Download](#third-party-sdk-download)
3. [Integration](#integration)
	1. [Add resource files](#add-resource-files)
	2. [Configuring Access to Ads](#configuring-access-to-ads)
	3. [Log and Toast](#log-and-toast)
	4. [Testing Platform Integrations](#testing-platform-integrations)
	5. [Obfuscation](#obfuscation)
4. [Compilation](#compilation)
5. [Conclusion](#conclusion)
6. [Exhibit A](#exhibit-a)
	1. [Using your own AndroidManifest.xml file](#using-your-own-androidmanifestxml-file)
	2. [Permissions for Android 6.0 and newer versions](#permissions-for-android-60-and-newer-versions)
7. [Exhibit B](#exhibit-b)
	1. [Banner Options](#banner-options)
	2. [Interstitial Options](#interstitial-options)
	3. [Rewarded Video Options](#rewarded-video-options)

## OVERVIEW

### Introduction

This document is designed to guide and assist developers in the integration of the YUMI Unity 3D SDK into their Unity3D Android products.  Successful integration and activation will make the full range of Yumi ad monetization services available to the product worldwide, or in specific geos such as China (if so required).  You also can control which ad networks get installed for mediation, and have a variety of other features available to help your product earn more ad revenue quickly.<br>

**Special note:** the Yumi approach to mediation is unique in that the ad networks chosen each use the Yumi account for, and not that of the developer (if any).  This fact benefits develoeprs in that it can shorten and simplify the integration requirements.  As Yumi consolidates and markets its own ZPLAY inventory with that of its select working partners, this can also increase revenue performance for the developer.  The benefit does not stop there though, as we consolidate more than just the reporting using this approach.  All payments from these ad newtorks come together in one place, and get paid at one time, often paying you even faster than we get paid.  Great revenue possibilities, simplified 
As the above approach implies, Yumi is a full service ad monetization solution.  You will be assigned an operations rep and a technical support rep to help you get started.    When the time comes to get started on integration, simply email onboarding@yumimobi.com and we will let you know who will be working with you to assist in getting your integration complete and functional.  We can even help in making suggestions about what ads to use where, and how often.  We are truly your partner for success!

### Development Environment

   OS：  Windows， Mac， Linux <br>
   Android SDK：&ensp;&gt;&ensp;4.4&ensp;(API level 19)<br>
   IDE：Unity 5

## DOWNLOADS REQUIRED

### SDK Download

Our main SDK and all 3rd party SDKs are available through registering and logging in to our website https://www.yumimobi.com.  Download the SDK package here or contact us at support@yumimobi.com and we can assist you in setting up your account to get started.   

<img src="document_unity\image01.png" alt="img1">

### Third-Party SDK Download

Your Yumi operation rep may suggest the most appropriate ad networks to integrate for your app or game, but the final decision is yours.  Choose the third-party platforms which you would like to integrate into the Yumi SDK jar package (the various package names clearly identify each ad network available).

<img src="document_unity\image02.png" alt="img2">

## Integration

### Add resource files

Everyone needs this first party Yumi resource file.  Import 
resource\YumiMobi_UnityPlugin_Android.unitypackage to the project.  For a manual import option, please see Exhibit A.

<img src="document_unity\image03.png" alt="img3">

When the application needs to be published to the googlePlay platform，Assest / plugins / Android does not have information about googlePlayServices related to Jar or aar files， import YumiMobi_UnityPLugin_GooglePlayServices.unitypackage

<img src="document_unity\image04.png" alt="img4">

When Assest / plugins / Android does not have support-v7 or support-v4 related jar or aar files，copy the following files to the ../Assest/plugins/Android folder

<img src="document_unity\image05.png" alt="img5">

When the application needs to be add to the mraid ad. copy the following files to the../Assest/plugins/Android 

<img src="document_unity\image06.png" alt="img6">

<a href="https://www.iab.com/guidelines/mobile-rich-media-ad-interface-definitions-mraid/">MRAID, or “Mobile Rich Media Ad Interface Definitions,” is the common API (Application Programming Interface) for mobile rich media ads that will run in mobile apps.</a>

Add any third-party SDK adapter resources for the Ad Networks previously chosen: The third-party adapters (yumi_adapter_******_v*.*.*.jar) within adapter folder should be added under \Assets\plugins\Android.

### Configuring Access to Ads

**Environment Configuration**

1) Click MakeZplayPrefab / MakeZplayYUMIPrefab to generate ZplayYUMIHelper prefab to Hietarchy，This prefab will follow all the scenes

<img src="document_unity\image07.png" alt="img7">

2) Once the above steps are complete, set your slot ID, channel number, version number (channel number and version number as non-mandatory) within the ZplayYUMIHelper class.

<img src="document_unity\image08.png" alt="img8">

3) ZplayYUMIHelper class Start () method The body has the following code

<img src="document_unity\image09.png" alt="img9">

initMedia(); // Initialize video，note if you don't need video <br>
InitIterstitialAD() ;// Initialize InitIterstitial ，note if you don't need InitIterstitial<br>
ZplayLogger.setDebug(false); // log，true to display the log, false does not display logs<br>

**Banner, Interstitial & Rewarded Video Ad Access**

STANDARD PROCESS <br>
These code calls generate and control ads.  See notes below for additional details.

| Action | Banner           | Interstitial          | Rewarded Video |
| ------ | ---------------- | --------------------- | -------------- |
| Show   | ShowBannel();    | ShowInterstitialAD(); | ShowMedia();   |
| Hide   | DismissBanner(); |                       |                |
| Unhide | ResumeBanner();  |                       |                |

Code sample : 
```c#
ZplayYUMIHelper.Instance.ShowBannel(); 
```

**Confirm – Call to confirm whether rewarded video has completed loading or not.**
```c#
yuMiUnityAD.IsMediaPrepared(gameObject.name)
```
Load completed function call logic In the ZplayYUMIHelperupdade update，as follows：

<img src="document_unity\image11.png" alt="img11">

Call IsMediaPrepared() to determine whether video has been loaded. It is recommended to request every five seconds.  After loading completed, please set GetRotaIsMediaPrepared to true and suspend calling IsMediaPrepared(). 

<img src="document_unity\image12.png" alt="img12">

**Turn on polling in the video ad closure callback to load the video again**

<img src="document_unity\image13.png" alt="img13">


### Log and Toast

The Android Log output method provided for the Unity plugin is as follows.  Developers are strong encouraged to use it for efficient debugging.
```c#
ZplayLogger.Log("********");
```

### Testing Platform Integrations

Here, developers can test a) initialization of the various adapters, b) the configuration of the Yumi and other ad network account setups, and c) the availability and response to ad calls.  Testing is accomplished in the Yumimobi SDK debug mode.  The following image shows the process of drilling down from the network level to the ad level of testing.  The debugging process is outlined in more detail afterwards.

<img src="document_unity\image14.png" alt="img14">

**Debugging Steps:**

1) To initialize the debugging feature, call:
```c#
ZplayYUMIHelper.Instance.StartDebugging();
```

2) Debugging Home Screen – After basic account configuration on our website, and download of the SDK, all of the platforms should be listed on this first screen, initially in red. The red markers will turn green once a platform is properly integrated and (at least one ad type) is shown successfully within the debugging program.  If none or only some are shown, please check with your operations rep for assistance.

<img src="document_unity\image16.jpg" alt="img16" width="200" height="355">

Each platform line can be clicked to enter and investigate the various layers of testing available.  The following items can be tested within the second screen for each platform shown on the debugging home screen.<br>

a) Adapter SDK Present? When this first line is green, it means that the platform adapter has been added; when in red, it means that the adapter has not yet been added.  If red, please review section 3.1-2 and check for or add any missing adapter. 

b) Adapter Configuration Correct?  When green, it means the platform adapter has been properly registered in the Manifest; when in red, it means that the adapter has not been added, is incomplete, or contains errors.  Please see section 3.1.3, Exhibit B (if needed) along with any custom integration content generated on our website when the account was first configured.  The platform adapter component content is essential.

c) Ad Calls Initialized?  You need to fetch an ad to test this line.  When green, it means the ad platform was reached and an ad has been shown successfully; when in red, it means that the ad call was not successfully initialized or that there was no demand available from the platform for that request. Please email us if assistance required here.

<img src="document_unity\image17.jpg" alt="img17" width="200" height="355">

d) Demand Available? Click Fetch to request ad. A fetch will generate an ad download.  You will get a text response confirming the ad download, or instead an error.

e) Ad Displayed? Click Show to display an ad.  When ad shows properly, all test elements will turn green, which indicates this platform has been integrated successfully, at least for that ad type.
Below is a sample screen of some banner ads that were fetched from Baidu ad network and displayed.  The HIDE button simple allows the developer to test the SDK’s hide feature.

<img src="document_unity\image18.jpg" alt="img18" width="200" height="355">

f) The debugging mode must be completed before releasing the App.

###  Obfuscation

If an obfuscated compilation is required for your project, please add the following items..
```c
-keepattributes Exceptions，InnerClasses，Signature，Deprecated，SourceFile，LineNumberTable，*Annotation*，Synthetic，EnclosingMethod
-keep class com.yumi.android.sdk.ads.** { *;}
-keep class com.yumi.android.sdk.ads.self.**{*;}
-keep class com.yumi.android.sdk.ads.selfmedia.**{*;}
```

## Compilation

To make a build of your game complete with the integrated Yumi SDK, please do the following steps:

1) Click File, choose Build & Run to compile, and complete the build and run screen that follows as needed.

<img src="document_unity\image19.png" alt="img19" width="240" height="260">
<img src="document_unity\image20.png" alt="img20" width="600" height="650">

2) Screen Orientation.  While many games force a specific orientation, some allow for either portrait or landscape.  Yumi, however, requires you to pick a default. The screen orientation in Unity3D shall also need to match the settings of AndroidManifest.xml file, as a conflict may lead to crash after starting application. The following image shows where this is set in the build process.

<img src="document_unity\image21.png" alt="img21" width="600" height="650">

3) Click【Build And Run】on the main screen and wait.

<img src="document_unity\image22.png" alt="img22" width="600" height="120">

## Conclusion

This concludes our integration document.  We hope your project has gone smoothly, and we wish you well in monetizing your game with ads.  Please don’t hesitate to reach out to your operations or technical support contact if you need any assistance in solving integration problems.  We also value and welcome your feedback on this document and for the integration process in general.  Thank you!

## Exhibit A

### Using your own AndroidManifest.xml file

To use your own file, please do the following:<br>
(1) Adding this permission (optional) will generally improve the ad fill rate.
```xml
<!-- yumi sdk start -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
<!--Below are the permissions required for MRAID advertising-->
<uses-permission android:name="android.permission.CALL_PHONE"/>
<uses-permission android:name="android.permission.SEND_SMS"/>
<uses-permission android:name="android.permission.WRITE_CALENDAR"/>
<!-- yumi sdk end -->
```

(2) Note: The third-party platform adapter component registration details will be generated on our website automatically when you choose which Ad Networks you intend to work with.<a href="https://github.com/yumimobi/YumiMediationSDKDemo-Android/blob/master/docs/YumiMediationSDK%20Unity%20-%20Mediation%20List(en)%20.md">《YumiMediationSDK Unity - Mediation List》</a>

### Permissions for Android 6.0 and newer versions

Caution: If app targetSdkVersion is 23 or above, you will need to call the following permission check code.   If the user has not previously provided permission, we will need to prompt user authorization via a popup.  This process needs to be called before instantiating an ad.  The android-support-v4.jar also will need to be added prior.
```c#
YumiUnityAdUtils.CheckPermission();
```

## Exhibit B

**Options for Banner/Interstitial/Rewarded Video Access**

###  Banner Options

a) Banner callback: The following callback messages are available to developers that would like to track or take certain game control actions (pause, unpause, etc.) conditional on these various ad status conditions.  To use this option, inherit Assets.YumiUnityAdUtils.BannerAdCallbackListener [check] interface and use any of the following callback codes.

|Method|Describe|
| ------------------------------------- | ------------------------------------------------------------ |
| onBannerPreparedFailed(string   data) | callback when banner loading   failed, get failure reason via data. |
| onBannerPrepared(string   data)       | callback when banner loading   success.                      |
| onBannerExposure(string   data)       | callback when banner show   success.                         |
| onBannerClosed(string   data)         | callback   when banner closed.                               |
| onBannerClicked(string   data)        | callback   when banner clicked.                              |

Sample:
```c#
#region Banner Callback
//callback method when banner loading failed
public void onBannerPreparedFailed(string data)
{ 
   ZplayLogger.Log("onBannerPreparedFailed"); 
}
//callback method when banner loading success
public void onBannerPrepared(string data)
{
   ZplayLogger.Log("onBannerPrepared"); 
}
//callback when banner show success
public void onBannerExposure(string data)
{
   ZplayLogger.Log("onBannerExposure"); 
}
//callback method of banner close event
public void onBannerClosed(string data)
{
   ZplayLogger.Log("onBannerClosed"); 
}
//callback of  banner click event
public void onBannerClicked(string data)
{
   ZplayLogger.Log("onBannerClicked"); 
}
#endregion
```

b) Banner width controls: The parameter status of isMatchWindowWidth can be used to autoexpand the banner width to the screen width, or to leave it in its default size.  We recommend using the default “false” status (do not expand) to improve ad display performance.  See below for the code to utilize, and also to see examples of the two banner display possibilities.

```c#
YumiUnityAdUtils.AddBannerAd(gameObject.name, isMatchWindowWidth); 
```

<img src="document_unity\image10.png" alt="img10">

### Interstitial Options

Callbacks: The following callback messages are available to developers that would like to track or take certain game control actions (pause, unpause, etc.) conditional on these various ad status conditions.  To make use of this option, inherit Assets.YumiUnityAdUtils.InterstitialAdCallbackListener interface and use any of the following methods.

|Method|Describe|
| ------------------------------------- | ------------------------------------------------------------ |
| onInterstitialPreparedFailed(string   data) | callback when interstitial   loading failed, get failure reason via data |
| onInterstitialPrepared(string   data)       | callback when interstitial   loading success                 |
| onInterstitialExposure(string   data)       | callback when interstitial show   success                    |
| onInterstitialExposureFailed(string   data) | callback when interstitial show failed                       |
| onInterstitialClosed(string   data)         | callback   when interstitial closed                          |
| onInterstitialClicked(string   data)        | callback   when interstitial clicked                         |

Sample:
```c#
#region InterstitialAd Callback
//callback method when interstitial loading failed
public void onInterstitialPreparedFailed(string data)
{ 
   ZplayLogger.Log("onInterstitialPreparedFailed"); 
}
//callback method when interstitial loading success
public void onInterstitialPrepared(string data)
{ 
   ZplayLogger.Log("onInterstitialPrepared"); 
}
//method when interstitial show success
public void onInterstitialExposure(string data)
{ 
   ZplayLogger.Log("onInterstitialExposure"); 
}
// method when interstitial show failed
public void onInterstitialExposureFailed (string data)
 { 
   ZplayLogger.Log("onInterstitialExposureFailed:"); 
}
//callback of interstitial close event
public void onInterstitialClosed(string data)
{ 
   ZplayLogger.Log("onInterstitialClosed" ); 
}
//callback of interstitial click event
public void onInterstitialClicked(string data)
{ 
   ZplayLogger.Log("onInterstitialClicked:" + data); 
} 
#endregion
```

### Rewarded Video Options

Callbacks: The following callback messages are available to developers that would like to track or take certain game control actions (pause, unpause, etc.) conditional on these various ad status conditions.  To make use of this option, inherit Assets.YumiUnityAdUtils. MediaAdCallbackListener interface and use any of the following methods.

|Method|Describe|
| ------------------------------------- | ------------------------------------------------------------ |
| onMediaExposure(string   data)   | when rewarded video starts   successfully.                   |
| onMediaClosed(string   data)     | callback when rewarded video closed.                         |
| onMediaClicked(string   data)    | callback when rewarded video clicked. Note: this method does not guarantee 100% callback depending on the   platform |
| onMediaIncentived(string   data) | callback when rewarded video play   completed and available to get bonus. Note: If video play not completed,   this callback will not be initiated. Note: this callback will always be   sequenced after an onMediaClosed () callback. |
| onIsMediaPrepared(string   data) | callback   when rewarded video prepared。prepared : data=true。Note：This method is only called once IsMediaPrepared(),this callback will   be initiated |

Sample:
```c#
#region MediaAd Callback
// bonus callback method of rewarded video 
public void onMediaIncentived(string data)
{ 
    ZplayLogger.Log("onMediaIncentived");
}
// callback method of rewarded video show completed
public void onMediaExposure(string data)
{ 
    ZplayLogger.Log("onMediaExposure");
}
// callback method of rewarded video close event
public void onMediaClosed(string data)
{ 
    ZplayLogger.Log("onMediaClosed");
}
// callback method of rewarded video click event
public void onMediaClicked (string data)
{ 
    ZplayLogger.Log("onMediaClicked");
}
// callback method of Prepared
public void onIsMediaPrepared (string data)
{ 
    ZplayLogger.LogError("yumiMobi SDK Media Is Prepared :" + data);
	if (Convert.ToBoolean(data))
	{
	     //Video requests success., stop run IsMediaPrepared
		 ZplayYUMIHelper.Instance.GetRotaIsMediaPrepared = true;
	}
}
#endregion
```

