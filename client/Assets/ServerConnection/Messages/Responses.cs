
using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;


[Serializable]
public class ResponseMessage<T>
{
    [JsonConverter(typeof(StringEnumConverter))]
    public MessageType msg;
    
    [JsonConverter(typeof(StringEnumConverter))]
    public ResponseStatus status;
    
    public T content;
}

public class LobbyListResponseMessage : ResponseMessage<List<Lobby>> {}
public class LobbyStatusResponseMessage : ResponseMessage<LobbyStatusMessageContent> {}
public class GameTryMoveResponseMessage : ResponseMessage<GameTryMoveMessageContent> {}
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
public class LobbyStatusMessageContent
{
    public string name;
    public List<LobbyPlayer> players;
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
