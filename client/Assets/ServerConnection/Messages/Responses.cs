
using System;
using System.Runtime.Serialization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using UnityEngine;

[JsonConverter(typeof(StringEnumConverter))]
public enum ResponseSatus
{
    [EnumMember(Value = "ok")]
    Ok,
    [EnumMember(Value = "not ok")]
    NotOk,
}

[Serializable]
public class ResponseMessage<T>
{
    [JsonConverter(typeof(StringEnumConverter))]
    public MessageType msg;
    
    [JsonConverter(typeof(StringEnumConverter))]
    public ResponseSatus status;
    
    public T content;
}

public class GameMoveResponseMessageContent
{
    public string player;
    public int[] position;
}