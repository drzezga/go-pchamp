using UnityEngine;

namespace MainMenu
{
    public class GoBackButton : MonoBehaviour
    {
        public MainMenuState state;

        public void OnClick()
        {
            state.SetMainMenuState(MainMenuState.StateEnum.MainMenu);
        }
    }
}
