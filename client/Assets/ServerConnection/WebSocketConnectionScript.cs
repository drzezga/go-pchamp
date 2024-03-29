
using NativeWebSocket;
using UnityEngine;

public class WebSocketConnectionScript : MonoBehaviour
{
    [SerializeField]
    private string serverAddress = "ws://localhost:8080/ws";

    [SerializeField]
    private ServerConnectionChannelSO serverConnectionChannelSo;

    private WebSocket _webSocket;

    private async void Start()
    {
        _webSocket = new WebSocket(serverAddress);

        _webSocket.OnOpen += () =>
        {
            Debug.Log("WebSocket open!");
        };

        _webSocket.OnClose += _ =>
        {
            Debug.Log("Websocket closed");
        };

        _webSocket.OnMessage += message =>
        {
            serverConnectionChannelSo.ReceiveMessage(
                System.Text.Encoding.UTF8.GetString(message)
            );
        };
        
        serverConnectionChannelSo.OnMessageSent += SendWebsocketMessage;
        
        await _webSocket.Connect();
    }
    
    
    private void Update()
    {
        #if !UNITY_WEBGL || UNITY_EDITOR
        _webSocket.DispatchMessageQueue();
        #endif
    }


    private async void SendWebsocketMessage(string message)
    {
        if(_webSocket.State != WebSocketState.Open)
        {
            Debug.LogWarning("Cannot send message because the WebSocket is not open");
            return;
        }
        
        await _webSocket.SendText(message);
    }
}
