using System.Collections;
using UnityEngine;

public class ZplayYUMIHelper : MonoBehaviour
{
    public static ZplayYUMIHelper Instance;

    //---------- yu mi slot id--------//
    private string banner_slotID = "";
    private string interstitial_slotID = "";
    private string media_slotID = "";
    //-----------channel id ------//
    private const string channelId = "";//
    //-----------version id ------//
    private const string versionId = "";//

    private YumiUnityAdUtils yuMiUnityAD;

    private bool rotaIsMediaPrepared;
    private float timeNum;

    void Awake()
    {
        //Use as a singleton
        Instance = this;
        //Follow all the scenes
        DontDestroyOnLoad(gameObject);
        //Init yumi sdk
        yuMiUnityAD = new YumiUnityAdUtils(banner_slotID, interstitial_slotID, media_slotID, channelId, versionId);
    }
	void Start () {
        //Remind users to get permission   
        yuMiUnityAD.CheckPermission();
        //Init video
        InitMedia();
        //Init Interstitial
        InitInterstitialAd();
        //Whether to open the log information (false:close，true：open)
        ZplayLogger.SetDebug(true);
        //After the initial video screen is completed, wait for 0.2 seconds to start requesting the video and the interstitial
        StartCoroutine(Reques(0.2f));
    }

    IEnumerator Reques(float time)
    {
        yield return new WaitForSeconds(time);
        Request();
    }

    void Update () {
        //Ask about video every 5 seconds is prepared 
        if (!rotaIsMediaPrepared)
        {
            timeNum += Time.fixedDeltaTime;
            if (timeNum > 5f)
            {
                timeNum = 0;
                //Check video for Prepared
                yuMiUnityAD.IsMediaPrepared(gameObject.name);
            }
        }
	}

    private void InitMedia()
    {
        yuMiUnityAD.InitMedia(gameObject.name);
    }

    private void InitInterstitialAd()
    {
        yuMiUnityAD.InitInterstitialAd(gameObject.name);
    }

    private void Request()
    {
        yuMiUnityAD.RequestInterstitial();
        yuMiUnityAD.RequestMedia();
    }

    #region Bannel
    //----------------------------- Bannel begin -----------------//
    public void ShowBannel()
    {
        ZplayLogger.LogError("yumiMobi show Bannel");
        yuMiUnityAD.AddBannerAd(gameObject.name);
    }

    /// <summary>
    /// Show Bannel
    /// </summary>
    /// <param name="isMatchWindowWidth">>banner Width Match Window</param>
    public void ShowBannel(bool isMatchWindowWidth)
    {
        ZplayLogger.LogError("yumiMobi show Bannel isMatchWindowWidth : " + isMatchWindowWidth);

        yuMiUnityAD.AddBannerAd(gameObject.name, isMatchWindowWidth);
    }
    public void DismissBanner()
    {
        ZplayLogger.LogError("yumiMobi Dismiss Banner：");
        yuMiUnityAD.DismissBanner();
    }

    public void ResumeBanner()
    {
        ZplayLogger.LogError("yumiMobi Resume Banner");
        yuMiUnityAD.ResumeBanner();
    } 
    //------------------------------- Bannel end -----------------//
    #endregion

    #region InitInterstitial
    //----------------------------- InitInterstitial begin -----------------//
    public void ShowInterstitialAD()
    {
        ZplayLogger.LogError("yumiMobi ShowInterstitialAD");
        yuMiUnityAD.ShowInterstitialAd();
    }
    //------------------------------- InitInterstitial end -----------------//
    #endregion

    #region Media
    //----------------------------- Media begin -----------------//
    public void ShowMedia()
    {
        ZplayLogger.LogError("yumiMobi ShowMedia ：" + yuMiUnityAD);
        yuMiUnityAD.ShowMedia();
    }

        
    /// <summary>
    /// Return to video to see if the AD is ready to be completed
    /// </summary>
    public bool GetRotaIsMediaPrepared
    {
        get
        {
            return rotaIsMediaPrepared;
        }
        set
        {
            rotaIsMediaPrepared = value;
        }
    }
    //------------------------------- Media end -----------------//
    #endregion

    #region Debugging
    /// <summary>
    /// Start Debugging Activity
    /// </summary>
    public void StartDebugging()
    {
        yuMiUnityAD.StartDebugging();
    } 
    #endregion

    /// <summary>
    /// Setting The APP Is for GooglePlay released
    /// </summary>
    public void SetAppIsGooglePlayVersions()
    {
        yuMiUnityAD.SetAppIsGooglePlayVersions();
    }
}
