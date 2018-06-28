using UnityEngine;
public class BannerAdCallbackListener:MonoBehaviour,YumiUnityAdUtils.BannerAdCallbackListener {

    public void onBannerPreparedFailed(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Bannel Prepared Failed :" + data);
    }
    public void onBannerPrepared(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Bannel Prepared Succeed");
    }
    public void onBannerExposure(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Bannel Exposure Succeed");
    }
    public void onBannerClosed(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Bannel Closed");
    }
    public void onBannerClicked(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Bannel Clicked");
    }
}
