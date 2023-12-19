using System;
using JetBrains.Annotations;
using Newtonsoft.Json;
using UnityEngine;
using Object = System.Object;

[CreateAssetMenu(menuName = "GO/MessageDecoderSO")]
public class MessageDecoderSO : ScriptableObject
{
    [SerializeField]
    private ServerConnectionChannelSO serverConnectionChannelSo;
    
    public delegate void OnMessageDelegate<T>([CanBeNull] ResponseMessage<T> responseMessage);

    public event OnMessageDelegate<GameMoveResponseMessageContent> OnGameMoveResponseMessage;

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
            case MessageType.GameMove:
                OnGameMoveResponseMessage?.Invoke(
                    JsonConvert.DeserializeObject<ResponseMessage<GameMoveResponseMessageContent>>(messageString)
                );
                break;
            default: throw new NotImplementedException($"Parsing was not implemented for message {message.msg}");
        }
    }
}
