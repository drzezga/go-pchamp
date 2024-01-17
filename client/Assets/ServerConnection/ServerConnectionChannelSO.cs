
using UnityEngine;

[CreateAssetMenu(fileName = "Data", menuName = "GO/ServerConnectionChannelSO", order = 1)]
public class ServerConnectionChannelSO : ScriptableObject
{
    public delegate void OnMessageSentDelegate(string message);
    public event OnMessageSentDelegate OnMessageSent;
    
    public delegate void OnMessageReceivedDelegate(string message);
    public event OnMessageReceivedDelegate OnMessageReceived;

    public void SendMessage(string message)
    {
        Debug.Log("Message sent " + message);
        OnMessageSent?.Invoke(message);
    }

    public void ReceiveMessage(string message)
    {
        OnMessageReceived?.Invoke(message);
    }

}
