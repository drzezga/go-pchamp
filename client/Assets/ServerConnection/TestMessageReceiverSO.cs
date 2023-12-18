using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu(menuName = "Testing/TestMessageReceiverSO", order = 1)]
public class TestMessageReceiverSO : ScriptableObject
{
    [TextArea(16, 32)]
    [SerializeField]
    private string message;

    [SerializeField]
    private ServerConnectionChannelSO connectionChannelSo;

    public void SendTestMessage()
    {
        connectionChannelSo.ReceiveMessage(message);
    }
}
