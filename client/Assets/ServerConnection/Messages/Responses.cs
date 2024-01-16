
using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;


[Serializable]
public class ResponseMessage<T>
{
    [JsonConverter(typeof(StringEnumConverter))]
    public MessageType msg;
    
    [JsonConverter(typeof(StringEnumConverter))]
    public ResponseStatus status;
    
    [AllowNull] [JsonProperty(NullValueHandling = NullValueHandling.Include)]
    public T content;
}

public class RegisterResponseMessage : ResponseMessage<string> {}
public class LobbyListResponseMessage : ResponseMessage<List<Lobby>> {}
public class LobbyStatusResponseMessage : ResponseMessage<LobbyStatusResponseMessageContent> {}
public class GameStartResponseMessage : ResponseMessage<GameStartResponseMessageContent> {}
public class GameLeaveResponseMessage : ResponseMessage<NullMessageContent> {}
public class GameTryMoveResponseMessage : ResponseMessage<GameTryMoveResponseMessageContent> {}
public class GameMoveResponseMessage : ResponseMessage<GameMoveResponseMessageContent> {}

public class NullMessageContent : System.Object {}

[Serializable]
public class Lobby
{
    public string name;
    public int playerCount;
}

public class LobbyPlayer
{
    public string name;
    public bool isHost;
}

[Serializable]
public class LobbyStatusResponseMessageContent
{
    public string name;
    public List<LobbyPlayer> players;
}

[Serializable]
public class GameStartResponseMessageContent
{
    public int size;
    public bool botOpponent;
    public string startingPlayer;
}

[Serializable]
public class GameTryMoveResponseMessageContent
{
    public string error;
}


[Serializable]
public class GameMoveResponseMessageContent
{
    public string player;
    public int[] position;
}
