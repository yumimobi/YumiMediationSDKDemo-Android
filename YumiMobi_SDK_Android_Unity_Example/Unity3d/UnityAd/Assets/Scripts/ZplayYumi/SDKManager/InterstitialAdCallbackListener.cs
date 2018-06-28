using UnityEngine;
public class InterstitialAdCallbackListener:MonoBehaviour,YumiUnityAdUtils.InterstitialAdCallbackListener
{
    public void onInterstitialPreparedFailed(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Interstitial Prepared Failed :" + data);
    }
    public void onInterstitialPrepared(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Interstitial Prepared Succeed");
    }
    public void onInterstitialExposure(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Interstitial Exposure Succeed");
    }
    public void onInterstitialExposureFailed(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Interstitial Exposure Failed ");
    }
    public void onInterstitialClosed(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Interstitial Closed ");
    }
    public void onInterstitialClicked(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Interstitial Clicke ");
    }
}
