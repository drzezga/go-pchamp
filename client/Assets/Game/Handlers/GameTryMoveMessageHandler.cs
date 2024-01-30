using System;
using System.Collections;
using System.Collections.Generic;
using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;

public class GameTryMoveMessageHandler : MonoBehaviour
{
    [SerializeField]
    private MessageReceiverSO messageReceiverSo;

    [SerializeField] private ErrorSO errorSo;

    private void OnEnable()
    {
        messageReceiverSo.OnGameTryMoveResponseMessage += HandleGameTryMoveMessage;
    }

    private void OnDisable()
    {
        messageReceiverSo.OnGameTryMoveResponseMessage -= HandleGameTryMoveMessage;
    }

    private void HandleGameTryMoveMessage(GameTryMoveResponseMessage message)
    {
        errorSo.DisplayError(message.error ?? "Undisclosed Game Move error");
    }
}
