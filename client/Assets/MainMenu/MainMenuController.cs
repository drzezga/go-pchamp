using UnityEngine;

namespace MainMenu
{
    public class MainMenuController : MonoBehaviour
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
}
