using MainMenu;
using UnityEngine;
using UnityEngine.Serialization;

public class MainMenuButtons : MonoBehaviour
{
    [SerializeField]
    private MainMenuState mainMenuState;
    
    public void QuitGame()
    {
        Application.Quit();
    }

    public void OpenLobbyList()
    {
        mainMenuState.SetMainMenuState(MainMenuState.StateEnum.LobbyList);
    }

    public void OpenReplayList()
    {
        mainMenuState.SetMainMenuState(MainMenuState.StateEnum.ReplayList);
    }
}
