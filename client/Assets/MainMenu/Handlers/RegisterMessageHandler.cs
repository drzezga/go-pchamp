using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu.Handlers
{
    public class RegisterMessageHandler : MonoBehaviour
    {
        [SerializeField] private MessageReceiverSO messageReceiverSo;

        [SerializeField] private UsernameSO usernameSo;

        private void OnEnable()
        {
            messageReceiverSo.OnRegisterResponseMessage += HandleMessage;
        }

        private void OnDisable()
        {
            messageReceiverSo.OnRegisterResponseMessage -= HandleMessage;
        }

        private void HandleMessage(RegisterResponseMessage message)
        {
            usernameSo.Value = message.content;
        }
    }
}