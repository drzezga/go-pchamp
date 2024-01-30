using System;
using System.Collections;
using System.Collections.Generic;
using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameLeaveMessageHandler : MonoBehaviour
{
    [SerializeField] private MessageReceiverSO messageReceiverSo;

    private void OnEnable()
    {
        messageReceiverSo.OnGameLeaveResponseMessage += Handle;
    }

    private void OnDisable()
    {
        messageReceiverSo.OnGameLeaveResponseMessage -= Handle;
    }

    private void Handle(GameLeaveResponseMessage message)
    {
        SceneManager.LoadScene("MainMenu");
    }
}
