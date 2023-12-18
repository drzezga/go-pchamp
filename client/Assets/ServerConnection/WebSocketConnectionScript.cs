
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
            serverConnectionChannelSo.OnMessageSent += SendMessage;
        };

        _webSocket.OnMessage += message =>
        {
            serverConnectionChannelSo.ReceiveMessage(
                System.Text.Encoding.UTF8.GetString(message)
            );
        };

        await _webSocket.Connect();
    }

    private async void SendMessage(string message)
    {
        if(_webSocket.State != WebSocketState.Open)
        {
            Debug.LogError("Cannot send message because the WebSocket is not open");
            return;
        }

        await _webSocket.SendText(message);
    }
}
