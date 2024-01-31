using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.SceneManagement;

namespace Game.Handlers
{
    public class GameFinishedMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        [SerializeField] private GameSettingsSO gameSettingsSo;

        [SerializeField] private ErrorSO errorSo;

        [SerializeField] private GameScoreSO gameScoreSo;

        private void OnEnable()
        {
            messageReceiverSo.OnGameFinishedResponseMessage += Handle;
        }

        private void OnDisable()
        {
            messageReceiverSo.OnGameFinishedResponseMessage -= Handle;
        }

        private void Handle(GameFinishedResponseMessage message)
        {
            if (message.status != ResponseStatus.Ok) return;
            gameScoreSo.Value = message.content.scores;
            SceneManager.LoadScene("MainMenu");
        }

    }
}
