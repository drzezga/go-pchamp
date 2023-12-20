using System;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using UnityEngine;

[Serializable]
public class RequestMessage<T>
{
    [JsonConverter(typeof(StringEnumConverter))]
    public MessageType msg;
    public T content;
}

class GameTryMoveRequestMessage : RequestMessage<GameTryMoveRequestMessageContent>
{
    public GameTryMoveRequestMessage(Vector2Int positionIndex)
    {
        msg = MessageType.GameTryMove;
        content = new GameTryMoveRequestMessageContent();
        content.position = new[]
        {
            positionIndex[0],
            positionIndex[1]
        };
    }
}

[Serializable]
public class GameTryMoveRequestMessageContent
{
    public int[] position;
}
