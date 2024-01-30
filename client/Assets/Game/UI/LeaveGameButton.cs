using System.Collections;
using System.Collections.Generic;
using ServerConnection.Messages;
using UnityEngine;

public class LeaveGameButton : MonoBehaviour
{
    [SerializeField] private MessageSenderSO messageSenderSo;

    public void LeaveGame()
    {
        messageSenderSo.SendMessage(new GameLeaveRequestMessage());
    }
}
