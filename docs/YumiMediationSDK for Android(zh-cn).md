* [YumiMediationSDK Android](#yumimediationsdk-android)
    * [1. 概述](#1-概述)
    * [2. 开发环境配置](#2-开发环境配置)
        * [Android-studio 接入](#android-studio-接入)
        * [Eclipse 接入](#eclipse-接入)
        * [可选权限](#可选权限)
    * [3. 代码集成](#3-代码集成)
        * [横幅](#横幅)
        * [插屏](#插屏)
        * [激励视频](#激励视频)
        * [开屏](#开屏)
        * [原生广告](#原生广告)
    * [4. 调试模式](#4-调试模式)
    * [5. 高级功能](#5-高级功能)
        * [横幅](#横幅-1)
        * [插屏](#插屏-1)
        * [激励视频](#激励视频-1)
        * [开屏](#开屏-1)
        * [混淆](#混淆)
    * [6. 注意事项](#6-注意事项)
        * [1. Android6.0以上系统权限处理](#1-android60以上系统权限处理)
        * [2. Google play Server 17.0.0 版本以上配置](#2-google-play-server-1700-版本以上配置)
        * [3. android 9.0 适配](#3-android-90-适配)
    * [7. 测试广告位](#7-测试广告位)

<<<<<<< HEAD
1. [概述](#1-概述)
2. [开发环境配置](#2-开发环境配置)
	1. [Android-studio 接入](#android-studio-接入)
	2. [Eclipse 接入](#eclipse-接入)
	3. [可选权限](#可选权限)
3. [代码集成](#3-代码集成)
	1. [横幅](#横幅)
	2. [插屏](#插屏)
	3. [激励视频](#激励视频)
	4. [开屏](#开屏)
	5. [原生广告](#原生广告)
4. [调试模式](#4-调试模式)
5. [高级功能](#5-高级功能)
	1. [横幅](#横幅)
	2. [插屏](#插屏)
	3. [激励视频](#激励视频)
	4. [开屏](#开屏)
	5. [混淆](#混淆)
6. [提示](#6-提示)
	1. [Android6.0以上系统权限处理](#android60以上系统权限处理)
7. [TEST ID](#7-TEST-ID)

=======
# YumiMediationSDK Android
>>>>>>> update mediation sdk

## 1. 概述

**1.1 面向人群**

   当前文档面向需要在Android产品中接入玉米移动广告SDK的开发人员。
   
**1.2 开发环境**

   OS：  Windows， Mac， Linux <br />
   Android SDK：&ensp;&gt;&ensp;4.4&ensp;(API level 19)<br />
   IDE： Eclipse with ADT (ADT version 23.0.4)&ensp;&ensp;OR&ensp;&ensp;Android-studio<br />
   Java：&ensp;&gt;&ensp;JDK 7

## 2. 开发环境配置

- ### Android-studio 接入

**添加依赖**

```java
//确认 android studio 的 Project 根目录主 build.gradle 中配置了 jcenter 支持。
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
        }//可选,如果需要导入Google Server相关的SDK时需要添加
        maven() {
            url "https://dl.bintray.com/yumimobi/thirdparty/"
        }//可选,如果需要导入Ksyun(金山云)相关的SDK时需要添加

        maven {
            url 'http://ad-sdk.oss-cn-beijing.aliyuncs.com/Android'
        }//可选,如果需要导入Iqzone相关的SDK时需要添加
    }
}
//在 module 的 build.gradle 中添加依赖
dependencies {
    //(*.*.*) 请替换为最新的SDK版本号，如：3.6.0
    implementation 'com.yumimobi.ads:mediation:*.*.*'
｝
```

>最新版本号，请[查看](https://github.com/yumimobi/YumiMediationSDKDemo-Android#latest-version)
> 
>富媒体广告，指的是MRAID，即Mobile Rich Media Ad Interface Definitions，是由IAB编写的用于移动流量上富媒体广告展示的接口规范。详细信息请[查看](https://www.iab.com/guidelines/mobile-rich-media-ad-interface-definitions-mraid/)

- ### Eclipse 接入

**第一步：添加lib文件：**

>[SDK 下载列表](https://github.com/yumimobi/YumiMediationSDKDemo-Android/blob/master/docs/YumiMediationSDK%20for%20Android%20Download%20Page.md)

玉米移动广告需要的lib文件均放在 ..\YumiMobi_SDK_AndroidEclipse_Example\lib 文件夹下：

- YumiMobi_Android_vX.X.X.jar

- Yumi_Adapter_Mraid_vX.X.X.jar

- android-support-v4.jar

- android-support-v7-appcompat.jar

- google_play_service的lib工程

使用时在工程的根目录下创建libs文件夹，将YumiMobi_Android_vX.X.X.jar添加到创建好的libs文件中。

<img src="document\image01.jpg" alt="img1">

如希望支持富媒体广告，请将Yumi_Adapter_Mraid_vX.X.X.jar添加到创建好的libs文件中。
可以视需求添加android-support-v4.jar、android-support-v7-appcompat.jar到libs文件中，需要用到V4或V7包时必须使用我们提供的jar。

<span style="color:red;">关于google_play_service工程：
google_play_service工程非必加，部分平台广告需要google_play_service支持，玉米移动广告不需要添加。使用时需要将此工程作为library工程， 添加到您的工程中。并在manifest.xml文件的&lt;application&gt;
标签内增加以下代码：</span>

```xml
<meta-data 
     android：name="com.google.android.gms.version"
     class="kix-line-break"
     android：value="@integer/google_play_services_version" />
```

**第二步：添加权限**

如以jar包方式接入SDK，请在工程中的manifest.xml中添加以下权限

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!--此权限受Android系统限制，若无此权限可能导致部分机型对下载类广告无法直接下载，国内渠道必须添加，Googleplay（一般为直接跳转型广告）可不加-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

**第三步：注册组件**

如以jar包方式接入SDK，请在工程中的manifest.xml文件中添加：

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


- ### 可选权限


```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
<!--以下为富媒体广告需要的权限-->
<uses-permission android:name="android.permission.CALL_PHONE"/>
<uses-permission android:name="android.permission.SEND_SMS"/>
<uses-permission android:name="android.permission.WRITE_CALENDAR"/>
```


## 3. 代码集成

- ### 横幅
**创建一个ViewGroup作为横幅广告容器，并添加到工程中Activity的适当位置。之后调用如下代码：**

```java
//创建YumiBanner对象. activity是您要展示横幅的activity。 SlotID，您需要通过玉米移动平台创建一个广告位ID以在应用中使用，auto代表使用模式是否是自动。
//auto==true 横幅广告自动轮换
//auto==false 横幅广告手动轮换，再次轮换需要重复调用banner.requestYumiBanner();
//若您在单独使用玉米广告，请开启玉米广告自动轮换，将字段置为true。若您在通过其他聚合工具使用玉米广告，为保证广告效果，请停止玉米广告自动轮换，将字段置为false
YumiBanner banner = new YumiBanner(activity， "YOUR_SLOT_ID"， auto);
//将您创建好的ViewGroup作为banner容器， 同尺寸一并设置
// bannerContainer  您的广告容器
// AdSize.BANNER_SIZE_AUTO  SDK自动根据屏幕设置320*50或728*90
// isMatchWindowWidth 请设置为false
banner.setBannerContainer(bannerContainer， AdSize.BANNER_SIZE_AUTO， isMatchWindowWidth);
//根据您在平台的配置, 设置渠道, 您只需要设置一次渠道. 重复调用以最后一次为准.
banner.setChannelID(channelStr);
//根据您在平台的配置, 设置版本, 您只需要设置一次版本. 重复调用以最后一次为准.
banner.setVersionName(versionStr);
//开始请求广告， auto==true时此方法只需要调用一次
banner.requestYumiBanner();
```



<span style="color:red;">
注：ChannelID是指应用发布的渠道标识，填写后YUMI平台可根据渠道ID进行数据统计和效果分析；以Popstar!消灭星星官网正版为例，当游戏发布到三星渠道时，需要将setChannelID(channelStr)设置为setChannelID(‘SamSung’)。
渠道标识为YUMI平台生成信息，不可随意修改；
</span>

| **渠道名称** | **ChannelID** |
| ------------ | ------------- |
| 三星         | SamSung       |
| 2345手机助手 | 2345shouzhu   |

**在Activity生命周期方法中实现：**

```java
@Override
protected void onDestroy() {
    if (banner != null) {
        banner.onDestory();   //销毁banner对象
    }
	super.onDestroy();
}
```



- ### 插屏
**在Activity的onCreate()方法内添加以下代码：**

```java
//创建YumiInterstitial对象. activity是插屏展示的activity。SlotID，您需要通过玉米移动平台创建一个广告位ID以在应用中使用。auto代表使用模式是否是自动。
//auto==true 插屏广告自动请求下一条，为保证广告效果建议设为自动请求
//auto==false 插屏广告不自动请求下一条，需要重复调用interstitial.requestYumiInterstitial()
//若您在使用玉米广告，为保证广告效果，请开启玉米广告自动轮换，将字段置为true。
YumiInterstitial interstitial = new YumiInterstitial(activity， "YOUR_SLOT_ID"， auto);
//请根据平台的配置, 设置渠道, 您只需要设置一次渠道. 重复调用取最后一次.
interstitial.setChannelID(channelStr);
//情根据平台的配置, 设置版本, 您只需要设置一次版本. 重复调用取最后一次.
interstitial.setVersionName(versionStr);
//开始请求广告， auto==true时此方法只需要调用一次
interstitial.requestYumiInterstitial();
```
<span style="color:red;">
注：ChannelID是指应用发布的渠道标识，填写后YUMI平台可根据渠道ID进行数据统计和效果分析；以Popstar!消灭星星官网正版为例，当游戏发布到三星渠道时，需要将setChannelID(channelStr)设置为setChannelID(‘SamSung’)。
渠道标识为YUMI平台生成信息，不可随意修改；
</span>

| **渠道名称** | **ChannelID** |
| ------------ | ------------- |
| 三星         | SamSung       |
| 2345手机助手 | 2345shouzhu   |

**在需要展现插屏广告的时候，调用以下代码：**

```java
//立即展示  如果有预加载完成的插屏， 立刻显示插屏弹窗. (弹出速度根据机型， 可能有视觉延迟)， 如果没有预加载完成的插屏， 则不展示， 并放弃此次展示机会. 直到下次调用.
if (interstitial != null) {
	interstitial.showInterstitial(false);
}
//延迟展示  如果在您意图展示插屏时， 预加载尚在进行， 延迟展示可以允许SDK等待此次预加载完成， 完成后自动展示插屏. 等待的时间不定.
if (interstitial != null) {
	interstitial.showInterstitial(true);
}
//取消延迟展示   延迟展示时当您需要进行其他操作， 而延迟展示还没进行， 您需要调用以下方法取消此次延迟展示. 取消后， 预加载完成时不会再进行插屏展示. 直到下次调用.
if (interstitial != null) {
	interstitial.cancelInterstitialDelayShown();
}
```

**在Activity生命周期方法中实现：**

```java
@Override
protected void onDestroy() {
    if (interstitial != null) {
    	interstitial .onDestory();
    }
    super.onDestroy();
}
```

因为不同平台的插屏显示弹出不同. 需要在Activity的onBackPressed()方法中添加如下代码：

```java
@Override
public void onBackPressed() {
	if (interstitial.onBackPressed()) {
		return;
	}
	super.onBackPressed();
}
```

<p><span style="color:red;">注意：在使用插屏时，必须增加该方法，避免back键逻辑混乱。</span></p>



- ### 激励视频

**在Activity的onCreate()方法内添加以下代码：**

```java
//创建YumiMedia对象. activity是您要展示插屏的activity。SlotID，您需要通过玉米移动平台创建一个广告位ID以在应用中使用。
YumiMedia media = new YumiMedia(activity， "YOUR_SLOT_ID");
//请根据平台的配置, 设置渠道, 您只需要设置一次渠道. 重复调用取最后一次.
media.setChannelID(channelStr);
//情根据平台的配置, 设置版本, 您只需要设置一次版本. 重复调用取最后一次.
media.setVersionName(versionStr);
//开始请求广告，只需调用一次
media.requestYumiMedia();
```
<span style="color:red;">
注：ChannelID是指应用发布的渠道标识，填写后YUMI平台可根据渠道ID进行数据统计和效果分析；以Popstar!消灭星星官网正版为例，当游戏发布到三星渠道时，需要将setChannelID(channelStr)设置为setChannelID(‘SamSung’)。
渠道标识为YUMI平台生成信息，不可随意修改；
</span>

| **渠道名称** | **ChannelID** |
| ------------ | ------------- |
| 三星         | SamSung       |
| 2345手机助手 | 2345shouzhu   |

**检查视频是否准备完成，调用以下代码：**

```java
if (media != null) {
	media.isMediaPrepared ();
}
```

<p><span style="color:red;">注意：建议调用间隔5秒一次。</span></p>

**请在需要展现视频广告的时候，调用以下代码：**

```java
if (media != null) {
	media.showMedia();
}
```

<p><span style="color:red;">注意：</span><p>
<p><span style="color:red;">1. 完成上述接入后，可以完成基本的视频接入，但是无法获取奖励回调。 请根据高级功能中状态监听部分，增加监听获取奖励回调。</span></p>
<p><span style="color:red;">2. 广告在关闭或请求失败后会自动请求下一条广告。</span></p>
<p><span style="color:red;">3. media.requestYumiMedia() 方法只在一开始调用一次即可。</span></p>

**在Activity生命周期方法中实现：**

```java
@Override
protected void onDestroy() {
    if (media != null) {
    	media.onDestory();
    }
    super.onDestroy();
}
```

 

- ### 开屏

**在Activity的onCreate()内添加以下代码：**

```java
//创建开屏对象. 
//activity：是您要展示插屏的activity，
//SlotID：您需要通过玉米移动平台创建一个广告位ID以在应用中使用。
// container：广告容器
// width/height：广告容器宽高
// SplashADListener：广告回调监听
SplashAD splashAD = new SplashAD(activity， SlotID， container， adwidth， adheight， SplashADListener); 
```

**在Activity生命周期方法中实现：**

 ```
@Override
protected void onDestroy() {
    if (splashAD != null) {
    	splashAD.onDestory();
    }
    super.onDestroy();
}
 ```



- ### 原生广告 

**在 Activity 的 onCreate() 内添加以下代码：**

```java
// 创建一个原生广告选项对象进行其他自定义设置
YumiNativeAdOptions nativeAdOptions = new YumiNativeAdOptions.Builder().build();
// 创建一个原生广告对象，YOUR_SLOT_ID 是在玉米后台申请的广告位 ID, nativeAdOptions 是原生广告的可选自定义设置
YumiNative nativeAd = new YumiNative(this, “YOUR_SLOT_ID”, nativeAdOptions);
// 请根据平台的配置, 设置渠道, 您只需要设置一次渠道. 重复调用取最后一次.
nativeAd.setChannelID(channelStr);
// 情根据平台的配置, 设置版本, 您只需要设置一次版本. 重复调用取最后一次.
nativeAd.setVersionName(versionStr);
// 设置原生广告回调接口
nativeAd.setNativeEventListener(new IYumiNativeListener()
{
    @Override
    public void onLayerPrepared(List<NativeContent> adList) 
    {
        // 请求成功的回调，其中 adList 是返回的广告列表
    }
    @Override
    public void onLayerFailed(LayerErrorCode error)
    {
        // 请求失败的回调，其中 error 是请求失败的错误提示
    }
    @Override
    public void onLayerClick() {
        // 广告点击回调
    }
});
// 请求广告，adCount 参数为请求广告条数,成功或失败的结果会在回调接口中返回
int adCount = 1;
nativeAd.requestYumiNative(adCount); 
```
<span style="color:red;">
注：ChannelID 是指应用发布的渠道标识，填写后YUMI平台可根据渠道 ID 进行数据统计和效果分析；以 Popstar!消灭星星官网正版为例，当游戏发布到三星渠道时，需要将 setChannelID(channelStr) 设置为 setChannelID(‘SamSung’)。
渠道标识为 YUMI 平台生成信息，不可随意修改；
</span>

| **渠道名称** | **ChannelID** |
| ------------ | ------------- |
| 三星         | SamSung       |
| 2345手机助手 | 2345shouzhu   |


**原生广告选项 ：**

原生广告让您可以使用 YumiNativeAdOptions 对象进行其他自定义。本节将介绍如何使用 YumiNativeAdOptions

示例代码:
```java
YumiNativeAdOptions nativeAdOptions = new YumiNativeAdOptions.Builder()
                .setIsDownloadImage(true)// 设置 SDK 是否下载图片资源
                .setAdChoicesPosition(YumiNativeAdOptions.POSITION_TOP_RIGHT)// 设置 AdChoices 组件位置
                .setAdAttributionPosition(YumiNativeAdOptions.POSITION_TOP_LEFT)// 设置 AdAttribution 组件位置
                .setAdAttributionText("广告")// 设置 AdAttribution 组件文字内容
                .setAdAttributionTextColor(Color.argb(255, 255, 255, 255))// 设置 AdAttribution 组件字体颜色
                .setAdAttributionBackgroundColor(Color.argb(90, 0, 0, 0))// 设置AdAttribution 组件字体背景颜
                .setAdAttributionTextSize(10)// 设置 AdAttribution 组件字体大小
                .setHideAdAttribution(false)// 设置是否隐藏 AdAttribution 组件
                .build();
```
* **setIsDownloadImage** 原生广告返回的 Icon 和大图资源为 Image 对象。如果 setIsDownloadImage 设置为 true，则 SDK 会自动获取图片素材资源，并为您填充 Image 对象中的 Drawable, url, scale 属性；如果 setIsDownloadImage 设置为 false, SDK 将不会自动下载 Icon 和大图的图片资源，返回的 Icon 和大图的 Image 对象只会填充 url 属性，从而允许您自行决定是否下载实际图片。默认为 true。
* **setAdChoicesPosition** 使用该属性指定“广告选择”图标应放置的位置。该图标可以显示在广告的任一角，默认为 YumiNativeAdOptions.POSITION_TOP_RIGHT。
* **setAdAttributionPosition** 使用该属性指定广告标识图标应放置的位置。该图标可以显示在广告的任一角，默认为 YumiNativeAdOptions.POSITION_TOP_LEFT。
* **setAdAttributionText** 您可以使用该属性指定广告标识的文案。根据手机语言显示为“广告”或者“Ad”。
* **setAdAttributionTextColor** 使用该属性指定广告标识的文字颜色。默认白色。
* **setAdAttributionBackgroundColor** 使用该属性指定广告标识的背景颜色。默认灰色。
* **setAdAttributionTextSize** 使用该属性指定广告标识的字体大小。默认10。
* **setHideAdAttribution** 使用该属性指定广告标识是否隐藏。默认显示。

如果您不想修改 YumiNativeAdOptions 默认的属性，可以创建一个默认的 YumiNativeAdOptions 对象，示例代码如下：
```java
YumiNativeAdOptions nativeAdOptions = new YumiNativeAdOptions.Builder().build();
```

**原生广告展示：**

* YumiNativeAdView 类：

YumiNativeAdView 类是一个 ViewGroup，开发者应将其用作原生广告的根视图。一个 YumiNativeAdView 对应于一个原生广告。凡是用于展示该广告素材资源的每个视图均应是 YumiNativeAdView 对象的子对象。

1、对于使用 LinearLayout 来展示素材资源视图的统一原生广告，其视图层次结构可能如下所示：

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
2、下面这个示例代码段创建了一个 YumiNativeAdView，然后用 NativeContent 填充该视图：

```java
private void showNativeAd() {
        if (adContentList != null && adContentList.size() > 0) // 判断原生回调 onLayerPrepared() 接口返回的广告列表是否为空
        {
            NativeContent content = adContentList.get(0);// 提取广告对象

            if(content.isExpired()){
                // 判断当前广告是否过期，true : 已过期；false ：未过期。
                // 如果判断为已过期，请不要展示当前广告，请求新的广告
                return;
            }

            // 获取原生广告父容器，用来显示原生广告
            FrameLayout nativeAdContinerView = (FrameLayout) findViewById(R.id.ll_ad_continer);
            
            // 填充一个 XML 布局，它的最外层节点为 YumiNativeAdView
            YumiNativeAdView adView = (YumiNativeAdView) getLayoutInflater()
                    .inflate(R.layout.activity_native_material, null);

            // 将标题视图注册到 YumiNativeAdView 对象中
            adView.setTitleView((TextView) adView.findViewById(R.id.headline));

            ...
            // 请按照上面的方法，将 Icon,大图, 行动号召等视图注册到 YumiNativeAdView 对象中
            ...

            // 如果想显示视频广告，请注册显示视频的容器
            adView.setMediaLayout((FrameLayout) adView.findViewById(R.id.media_content));
           
            // 使用广告对象提供的字符串素材资源，给标题视图填充文字
            if (content.getTitle() != null) {
                ((TextView) adView.getHeadlineView()).setText(content.getTitle());
            }
           
            ...
            // 请按照上面的方法，给 Icon,大图, 行动号召等视图填充内容
            ...

            // 使用 YumiNativeAdView 对象中 setNativeAd 接口注册当前的广告对象
            adView.setNativeAd(content);

            // 确认父容器不包含 ad View
            nativeAdContinerView.removeAllViews();
            // 将 adView 添加到父容器中
            nativeAdContinerView.addView(adView);
        }
    }
```

3、以下是各项具体任务细节：

* 展示原生广告之前请先判断广告是否过期，代码示例如下：
```java
content.isExpired()
```
| 返回值 | 说明   | 备注                           |
| ------ | ------ | ------------------------------ |
| true   | 已过期 | 展示已过期的广告将不会产生收益 |
| false  | 未过期 | 当前广告可以展示               |

* 填充布局

```java
// 填充一个 XML 布局，它的最外层节点为 YumiNativeAdView
YumiNativeAdView adView = (YumiNativeAdView) getLayoutInflater()
            .inflate(R.layout.activity_native_material, null);
```

在本示例中，我们要填充一个 XML 布局（该布局包含的视图用于展示原生广告），然后找到对 YumiNativeAdView 的引用。

* 填充和注册素材资源视图

下面的示例代码会找到用于显示标题的视图，使用广告对象提供的字符串素材资源设置其文字，然后向 YumiNativeAdView 对象注册该视图：

```java
// 获取标题视图
TextView headline = (TextView) adView.findViewById(R.id.headline)
// 调用 YumiNativeAdView 的 setTitleView 接口注册标题视图
adView.setTitleView(title);
if (content.getTitle() != null) {
// 使用广告对象提供的字符串素材资源，给标题视图填充文字
   ((TextView) adView.getHeadlineView()).setText(content.getTitle());
}
```
对于应用要展示的由原生广告对象提供的每项素材资源，都应为其重复上述过程，即找到相应视图、设置其值并向广告视图类注册它。

 * 注册原生广告对象

这是最后一步，也就是向负责显示原生广告对象的视图注册该对象：

```java
adView.setNativeAd(content);
```

**原生视频**
 
 1、如果您想在原生广告中展示视频，仅需要在注册视图时，在视图中预留视频容器（FrameLayout）的位置，将该容器 传入 SDK。 
 
 视频容器可以在 XML 布局中定义，也可以动态构建。就像所有其他素材资源视图一样，应该将其放在 YumiNativeAdView 的视图层次结构中。对于使用视频容器的应用，不需要在其中填充素材资源，但必须向 YumiNativeAdView 注册它，如下所示：
```java
 FrameLayout media_content = (FrameLayout) adView.findViewById(R.id.media_content);
 adView.setMediaLayout(media_content);
```
视频容器是一个专门用于展示主媒体素材资源的 View。它具有以下行为：

* 如果加载的广告具有视频素材资源，则会对视频进行缓冲并将视频播放器放到该容器内播放。

2、通过下面的广告对象提供的接口可以判断当前广告对象是否有视频素材：

```java
content.getHasVideoContent()
```

**YumiNativeAdVideoController**

1、YumiNativeAdVideoController 类用于获取有关视频素材资源的信息。通过调用 getNativeAdVideoController() 方法，应用可以从 NativeContent 获得对该控制器的引用：

```java
YumiNativeAdVideoController nativeAdVideoController = content.getNativeAdVideoController();
```
即使广告中没有视频素材资源，此方法也会始终返回 YumiNativeAdVideoController 对象。

YumiNativeAdVideoController 提供以下视频状态查询方法：
 *  getAspectRatio() - 此方法返回视频的宽高比（宽度/高度），如果没有视频素材资源，则返回零。

2、应用也可以使用 YumiNativeAdVideoController.YumiVideoLifecycleCallbacks() 类，以便在视频素材资源生命周期内发生事件时收到通知：

```java
nativeAdVideoController.setVideoLifecycleCallbacks(new YumiNativeAdVideoController.YumiVideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
```
**在Activity生命周期方法中实现：**

如果不再使用当前原生对象，可以调用nativeAd.onDestroy()方法销毁nativeAd对象，例如可以在Activity的onDestroy()方法里面销毁。

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

## 4. 调试模式 

**玉米广告SDK为开发者提供了一个检测三方平台集成状态的调试模式，如图：** 

<img src="document\image10.png" alt="img3">

**使用步骤：** 

1、调用方法打开调试页面：

YumiSettings.startDebugging(Activity,BannerSlotID,InterstitialSlotID,MediaSlotID,NativeSloatID); 

如果设置了版本、渠道，根据您在平台的配置需要设置渠道、版本调用方法打开调试页面：

YumiSettings.startDebugging (Activity, BannerSlotID,InterstitialSlotID,MediaSlotID, NativeSloatID,channelID, versionName);

2、玉米SDK将检测开发者接入的平台，并将获取到的平台展示在平台列表中，进入debug页面：

  1）&nbsp; debug 页面：

<img src="document\image08.png" alt="img4" width="200" height="355">
  
  debug 页面说明：

* 如果平台名称没有在平台列表中，说明开发者没有接入此平台

* 如果平台名称为绿色，说明服务端配置了当前平台。
* 如果平台名称为灰色，说明服务端未配置当前平台。

3、如果平台名称为灰色，点击此平台，会显示警告：

<img src="document\image09.png" alt="img4" width="200" height="355">


4、如果平台名称为绿色，你可以点击此平台进行调试：

  1）SDK Available 为绿色时表示三方平台适配器已添加；当为红色时表示三方平台适配器未添加，回到文档添加lib文件部分检查该平台adapter是否添加

  2）Configuration present为绿色时表示三方平台适配器组件Manifest已注册；当为红色时表示三方方平台适配器组件Manifest未注册，可回到文档注册组件部分检查该平台适配器组件是否添加

  3）SDK Failed to start or No_fill 为绿色表示广告曾经展示成功过；当为红色时表示还未展示成功过广告， 可继续进行下一个步骤，如果所有步骤完成后仍为红色，请邮件联系我们： support@yumimobi.com

<img src="document\image06.jpg" alt="img4" width="200" height="355">


5、点击Fetch开始请求广告，点击Show展示广告

6、广告展示成功后检查项全部变为绿色，表示该家平台接入成功

<img src="document\image07.jpg" alt="img4" width="200" height="355">

7、应用发布前需要将调试模式注释掉。


## 5. 高级功能 

- ### 横幅

**设置广告状态监听**

如果需要监听横幅广告生命周期，请在创建YumiBanner对象后， 调用如下方法： 

```java
//设置banner状态监听.
banner.setBannerEventListener(bannerListener);
```

关于广告监听，您可以直接实例化一个IYumiBannerListener，并根据回调增加您自己的逻辑。监听有以下回调方法： 

| 方法                                             | 说明                                                          |
| ------------------------------------------------ | ------------------------------------------------------------- |
| onBannerPreparedFailed(LayerErrorCode errorCode) | 当横幅加载失败时回调,可以通过errorCode.getMsg()获得失败原因。 |
| onBannerPrepared()                               | 当横幅加载成功时回调                                          |
| onBannerExposure()                               | 当横幅展示成功时回调                                          |
| onBannerClosed()                                 | 当横幅关闭时回调                                              |
| onBannerClicked()                                | 当横幅点击时回调                                              |

**接入示例：**

```java
//创建banner状态监听
bannerListener = new IYumiBannerListener() {
    @Override
    public void onBannerPreparedFailed(LayerErrorCode errorCode) {
        //当横幅加载失败时回调. 可以通过errorCode.getMsg()获得失败原因.
    }
    @Override
    public void onBannerPrepared() {
        //当横幅加载成功时回调. 
    }
    @Override
    public void onBannerExposure() {
        //当横幅展示成功时回调.
    }
    @Override
    public void onBannerClosed() {
        //当横幅关闭时回调.
    }
    @Override
    public void onBannerClicked() {
        //当横幅点击时回调.
    }
};
```



 **横幅显示和隐藏**

```java
//隐藏横幅， 同时暂停横幅轮换请求
banner.dismissBanner();
//恢复显示横幅， 同时恢复横幅轮换请求
banner.resumeBanner();
```





- ### 插屏 

**设置广告状态监听**

如果您需要监听插屏广告生命周期，请在创建YumiInterstitial对象后，调用如下方法：

```java
//设置插屏状态监听.
interstitial.setInterstitialEventListener(interstitialListener);
```

关于广告监听，您可以直接实例化一个IYumiInterstitialListener，并根据回调增加您自己的逻辑。监听有以下回调方法： 

| 方法                                               | 说明                                                                    |
| -------------------------------------------------- | ----------------------------------------------------------------------- |
| onInterstitialPreparedFailed(LayerErrorCode error) | 当插屏加载失败时回调，可以通过errorCode.getMsg()获得失败原因。          |
| onInterstitialPrepared()                           | 当插屏加载成功时回调。   注意： 请不要直接在此回调中调用插屏的展示方法. |
| onInterstitialExposure()                           | 当插屏展示成功时回调                                                    |
| onInterstitialExposureFailed()                     | 当插屏展示失败时回调                                                    |
| onInterstitialClosed()                             | 当插屏关闭时回调                                                        |
| onInterstitialClicked()                            | 当插屏点击时回调                                                        |

**接入示例：**

```java
//创建插屏状态监听
interstitialListener = new IYumiInterstititalListener() {
    @Override
    public void onInterstitialPreparedFailed(LayerErrorCode error) {
        //当插屏加载失败时回调. 可以通过errorCode.getMsg()获得失败原因.
    }
    @Override
    public void onInterstitialPrepared() {
        //当插屏加载成功时回调.
    }
    @Override
    public void onInterstitialExposure() {
        //当插屏展示成功时回调.
    }
    @Override
    public void onInterstitialExposureFailed () {
        //当插屏展示失败时回调.
    }
    @Override
    public void onInterstitialClosed() {
        //当插屏关闭时回调.
    }
    @Override
    public void onInterstitialClicked() {
        //当插屏点击时回调.
    }
};
```

 

- ### 激励视频

**设置广告状态监听**

如果您需要监听视频广告生命周期，请在创建YumiMedia对象后， 调用如下方法：

```java
//设置视频状态监听.
media.setMediaEventListner(mediaListener);
```

关于广告监听，您可以直接实例化一个IYumiMediaListener，并根据回调增加您自己的逻辑。监听有以下回调方法：

| 方法                | 说明                                                                                                                        |
| ------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| onMediaExposure()   | 当激励视频展示成功时回调                                                                                                    |
| onMediaClosed()     | 当激励视频关闭时回调                                                                                                        |
| onMediaClicked()    | 当激励视频点击时回调                                                                                                        |
| onMediaIncentived() | 当激励视频播放完成， 可以获取奖励时回调。注意： 如果未完成播放，   不会回调此方法. 另外， 此方法在onMediaClosed ()之前触发. |

**接入示例：**

```java
//创建视频状态监听
mediaListener = new IYumiMediaListener() {
    @Override
    public void onMediaIncentived() {
        //当激励视频获得奖励时回调			
    }
    @Override
    public void onMediaExposure() {
        //当激励视频展示成功时回调.
    }
    @Override
    public void onMediaClosed() {
        //当激励视频关闭时回调.				
    }
    @Override
    public void onMediaClicked() {
        //当激励视频点击时回调.
    }
};
```



- ### 开屏

**设置广告状态监听**

关于广告监听，您可以直接实例化一个SplashADListener，并根据回调增加您自己的逻辑。监听有以下回调方法： 

| 方法             | 说明                 |
| ---------------- | -------------------- |
| onSplashShow()   | 当开屏展示时回调     |
| onSplashClose()  | 当开屏关闭时回调     |
| onSplashClick()  | 当开屏点击时回调     |
| onSplashFailed() | 当开屏加载失败时回调 |

**接入示例：**

```java
//创建视频状态监听
splashListener = new SplashADListener () {
    @Override
    public void onSplashShow () {
        //当开屏展示时回调		
    }
    @Override
    public void onSplashFailed () {
        //当开屏加载失败时回调
    }
    @Override
    public void onSplashClose () {
        //当开屏关闭时回调				
    }
    @Override
    public void onSplashClick () {
        //当开屏点击时回调
    }
};
```


- ### 混淆

如果您的工程需要混淆编译， 请在混淆文件内增加以下内容。

```c
-keepattributes Exceptions，InnerClasses，Signature，Deprecated，SourceFile，LineNumberTable，*Annotation*，Synthetic，EnclosingMethod
-keep class com.yumi.android.sdk.ads.** { *;}
-keep class com.yumi.android.sdk.ads.self.**{*;}
-keep class com.yumi.android.sdk.ads.selfmedia.**{*;}
-keep class com.playableads.**{*;}
```

## 6. 注意事项


### 1. Android6.0以上系统权限处理

当您的应用targetSdkVersion为23及以上时，可选择以下方法进行权限检查并且弹窗提示用户授权。
<p><span style="color:red;">注：该方法默认为false， 不会对用户进行权限提示并且不会导致崩溃。设为true，会进行权限检查并且弹窗提示用户授权。该方法在实例化广告之前调用，并且需要添加android-support-v4.jar。</span></p>

```java
YumiSettings.runInCheckPermission(true);
```

### 2. Google play Server 17.0.0 版本以上配置

YumiMediationSDK会通过play-services-ads:17.1.3获取advertising_Id，需要添加如下配置，避免程序崩溃，以下内容引用自[google官方文档](https://developers.google.com/ad-manager/mobile-ads-sdk/android/quick-start#update_your_androidmanifestxml)：

通过在 AndroidManifest.xml 中添加以下 <meta-data> 标记，声明您的应用是 Ad Manager 应用。

```java
<!-- google player service 17.0.0 版本以上必须得添加 start-->
<meta-data
     android:name="com.google.android.gms.ads.AD_MANAGER_APP"
     android:value="true" />
<!-- google player service 17.0.0 版本以上必须得添加 end -->
```
重要提示：自 Google 移动广告 SDK 17.0.0 版本开始，必须执行此步骤。如果未能添加此 <meta-data> 代码，将会导致崩溃，并显示以下消息："The Google Mobile Ads SDK was initialized incorrectly."

### 3. android 9.0 适配
目前一些平台Android SDK暂不支持Android9.0以上操作系统，比如 Mintegral 平台，如果在Android9.0以上系统出现的崩溃，可以通过以下两个方法之一解决。

1. 将targaetSDKveriosn设置为27或者27以下。 

2. 在AndroidManifest.xml中的application标签中添加以下内容： 
```xml
<uses-library android:name="org.apache.http.legacy" android:required="false"/>
```
使用方法 2 可能导致广告加载失败

## 7. 测试广告位
 
| 广告类型               | Slot(Placement) ID                                                                                                                | 备注                                                                                                                               |
| ---------------------- | --------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| Banner                 | uz852t89                                                                                                                          | 使用此test id，可以测试到YUMI、AdMob、AppLovin、Baidu、IQzone等平台的测试广告                                                 |
| Interstitial |  56ubk22h | 使用此test id，可以测试到YUMI、AdMob、AppLovin、Baidu、IronSource、InMobi、IQzone、Unity Ads、Vungle、ZPLAYAds等平台的测试广告 |
| Rewarded Video         | ew9hyvl4                                                                                                                          | YUMI、AdMob、AppLovin、GDTMob、IronSource、InMobi、IQzone、Unity Ads、Vungle、ZPLAYAds等平台的测试广告可以使用此test id进行测试 |
| Native                 | dt62rndy                                                                                                                          | YUMI、AdMob、Baidu、GDTMob、Facebook等平台可以使用此test id进行测试                                        |
| Splash                 | vv7snvc5                                                                                                                          | 使用此test id，只能测试到YUMI平台的测试广告                                                                                                     |
