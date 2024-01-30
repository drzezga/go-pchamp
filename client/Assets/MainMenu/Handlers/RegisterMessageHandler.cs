using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu.Handlers
{
    public class RegisterMessageHandler : MonoBehaviour
    {
        [SerializeField] private MessageReceiverSO messageReceiverSo;

        [SerializeField] private UsernameSO usernameSo;

        [SerializeField] private ErrorSO errorSo;

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
            if (message.status == ResponseStatus.Ok)
                usernameSo.Value = message.content;
            else
                errorSo.DisplayError(message.error ?? "Login error");
        }
    }
}