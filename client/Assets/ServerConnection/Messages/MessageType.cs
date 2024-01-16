using System.Runtime.Serialization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace ServerConnection.Messages
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum MessageType
    {
        [EnumMember(Value = "REGISTER")]
        Register,
        [EnumMember(Value = "LOBBY_LIST")]
        LobbyList,
        [EnumMember(Value = "LOBBY_STATUS")]
        LobbyStatus,
        [EnumMember(Value = "GAME_START")]
        GameStart,
        [EnumMember(Value = "GAME_LEAVE")]
        GameLeave,
        [EnumMember(Value = "GAME_TRY_MOVE")]
        GameTryMove,
        [EnumMember(Value = "GAME_MOVE")]
        GameMove,
        [EnumMember(Value = "GAME_FINISHED")]
        GameFinished,
        [EnumMember(Value = "REPLAY_LIST")]
        ReplayList,
        [EnumMember(Value = "REPLAY_GET")]
        ReplayGet,
    }
}
