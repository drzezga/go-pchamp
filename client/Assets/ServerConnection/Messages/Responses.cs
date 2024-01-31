using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using UnityEngine;

namespace ServerConnection.Messages
{
    [Serializable]
    public class ResponseMessage<T>
    {
        [JsonProperty("msg")]
        [JsonConverter(typeof(StringEnumConverter))]
        public MessageType msg;
    
        [JsonProperty("status")]
        [JsonConverter(typeof(StringEnumConverter))]
        public ResponseStatus status;
        
        [AllowNull] [JsonProperty(NullValueHandling = NullValueHandling.Include)]
        public string error;
    
        [AllowNull] [JsonProperty(NullValueHandling = NullValueHandling.Include)]
        public T content;
    }

    public class RegisterResponseMessage : ResponseMessage<string> {}
    public class LobbyListResponseMessage : ResponseMessage<List<Lobby>> {}
    public class LobbyStatusResponseMessage : ResponseMessage<LobbyDetails> {}
    public class GameStartResponseMessage : ResponseMessage<GameSettings> {}
    public class GameLeaveResponseMessage : ResponseMessage<NullMessageContent> {}
    public class GameTryMoveResponseMessage : ResponseMessage<GameTryMoveResponseMessageContent> {}
    public class GameMoveResponseMessage : ResponseMessage<GameMoveResponseMessageContent> {}
    public class GameFinishedResponseMessage : ResponseMessage<GameFinishedResponseMessageContent> {}
    public class ReplayListResponseMessage : ResponseMessage<List<ReplayBrief>> {}
    public class ReplayGetResponseMessage : ResponseMessage<ReplayGetResponseMessageContent> {}

    public class NullMessageContent : System.Object {}

    [Serializable]
    public class Lobby
    {
        public string name;
        public int playerCount;
    }

    [Serializable]
    public class LobbyPlayer
    {
        public string name;
        public bool isHost;
    }

    [Serializable]
    public class ScorePlayer
    {
        public string name;
        public float score;
    }

    [Serializable]
    public class LobbyDetails
    {
        public string name;
        public List<LobbyPlayer> players;
    }

    [Serializable]
    public class GameSettings
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
        [AllowNull] [JsonProperty(NullValueHandling = NullValueHandling.Include)]
        public int[] position;

        public int[][] captures;
    }

    [Serializable]
    public class GameFinishedResponseMessageContent
    {
        public List<ScorePlayer> scores;
    }

    [Serializable]
    public class ReplayBrief
    {
        public string id;
        public string name;
        public List<ScorePlayer> players;
        public string startingPlayer;
    }
    
    [Serializable]
    public class BoardState
    {
        public int[][] white;
        public int[][] black;
    }

    [Serializable]
    public class ReplayGetResponseMessageContent
    {
        public string id;
        public string name;
        public List<ScorePlayer> players;
        public List<int[]> moves;
        public List<BoardState> boardStates;
        public GameSettings gameSettings;
    }

}