using System;
using Newtonsoft.Json;
using UnityEngine;
using Object = System.Object;

namespace ServerConnection.Messages
{
    [CreateAssetMenu(menuName = "GO/MessageReceiverSO")]
    public class MessageReceiverSO : ScriptableObject
    {
        [SerializeField]
        private ServerConnectionChannelSO serverConnectionChannelSo;

        public delegate void OnMessageDelegate<in T>(T responseMessage);

        
        public event OnMessageDelegate<RegisterResponseMessage> OnRegisterResponseMessage;
        public event OnMessageDelegate<LobbyListResponseMessage> OnLobbyListResponseMessage;
        public event OnMessageDelegate<LobbyStatusResponseMessage> OnLobbyStatusResponseMessage;
        public event OnMessageDelegate<GameStartResponseMessage> OnGameStartResponseMessage;
        public event OnMessageDelegate<GameLeaveResponseMessage> OnGameLeaveResponseMessage;
        public event OnMessageDelegate<GameTryMoveResponseMessage> OnGameTryMoveResponseMessage;
        public event OnMessageDelegate<GameMoveResponseMessage> OnGameMoveResponseMessage;
        public event OnMessageDelegate<GameFinishedResponseMessage> OnGameFinishedResponseMessage;
        public event OnMessageDelegate<ReplayListResponseMessage> OnReplayListResponseMessage;
        public event OnMessageDelegate<ReplayGetResponseMessage> OnReplayGetResponseMessage;
    

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
            
            switch (message!.msg)
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
                case MessageType.Register:
                    OnRegisterResponseMessage?.Invoke(
                        JsonConvert.DeserializeObject<RegisterResponseMessage>(messageString)
                    );
                    break;
                case MessageType.LobbyList:
                    OnLobbyListResponseMessage?.Invoke(
                        JsonConvert.DeserializeObject<LobbyListResponseMessage>(messageString)
                    );
                    break;
                case MessageType.LobbyStatus:
                    OnLobbyStatusResponseMessage?.Invoke(
                        JsonConvert.DeserializeObject<LobbyStatusResponseMessage>(messageString)
                    );
                    break;
                case MessageType.GameStart:
                    OnGameStartResponseMessage?.Invoke(
                        JsonConvert.DeserializeObject<GameStartResponseMessage>(messageString)
                    );
                    break;
                case MessageType.GameLeave:
                    OnGameLeaveResponseMessage?.Invoke(
                        JsonConvert.DeserializeObject<GameLeaveResponseMessage>(messageString)
                    );
                    break;
                case MessageType.GameFinished:
                    OnGameFinishedResponseMessage?.Invoke(
                        JsonConvert.DeserializeObject<GameFinishedResponseMessage>(messageString)
                    );
                    break;
                case MessageType.ReplayList:
                    OnReplayListResponseMessage?.Invoke(
                        JsonConvert.DeserializeObject<ReplayListResponseMessage>(messageString)
                    );
                    break;
                case MessageType.ReplayGet:
                    OnReplayGetResponseMessage?.Invoke(
                        JsonConvert.DeserializeObject<ReplayGetResponseMessage>(messageString)
                    );
                    break;
                default:
                    throw new NotImplementedException($"Parsing was not implemented for message {message.msg}");
            }
        }
    }
}
