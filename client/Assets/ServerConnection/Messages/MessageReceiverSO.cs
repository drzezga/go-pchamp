using System;
using JetBrains.Annotations;
using Newtonsoft.Json;
using UnityEngine;
using Object = System.Object;

[CreateAssetMenu(menuName = "GO/MessageReceiverSO")]
public class MessageReceiverSO : ScriptableObject
{
    [SerializeField]
    private ServerConnectionChannelSO serverConnectionChannelSo;

    public delegate void OnMessageDelegate<in T>(T responseMessage);

    public event OnMessageDelegate<GameTryMoveResponseMessage> OnGameTryMoveResponseMessage;
    public event OnMessageDelegate<GameMoveResponseMessage> OnGameMoveResponseMessage;
    

    private void OnEnable()
    {
        serverConnectionChannelSo.OnMessageReceived += HandleMessageParsing;
    }

    private void OnDisable()
    {
        serverConnectionChannelSo.OnMessageReceived -= HandleMessageParsing;
    }

    private void HandleMessageParsing(string messageString)
    {
        var message = JsonConvert.DeserializeObject<ResponseMessage<Object>>(messageString);

        switch(message!.msg)
        {
            case MessageType.GameTryMove:
                OnGameTryMoveResponseMessage?.Invoke(
                    JsonConvert.DeserializeObject<GameTryMoveResponseMessage>(messageString)
                );
                break;
            case MessageType.GameMove:
                OnGameMoveResponseMessage?.Invoke(
                    JsonConvert.DeserializeObject<GameMoveResponseMessage>(messageString)
                );
                break;
            default: throw new NotImplementedException($"Parsing was not implemented for message {message.msg}");
        }
    }
}
