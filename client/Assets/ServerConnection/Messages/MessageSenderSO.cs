using Newtonsoft.Json;
using UnityEngine;

namespace ServerConnection.Messages
{
    [CreateAssetMenu(menuName = "GO/MessageSenderSO")]
    public class MessageSenderSO : ScriptableObject
    {
        [SerializeField]
        private ServerConnectionChannelSO serverConnectionChannelSo;

        public void SendMessage<T>(RequestMessage<T> requestMessage)
        {
            var json = JsonConvert.SerializeObject(requestMessage);

            serverConnectionChannelSo.SendMessage(json);
        }
    }
}
