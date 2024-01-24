using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu.Handlers
{
    public class LobbyStatusMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        [SerializeField]
        private ErrorSO errorSo;

        [SerializeField]
        private LobbySO lobbySo;
        
        private void OnEnable()
        {
            messageReceiverSo.OnLobbyStatusResponseMessage += Handle;
        }

        private void OnDisable()
        {
            messageReceiverSo.OnLobbyStatusResponseMessage -= Handle;
        }

        private void Handle(LobbyStatusResponseMessage message)
        {
            if (message.status == ResponseStatus.Ok)
            {
                lobbySo.Value = message.content;
            }
            else
            {
                // Display the error
                errorSo.Value = "Lobby error";
            }
        }
    }
}