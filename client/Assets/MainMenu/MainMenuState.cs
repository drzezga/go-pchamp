using System;
using UnityEngine;

namespace MainMenu
{
    public class MainMenuState : MonoBehaviour
    {
        [SerializeField]
        private GameObject mainMenuUI;
        [SerializeField]
        private GameObject lobbyListUI;
        [SerializeField]
        private GameObject replayListUI;
    
        public enum StateEnum
        {
            MainMenu,
            LobbyList,
            ReplayList,
        }

        private StateEnum _state = StateEnum.MainMenu;

        public void SetMainMenuState(StateEnum newState)
        {
            SetCurrentUIActive(false);
            _state = newState;
            SetCurrentUIActive(true);
        }

        private void SetCurrentUIActive(bool newState)
        {
            switch (_state)
            {
                case StateEnum.MainMenu:
                    mainMenuUI.SetActive(newState);
                    break;
                case StateEnum.LobbyList:
                    lobbyListUI.SetActive(newState);
                    break;
                case StateEnum.ReplayList:
                    replayListUI.SetActive(newState);
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

    }
}
