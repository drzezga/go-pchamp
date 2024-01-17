using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu.Handlers
{
    public class LobbyListMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        private void OnEnable()
        {
            messageReceiverSo.OnLobbyListResponseMessage += HandleMessage;
        }

        private void OnDisable()
        {
            messageReceiverSo.OnLobbyListResponseMessage -= HandleMessage;
        }

        private void HandleMessage(LobbyListResponseMessage message)
        {
            
        }
    }
}