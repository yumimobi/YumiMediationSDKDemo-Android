
###  iOS SDK  & iOS_Unity  SDK更新日志

#### V 3.6.4

1.update baidu sdk to 4.6.4，无locaotion权限

2.update GDT sdk to 4.10.2；若对loacation权限要求严格，删除GDT  corelocation.framework 库将无laocation相关权限

3.update Mintegral sdk to 5.3.1

4.增加 BytedanceAds （穿山甲）平台，支持广告形式：Banner、Interstitial、Video、Native




#### V 3.6.3

1.YUMI，AdColony，AdMob，AppLovin ， Chartboost，Facebook，IronSource，ONEWAY，UnityAds ，Vungle，IronSource , ZplayAds 无Location获取权限；其他平台请酌情考虑是否添加

2.更新IronSource到6810版本

#### V 3.6.1

1. 适配更多聚合平台

#### V 3.6.0

1. YUMI支持可玩物料类型（插屏 & 视频）,支持插页式视频物料
2. 修改原生广告聚合逻辑和返回的数据接口，增加百度,admob,facebook平台原生广告
3. 修改 adtype 的枚举值
4. 增加SMART_BANNE
5. 新增 IQzone banner、interstitial 、video 广告形式
6. 新增 Mintegral interstitials video广告形式
7. 上报参数增加 osVersion 和 brand
8. 优化debugcenter逻辑
9. update Chartboost sdk to 7.3.1
10. update Facebook sdk to 5.1.0
11. update InMobi sdk to 7.2.2
12. interstitial & video增加新的上报节点，增加更多宏变量参数
13. Untiy插件SDK:接口字段做了优化和修改，请按照文档重新接入



#### V 3.4.1
1. vungle支持多层配置功能


#### V 3.4.0

如您使用Unity插件接入SDK 3.4.0版本，请先将此前版本删除并按照最新文档重新接入。

1.百度SDK更新到4.5.0.4，新增视频广告形式

2.支持facebook header bidding功能，支持广告形式banner，插屏，视频

3.广点通实现插屏&视频自渲染功能

4.applovin banner支持后台配置

5.视频增加新的上报节点

6.更新banner，插屏，视频配置请求及使用逻辑

7.优化debug center 模块


#### V 3.3.9

1.ironsource 支持多层配置

2.视频平台重试逻辑优化

3.facebook udpate to 4.99.3

4.unity update to 2.3.0

5.vungle update 6.2.0，修改插屏播放完成立即返回广告问题

6.chartboost SDK update to 7.2.0


#### V 3.3.8
1.更新ZPLAYAds到2.1.0，支持插屏和视频广告形式

2.更新IronSource到6.7.10，支持BANNER,插屏和视频广告形式

3.更新Domob到3.8.0，支持视频广告形式




#### V 3.3.7
1.优化请求逻辑

2.更新facebook到4.99.1

3.更新mobvista 到3.9.1

4.更新 APPLovin逻辑

5.更新ZPLAYAds逻辑




