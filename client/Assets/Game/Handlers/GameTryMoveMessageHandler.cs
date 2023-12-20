using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameTryMoveMessageHandler : MonoBehaviour
{
    [SerializeField]
    private MessageReceiverSO messageReceiverSo;

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
        Debug.Log($"WRONG MOVE: {message.content.error}");
    }
}
