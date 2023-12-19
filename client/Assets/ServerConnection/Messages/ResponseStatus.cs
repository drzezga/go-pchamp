using System.Collections;
using System.Collections.Generic;
using System.Runtime.Serialization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using UnityEngine;

[JsonConverter(typeof(StringEnumConverter))]
public enum ResponseStatus
{
    [EnumMember(Value = "ok")]
    Ok,
    [EnumMember(Value = "not ok")]
    NotOk,
}