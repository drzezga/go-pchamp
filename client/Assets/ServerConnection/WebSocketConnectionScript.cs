
using NativeWebSocket;
using UnityEngine;

public class WebSocketConnectionScript : MonoBehaviour
{
    [SerializeField]
    private string serverAddress = "ws://localhost:8000";

    [SerializeField]
    private ServerConnectionChannelSO serverConnectionChannelSo;

    private WebSocket _webSocket;

    private async void Start()
    {
        _webSocket = new WebSocket(serverAddress);

        _webSocket.OnOpen += async () =>
        {
            Debug.Log("WebSocket open!");
        };
        
        _webSocket.OnMessage += message =>
        {
            serverConnectionChannelSo.ReceiveMessage(
                System.Text.Encoding.UTF8.GetString(message)
            );
        };
        
        serverConnectionChannelSo.OnMessageSent += SendMessage;
        
        await _webSocket.Connect();
    }

    private async void SendMessage(string message)
    {
        if(_webSocket.State != WebSocketState.Open)
        {
            Debug.LogWarning("Cannot send message because the WebSocket is not open");
            return;
        }

        await _webSocket.SendText(message);
    }
}
