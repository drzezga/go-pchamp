using System;
using System.Runtime.Serialization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using UnityEngine;

namespace ServerConnection.Messages
{
    [Serializable]
    public class RequestMessage<T>
    {
        [JsonConverter(typeof(StringEnumConverter))]
        public MessageType msg;
        public T content;
    }

    public class RegisterRequestMessage : RequestMessage<string>
    {
        public RegisterRequestMessage(string username)
        {
            msg = MessageType.Register;
            content = username;
        }
    }

    public class LobbyListRequestMessage : RequestMessage<NullMessageContent>
    {
        public LobbyListRequestMessage()
        {
            msg = MessageType.LobbyList;
            content = null;
        }
    }
    public class LobbyJoinRequestMessage : RequestMessage<LobbyStatusRequestMessageContent>
    {
        public LobbyJoinRequestMessage(string lobbyName)
        {
            msg = MessageType.LobbyStatus;
            content = new LobbyStatusRequestMessageContent
            {
                action = LobbyStatusAction.Join,
                name = lobbyName
            };
        }
    }
    
    public class LobbyLeaveRequestMessage : RequestMessage<LobbyStatusRequestMessageContent>
    {
        public LobbyLeaveRequestMessage(string lobbyName)
        {
            msg = MessageType.LobbyStatus;
            content = new LobbyStatusRequestMessageContent
            {
                action = LobbyStatusAction.Leave,
                name = lobbyName
            };
        }
    }

    public class GameStartRequestMessage : RequestMessage<GameSettings>
    {
        public GameStartRequestMessage(GameSettings settings)
        {
            msg = MessageType.GameStart;
            content = settings;
        }
    }
    
    public class GameLeaveRequestMessage : RequestMessage<NullMessageContent>
    {
        public GameLeaveRequestMessage()
        {
            msg = MessageType.GameLeave;
            content = null;
        }
    }

    public class GameTryMoveRequestMessage : RequestMessage<GameTryMoveRequestMessageContent>
    {
        public GameTryMoveRequestMessage(Vector2Int positionIndex)
        {
            msg = MessageType.GameTryMove;
            content = new GameTryMoveRequestMessageContent
            {
                position = new[]
                {
                    positionIndex[0],
                    positionIndex[1]
                }
            };
        }
    }
    
    public class GamePassTurnRequestMessage : RequestMessage<GameTryMoveRequestMessageContent>
    {
        public GamePassTurnRequestMessage()
        {
            msg = MessageType.GameTryMove;
            content = new GameTryMoveRequestMessageContent
            {
                position = null
            };
        }
    }

    public class ReplayListRequestMessage : RequestMessage<NullMessageContent>
    {
        public ReplayListRequestMessage()
        {
            msg = MessageType.ReplayList;
            content = null;
        }
    }

    public class ReplayGetRequestMessage : RequestMessage<ReplayGetRequestMessageContent>
    {
        public ReplayGetRequestMessage(string replayId)
        {
            msg = MessageType.ReplayGet;
            content = new ReplayGetRequestMessageContent()
            {
                id = replayId
            };
        }
    }

    public enum LobbyStatusAction
    {
        [EnumMember(Value = "JOIN")]
        Join,
        [EnumMember(Value = "LEAVE")]
        Leave,
    }

    [Serializable]
    public class LobbyStatusRequestMessageContent
    {
        [JsonConverter(typeof(StringEnumConverter))]
        public LobbyStatusAction action;

        public string name;
    }
    
    [Serializable]
    public class GameTryMoveRequestMessageContent
    {
        public int[] position;
    }

    [Serializable]
    public class ReplayGetRequestMessageContent
    {
        public string id;
    }
}