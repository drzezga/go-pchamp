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
            
        }

    }
}