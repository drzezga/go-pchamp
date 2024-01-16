using System.Runtime.Serialization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace ServerConnection.Messages
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum ResponseStatus
    {
        [EnumMember(Value = "ok")]
        Ok,
        [EnumMember(Value = "not ok")]
        NotOk,
    }
}