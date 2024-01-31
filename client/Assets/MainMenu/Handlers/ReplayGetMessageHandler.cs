using System.Collections;
using System.Collections.Generic;
using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ReplayGetMessageHandler : MonoBehaviour
{
    [SerializeField]
    private MessageReceiverSO messageReceiverSo;

    [SerializeField] private GameSettingsSO gameSettingsSo;
    [SerializeField] private CurrentReplaySO currentReplaySo;

    [SerializeField] private ErrorSO errorSo;

    private void OnEnable()
    {
        messageReceiverSo.OnReplayGetResponseMessage += HandleMessage;
    }

    private void OnDisable()
    {
        messageReceiverSo.OnReplayGetResponseMessage -= HandleMessage;
    }

    private void HandleMessage(ReplayGetResponseMessage message)
    {
        if (message.status == ResponseStatus.Ok)
        {
            gameSettingsSo.Value = message.content.gameSettings;

            currentReplaySo.Value = message.content;

            SceneManager.LoadScene("GO_Replay_AdaptiveSize");
        }
        else
        {
            errorSo.DisplayError(message.error ?? "Error opening replay");
        }
    }
}
