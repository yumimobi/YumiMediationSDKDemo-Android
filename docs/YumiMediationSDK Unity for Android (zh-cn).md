# YumiMediationSDK Unity for Android 

1. [概述](#概述)
2. [下载](#下载)
	1. [玉米SDK下载](#玉米sdk下载)
	2. [三方SDK下载](#三方sdk下载)
3. [添加资源文件](#添加资源文件)
	1. [添加SDK需要的资源文件](#添加sdk需要的资源文件)
	2. [添加三方平台SDK](#添加三方平台sdk)
	3. [AndroidManifest.xml中权限添加](#androidmanifestxml中权限添加)
	4. [Android6.0以上系统权限处理](#android60以上系统权限处理)
4. [快速接入](#快速接入)
	1. [环境配置](#环境配置)
	2. [横幅广告形式接入](#横幅广告形式接入)
	3. [插屏广告形式接入](#插屏广告形式接入)
	4. [激励视频广告形式接入](#激励视频广告形式接入)
5. [Log](#log)
6. [调试模式](#调试模式)
7. [混淆](#混淆)
8. [编译](#编译)

## 概述

1. 面向人群

   本产品面向需要在Unity产品中接入玉米移动广告Unity3D SDK的开发人员
   
2. 开发环境

   OS：  Windows， Mac， Linux <br>
   Android SDK：&ensp;&gt;&ensp;4.4&ensp;(API level 19)<br>
   IDE：Unity 5

## 下载

### 玉米SDK下载

通过官网下载SDK主包或者联系我们： support@yumimobi.com

<img src="document_unity\image01.png" alt="img1">

### 三方SDK下载

在玉米SDK jar包中选择需要接入的三方平台（三方平台的jar包可以通过名称进行区分），接入工程中

<img src="document_unity\image02.png" alt="img2">


## 添加资源文件

### 添加SDK需要的资源文件

（1） 将resource\YumiMobi_UnityPlugin_Android.unitypackage导入到工程中。如果资源文件跟项目中现有资源文件有冲突，请尽量使用我们提供的资源文件，若存在其他问题可联系我们 support@yumimobi.com ，我们会尽快反馈。资源文件不添加可能导致广告无法正常展示。

<img src="document_unity\image03.png" alt="img3">

（2）当应用需发布到googlePlay平台时，若Assest/plugins/Android中没有关于googlePlayServices相关Jar或者aar文件时，请导入YumiMobi_UnityPLugin_GooglePlayServices.unitypackage

<img src="document_unity\image04.png" alt="img4">

（3）当Assest/plugins/Android 没有support-v7或者support-v4相关jar或者aar文件时，请把以下文件拷贝到../Assest/plugins/Android文件夹中

<img src="document_unity\image05.png" alt="img5">

（4）如希望支持富媒体广告，请将Yumi_Adapter_Mraid_vX.X.X.jar添加到../Assest/plugins/Android文件夹中

<img src="document_unity\image06.png" alt="img6">

<a href="https://www.iab.com/guidelines/mobile-rich-media-ad-interface-definitions-mraid/">MRAID, or “Mobile Rich Media Ad Interface Definitions,” is the common API (Application Programming Interface) for mobile rich media ads that will run in mobile apps.</a>

### 添加三方平台SDK

三方平台 adapter (yumi_adapter_******_v*.*.*.jar) 在adapter文件夹下，如需接入三方平台，可添加到\Assets\plugins\Android下。

### AndroidManifest.xml中权限添加

（1） 可选权限： 可视需求添加到您的Assets\plugins\Android\AndroidManifest.xml文件中。若添加以下权限，有助提高广告填充

```xml
<!--此权限受Android系统限制，若无此权限可能导致部分机型对下载类广告无法直接下载，国内渠道必须添加，Googleplay（一般为直接跳转型广告）可不加-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- yumi sdk start -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
<!--以下为富媒体广告需要的权限-->
<uses-permission android:name="android.permission.CALL_PHONE"/>
<uses-permission android:name="android.permission.SEND_SMS"/>
<uses-permission android:name="android.permission.WRITE_CALENDAR"/>
<!-- yumi sdk end -->
```

（2） 注：三方平台注册组件，请查看文档 <a href="https://github.com/yumimobi/YumiMediationSDKDemo-Android/blob/master/docs/YumiMediationSDK%20Unity%20-%20Mediation%20List(zh-cn)%20.md">《YumiMediationSDK Unity - Mediation List》</a>

### Android6.0以上系统权限处理

当您的应用targetSdkVersion为23及以上时，调用以下方法进行权限检查并且弹窗提示用户授权。该方法在实例化广告之前调用，并且需要添加android-support-v4.jar。

```c#
YumiUnityAdUtils.CheckPermission();
```

## 快速接入

### 环境配置

**1、点击MakeZplayPrefab/MakeZplayYUMIPrefab 生成ZplayYUMIHelper prefab到Hietarchy，此prefab会全程跟随所有场景**

<img src="document_unity\image07.png" alt="img7">

**2、上述步骤完成之后，在ZplayYUMIHelper类内设置您的广告位ID、渠道号、版本号(渠道号和版本号为非必选项)。**

<img src="document_unity\image08.png" alt="img8">

**3、ZplayYUMIHelper类Start () 方法体内有以下代码**

<img src="document_unity\image09.png" alt="img9">

initMedia() //为初始化视频，如不需要视频则要注释掉<br>
InitIterstitialAD() //为初始化插屏 。如不需要插屏则要注释掉<br>
ZplayLogger.setDebug(false) //为log信息类。传true显示日志，false不显示日志<br>

### 横幅广告形式接入

**1、必须接入方法：此方法直接影响广告是否可正常展示**

1). 调用以下方法展示横幅：
```c#
ZplayYUMIHelper.Instance.ShowBannel();
```

2). 调用以下方法隐藏横幅：
```c#
ZplayYUMIHelper.Instance.DismissBanner();
```

3). 调用以下方法恢复展示横幅：
```c#
ZplayYUMIHelper.Instance.ResumeBanner();
```
**2、可选接入方法：回调方法可帮助开发者获取SDK状态； 自适应可提升广告展示效果**

1) 横幅广告回调方法：开发者可根据回调方法获悉SDK状态，并根据自身应用逻辑需要做在此基础上做相关处理。若不接入此方法，不影响广告的展示上报等效果。<br>
横幅广告回调类BannerAdCallbackListener实现了YumiUnityAdUtils.BannerAdCallbackListener 接口，有以下方法：

| 方法 | 说明 |
| ------------------------------------- | ------------------------------------------------ |
| onBannerPreparedFailed(string   data) | 当横幅加载失败时回调，可以通过data获得失败原因。 |
| onBannerPrepared(string   data)       | 当横幅加载成功时回调                             |
| onBannerExposure(string   data)       | 当横幅展示成功时回调                             |
| onBannerClosed(string   data)         | 当横幅关闭时回调                                 |
| onBannerClicked(string   data)        | 当横幅点击时回调                                 |

接入示例：

```c#
#region Banner Callback
//横幅加载失败回调方法
public void onBannerPreparedFailed(string data)
{ 
   ZplayLogger.Log("onBannerPreparedFailed:"); 
}
//横幅加载成功回调方法
public void onBannerPrepared(string data)
{
   ZplayLogger.Log("onBannerPrepared:"); 
}
//横幅展示成功回调方法
public void onBannerExposure(string data)
{
   ZplayLogger.Log("onBannerExposure:"); 
}
//横幅关闭事件回调方法
public void onBannerClosed(string data)
{
   ZplayLogger.Log("onBannerClosed:"); 
}
//横幅点击事件回调方法
public void onBannerClicked(string data)
{
   ZplayLogger.Log("onBannerClicked:"); 
}
#endregion
```

2) 横幅自适应屏幕宽度： 可根据需要修改isMatchWindowWidth参数状态，建议使用默认false状态，提高广告展示效果。

<img src="document_unity\image10.png" alt="img10">

横幅设置广告容器的同时，玉米SDK提供了 boolean 类型的参数：isMatchWindowWidth。可修改boolean 类该参数为横幅宽度是否充满全屏，
当该参数为false时横幅的宽度为容器宽度；当该参数为true时横幅的宽度为屏幕宽度。默认状态为false

```c#
ZplayYUMIHelper.Instance.ShowBannel(true);
```

### 插屏广告形式接入

**1、必须接入方法：此方法直接影响广告是否可正常展示**

调用以下方法展示插屏：

```c#
ZplayYUMIHelper.Instance.ShowInterstitialAD();    
```

**2、可选接入方法：接入以下方法可帮助开发者获取SDK状态**

插屏广告回调方法：开发者可根据回调方法获悉SDK状态，并根据自身应用逻辑需要做在此基础上做相关处理。若不接入此方法，不影响广告的展示上报等效果。<br>
插屏广告回调类InterstitialAdCallbackListener实现了YumiUnityAdUtils.InterstitialAdCallbackListener 接口，有以下方法：

| 方法 | 说明 |
| ------------------------------------- | ------------------------------------------------ |
| onInterstitialPreparedFailed(string   data) | 当插屏加载失败时回调，可以通过data获得失败原因。 |
| onInterstitialPrepared(string   data)       | 当插屏加载成功时回调。                           |
| onInterstitialExposure(string   data)       | 当插屏展示成功时回调                             |
| onInterstitialExposureFailed(string   data) | 当插屏展示失败时回调                             |
| onInterstitialClosed(string   data)         | 当插屏关闭时回调                                 |
| onInterstitialClicked(string   data)        | 当插屏点击时回调                                 |

接入示例：

```C#
#region InterstitialAd Callback
//插屏加载失败回调方法
public void onInterstitialPreparedFailed(string data)
{ 
   ZplayLogger.Log("onInterstitialPreparedFailed:"); 
}
//插屏加载成功回调方法
public void onInterstitialPrepared(string data)
{ 
   ZplayLogger.Log("onInterstitialPrepared:"); 
}
//插屏展示成功回调方法
public void onInterstitialExposure(string data)
{ 
   ZplayLogger.Log("onInterstitialExposure:"); 
}
//插屏展示失败回调方法
public void onInterstitialExposureFailed (string data)
{ 
   ZplayLogger.Log("onInterstitialExposureFailed:"); 
}
//插屏关闭事件回调方法
public void onInterstitialClosed(string data)
{ 
   ZplayLogger.Log("onInterstitialClosed:"); 
}
//插屏点击事件回调方法
public void onInterstitialClicked(string data)
{ 
   ZplayLogger.Log("onInterstitialClicked:"); 
} 
#endregion
```
### 激励视频广告形式接入

**1、必须接入方法：以下方法直接影响广告是否可正常展示**

（1） 调用以下方法询问激励视频是否加载完成：

```C#
yuMiUnityAD.IsMediaPrepared(gameObject.name);
```

此加载完成函数调用逻辑已经提供在ZplayYUMIHelperupdade 类update函数中，详情如下：

<img src="document_unity\image11.png" alt="img11">

调用视频是否加载完成函数IsMediaPrepared()，建议每五秒请求一次。加载成功后GetRotaIsMediaPrepared设置为true，暂停循环调用IsMediaPrepared()函数。

<img src="document_unity\image12.png" alt="img12">

在视频广告关闭回调中再次开启轮询加载视频

<img src="document_unity\image13.png" alt="img13">

（2） 调用以下方法展示激励视频：

```C#
ZplayYUMIHelper.Instance.ShowMedia();
```

（3） 激励视频广告回调方法：<br>

激励视频广告回调类MediaAdCallbackListener实现了Assets.YumiUnityAdUtils.MediaAdCallbackListener接口，有以下方法：

| 方法 | 说明 |
| ------------------------------------- | ------------------------------------------------ |
| onMediaExposure(string   data)   | 当激励视频展示成功时回调                                     |
| onMediaClosed(string   data)     | 当激励视频关闭时回调                                         |
| onMediaClicked(string   data)    | 当激励视频点击时回调。 注意： 根据平台不同， 此方法不保证100%回调 |
| onMediaIncentived(string   data) | 当激励视频播放完成， 可以获取奖励时回调。注意： 如果未完成播放，   不会回调此方法. 另外， 此方法永远在onMediaClosed ()之后触发. |
| onIsMediaPrepared(string   data) | 视频是否准备完成回调。准备完成data=true。注意：此方法只有在调用一次IsMediaPrepared()方法后才会产生一次回调 |

接入示例：

```c#
#region MediaAd Callback
// 激励视频奖励回调方法
public void onMediaIncentived(string data)
{ 
    ZplayLogger.Log("onMediaIncentived" );
}
// 激励视频展示完成回调方法
public void onMediaExposure(string data)
{ 
    ZplayLogger.Log("onMediaExposure");
}
// 激励视频关闭事件回调方法
public void onMediaClosed(string data)
{ 
    ZplayLogger.Log("onMediaClosed");
}
// 激励视频点击事件回调方法
public void onMediaClicked (string data)
{ 
    ZplayLogger.Log("onMediaClicked");
}
// 激励视频准备完成回调方法
public void onIsMediaPrepared (string data)
{ 
    ZplayLogger.LogError("yumiMobi SDK Media Is Prepared:" + data);
	if (Convert.ToBoolean(data))
	{
	     //Video requests success., stop run IsMediaPrepared
		 ZplayYUMIHelper.Instance.GetRotaIsMediaPrepared = true;
	}
}
#endregion
```


## Log

以下是插件提供的Android Log输出方法，开发者可自行判断是否使用。增加此方法可方便查找问题：

```c#
ZplayLogger.Log("********");
```

## 调试模式

**玉米广告SDK为开发者提供了一个检测三方平台集成状态的调试模式，如图：**

<img src="document_unity\image14.png" alt="img14">

可以通过调用以下方法启动调试模式页面。

```c#
ZplayYUMIHelper.Instance.StartDebugging();
```
**使用步骤：**

1、调用方法打开调试页面：ZplayYUMIHelper.Instance.StartDebugging();<br>
2、玉米SDK将获取配置并展示三方平台列表，进入debug页面： <br>
&ensp;&ensp;（1） 页面展示为Searching for third party ADnetwork adapters：表示未进行配置，请检查应用的不同广告形式配置情况，如果问题仍未解决，请通过邮件联系我们： support@yumimobi.com

<img src="document_unity\image15.jpg" alt="img15" width="200" height="355">

&ensp;&ensp;（2） 广告配置后，正常展示配置平台，首次进入左侧所有平台均为红色，当某个平台正确接入并展示成功后，左侧状态为绿色。

<img src="document_unity\image16.jpg" alt="img16" width="200" height="355">

3、无论左侧状态栏颜色为何状态，均可选择一家平台点击进入：<br>
&ensp;&ensp;（1） SDK Available 为绿色时表示三方平台适配器已添加；当为红色时表示三方平台适配器未添加，回到3.1-2检查该平台adapter是否添加<br>
&ensp;&ensp;（2） Configuration present为绿色时表示三方平台适配器组件Manifest已注册；当为红色时表示三方方平台适配器组件Manifest未注册，可回到3.1-3检查该平台适配器组件是否添加<br>
&ensp;&ensp;（3） SDK Failed to start or No_fill 为绿色表示广告曾经展示成功过；当为红色时表示还未展示成功过广告， 可继续进行下一个步骤，如果所有步骤完成后仍未红色，请邮件联系我们： support@yumimobi.com <br>

<img src="document_unity\image17.jpg" alt="img17" width="200" height="355">

4、点击Fetch开始请求广告，点击Show展示广告<br>
5、广告展示成功后检查项全部变为绿色，表示该家平台接入成功

<img src="document_unity\image18.jpg" alt="img18" width="200" height="355">

6、应用发布前需要将调试模式注释掉。

## 混淆

**如果您的工程需要混淆编译， 请在混淆文件内增加以下内容。**
```c
-keepattributes Exceptions，InnerClasses，Signature，Deprecated，SourceFile，LineNumberTable，*Annotation*，Synthetic，EnclosingMethod
-keep class com.yumi.android.sdk.ads.** { *;}
-keep class com.yumi.android.sdk.ads.self.**{*;}
-keep class com.yumi.android.sdk.ads.selfmedia.**{*;}
```

## 编译

**1、点击File，选择Build&Run 进行编译。**

<img src="document_unity\image19.png" alt="img19" width="240" height="260">


<img src="document_unity\image20.png" alt="img20" width="600" height="650">

**2、请注意设定Unity3D项目中的屏幕方向和Android项目中AndroidManifest.xml中所设置的保持一致，否则启动app后旋转屏幕可能会导致崩溃。（如下图）**


<img src="document_unity\image21.png" alt="img21" width="600" height="650">

**3、点击【Build And Run】等待进度条完成。**

<img src="document_unity\image22.png" alt="img22" width="600" height="120">

