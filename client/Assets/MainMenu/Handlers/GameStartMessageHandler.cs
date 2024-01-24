using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.Serialization;

namespace MainMenu.Handlers
{
    public class GameStartMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        private void OnEnable()
        {
            messageReceiverSo.OnGameStartResponseMessage += HandleMessage;
        }

        private void OnDisable()
        {
            messageReceiverSo.OnGameStartResponseMessage -= HandleMessage;
        }

        private void HandleMessage(GameStartResponseMessage message)
        {
            // We have left the lobby
            // Load the MainMenu scene

            // OR

            // We are joining a lobby
            // Load the appropriate level scene
            // TODO: Add dynamic size scenes
        }

    }
}