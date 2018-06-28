using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DemoTest : MonoBehaviour {

    private Camera mainCamera;

    private int buttonWidth = 200;
    private int buttonHeight = 80;
    private int buttonFontSize = 20;

    void Start()
    {
        // Use this for instantiation

        //Remind users to get permission

        mainCamera = Camera.main;
        mainCamera.aspect = 1.78f;

       
        int window_Width = Screen.width;
        if (window_Width > 720 && window_Width <= 1080)
        {
            buttonWidth = 300;
            buttonHeight = 120;
            buttonFontSize = 30;
        }
        else if (window_Width > 1080)
        {
            buttonWidth = 400;
            buttonHeight = 160;
            buttonFontSize = 40;
        }

    }

    void OnGUI()
    {
        GUIStyle myButtonStyle = new GUIStyle(GUI.skin.button);
        myButtonStyle.fontSize = buttonFontSize;


        if (GUI.Button(new Rect(30, 40, buttonWidth, buttonHeight), "ShowBannel", myButtonStyle))
        {
            // show banner
            ZplayYUMIHelper.Instance.ShowBannel();

            //banner Width Match Window
            //ZplayYUMIHelper.Instance.ShowBannel(true);
        }

        if (GUI.Button(new Rect(40+ buttonWidth, 40, buttonWidth, buttonHeight), "ResumeBanner", myButtonStyle))
        {
            //resume banner
            ZplayYUMIHelper.Instance.ResumeBanner();
        }

        if (GUI.Button(new Rect(30, 60 + buttonHeight, buttonWidth, buttonHeight), "DismissBanner", myButtonStyle))
        {
            // dismiss banner
            ZplayYUMIHelper.Instance.DismissBanner();
        }



        if (GUI.Button(new Rect(30, (buttonHeight * 2)+ 80, buttonWidth, buttonHeight), "ShowInterstitialAd", myButtonStyle))
        {
            // show interstitial
            ZplayYUMIHelper.Instance.ShowInterstitialAD();
        }



        if (GUI.Button(new Rect(30, (buttonHeight * 3) + 100, buttonWidth, buttonHeight), "ShowMedia", myButtonStyle))
        {
            // show Media
            ZplayYUMIHelper.Instance.ShowMedia();
        }

        if (GUI.Button(new Rect(30, (buttonHeight * 4) + 120, buttonWidth, buttonHeight), "StartDebugging", myButtonStyle))
        {
            // Start Debugging
            ZplayYUMIHelper.Instance.StartDebugging();           
        }

    }
}
