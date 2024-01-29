using System;
using System.Collections;
using System.Collections.Generic;
using Game.GameState;
using ServerConnection.Messages;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class LobbyController : MonoBehaviour
{
    [SerializeField] private TMP_Dropdown startingPlayer;
    [SerializeField] private TMP_InputField boardSize;
    [SerializeField] private Toggle playAgainstBot;
    [SerializeField] private TMP_Text lobbyNameText;

    [SerializeField] private MessageSenderSO messageSenderSO;

    [SerializeField] private UsernameSO usernameSO;

    [SerializeField] private CurrentLobbySO currentLobbySO;

    [SerializeField] private ErrorSO errorSO;

    private void OnEnable()
    {
        UpdateContent(currentLobbySO.Value);

        currentLobbySO.OnValueChanged += UpdateContent;
    }

    private void OnDisable()
    {
        currentLobbySO.OnValueChanged -= UpdateContent;
    }

    private void UpdateContent(LobbyDetails lobbyDetails)
    {
        lobbyNameText.text = lobbyDetails.name;

        if (lobbyDetails.players.Find(x => x.name == usernameSO.Value).isHost) return;

        startingPlayer.interactable = false;
        boardSize.interactable = false;
        playAgainstBot.interactable = false;
    }

    void LeaveLobby()
    {
        messageSenderSO.SendMessage(new LobbyLeaveRequestMessage(currentLobbySO.name));
    }

    void StartGame()
    {
        var boardSizeInt = 19;
        try
        {
            boardSizeInt = int.Parse(boardSize.text);
        }
        catch
        {
            errorSO.DisplayError("The provided board size is invalid");
        }

        if (boardSizeInt <= 0)
        {
            errorSO.DisplayError("The provided board size is too small");
            return;
        }

        if (currentLobbySO.Value.players.Count != 2 && !playAgainstBot.isOn)
        {
            errorSO.DisplayError("There are not enough players to start the game.");
            return;
        }

        var otherPlayer = currentLobbySO.Value.players.Find(x => x.name != usernameSO.Value).name;
        
        messageSenderSO.SendMessage(new GameStartRequestMessage(new GameSettings
        {
            size = boardSizeInt,
            startingPlayer = startingPlayer.value == 0 ? usernameSO.Value : otherPlayer,
            botOpponent = playAgainstBot.isOn,
        }));
    }
}
