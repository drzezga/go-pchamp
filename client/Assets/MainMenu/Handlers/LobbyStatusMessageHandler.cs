using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu.Handlers
{
    public class LobbyStatusMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        private void OnEnable()
        {
            messageReceiverSo.OnLobbyStatusResponseMessage += HandleMessage;
        }

        private void OnDisable()
        {
            messageReceiverSo.OnLobbyStatusResponseMessage -= HandleMessage;
        }

        private void HandleMessage(LobbyStatusResponseMessage message)
        {
            
        }
    }
}