using System.Collections;
using System.Collections.Generic;
using ServerConnection.Messages;
using TMPro;
using UnityEngine;

public class ReplayListElement : MonoBehaviour
{
    [SerializeField]
    private TMP_Text replayIdText;
        
    [SerializeField]
    private TMP_Text replayPlayersText;

    [SerializeField] private MessageSenderSO messageSenderSO;

    private string _replayId;

    public void SetText(string replayId, string playerOneName, string playerTwoName, float playerOneScore, float playerTwoScore)
    {
        replayIdText.text = replayId;
        replayPlayersText.text = playerOneName + " (" + playerOneScore + ") vs " + playerTwoName + " (" + playerTwoScore + ")";
        _replayId = replayId;
    }

    public void GetReplay()
    {
        messageSenderSO.SendMessage(new ReplayGetRequestMessage(_replayId));
    }
}
