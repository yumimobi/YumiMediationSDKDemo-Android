using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;

public class MakeZplayPrefab
{
    [MenuItem("MakeZplayPrefab/MakeZplayYUMIPrefab")]
    private static void CrentZplayYUMI()
    {
        GameObject obj = new GameObject("ZplayYUMIHelper");
        obj.AddComponent<ZplayYUMIHelper>();
        obj.AddComponent<BannerAdCallbackListener>();
        obj.AddComponent<MediaAdCallbackListener>();
        obj.AddComponent<InterstitialAdCallbackListener>();
    }
}
