using System.Collections;
using System.Collections.Generic;
using Game.GameState;
using UnityEngine;

public class AllStateHolder : MonoBehaviour
{
    [SerializeField]
    private CurrentLobbySO _currentLobbySO;

    private CurrentReplaySO _currentReplaySO;
    [SerializeField]

    private ErrorSO _errorSO;
    [SerializeField]

    private GameBoardSO _gameBoardSO;
    [SerializeField]

    private GameScoreSO _gameScoreSO;
    [SerializeField]

    private GameSettingsSO _gameSettings;
    [SerializeField]

    private LobbyListSO _lobbyListSO;
    [SerializeField]

    private ReplayListSO _replayListSO;
    [SerializeField]

    private SelectedGamerTileSO _selectedGamerTileSO;
    [SerializeField]

    private UsernameSO _usernameSO;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
