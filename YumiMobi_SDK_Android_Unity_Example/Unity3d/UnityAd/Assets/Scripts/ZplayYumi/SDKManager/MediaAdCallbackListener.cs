using System;
using UnityEngine;
public class MediaAdCallbackListener: MonoBehaviour,YumiUnityAdUtils.MediaAdCallbackListener {

    public void onMediaIncentived(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Media Incentived Succeed callBack");
        //Give a reward       
    }
    public void onMediaExposure(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Media Exposure Succeed");
    }
    public void onMediaClosed(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Media Closed");
        //Once the video screen is successful, it will be retrained to see if the video screen is loaded
        ZplayYUMIHelper.Instance.GetRotaIsMediaPrepared = false;
    }
    public void onMediaClicked(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Media Clicked");
    }
    public void onIsMediaPrepared(string data)
    {
        ZplayLogger.LogError("yumiMobi SDK Media Is Prepared :" + data);
        if (Convert.ToBoolean(data))
        {

            //Video requests success., stop run IsMediaPrepared
            ZplayYUMIHelper.Instance.GetRotaIsMediaPrepared = true;
        }

    }
   
}
