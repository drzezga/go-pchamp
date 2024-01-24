using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.Serialization;

namespace MainMenu.Handlers
{
    public class LobbyStatusMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        [SerializeField]
        private ErrorSO errorSo;

        [FormerlySerializedAs("lobbySo")] [SerializeField]
        private CurrentLobbySO currentLobbySo;
        
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
                currentLobbySo.Value = message.content;
            }
            else
            {
                // Display the error
                errorSo.DisplayError("Lobby error");
            }
        }
    }
}