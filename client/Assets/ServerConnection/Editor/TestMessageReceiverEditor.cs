
using UnityEngine;
using UnityEditor;

[CustomEditor(typeof(TestMessageReceiverSO))]
public class TestMessageReceiverEditor : Editor
{
    public override void OnInspectorGUI()
    {
        base.OnInspectorGUI();

        if(GUILayout.Button("Receive Specified Message", GUILayout.Height(32)))
        {
            var script = (TestMessageReceiverSO) target;
            script.SendTestMessage();
        }
    }
}
