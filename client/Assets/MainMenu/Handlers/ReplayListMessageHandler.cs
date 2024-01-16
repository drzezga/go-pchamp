using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu.Handlers
{
    public class ReplayListMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        private void OnEnable()
        {
            messageReceiverSo.OnReplayListResponseMessage += HandleMessage;
        }

        private void OnDisable()
        {
            messageReceiverSo.OnReplayListResponseMessage -= HandleMessage;
        }

        private void HandleMessage(ReplayListResponseMessage message)
        {
            
        }
    }
}