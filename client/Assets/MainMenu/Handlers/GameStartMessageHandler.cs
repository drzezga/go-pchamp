using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.Serialization;

namespace MainMenu.Handlers
{
    public class GameStartMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        [SerializeField] private GameSettingsSO gameSettingsSo;

        [SerializeField] private ErrorSO errorSo;

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
            if (message.status == ResponseStatus.Ok)
            {
                gameSettingsSo.inGame = true;
                gameSettingsSo.Value = message.content;

                // TODO: Add dynamically-sized scene
                SceneManager.LoadScene("GO_AdaptiveSize");
            }
            else
            {
                errorSo.DisplayError(message.error ?? "Error joining the game");
            }
        }

    }
}