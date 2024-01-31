using UnityEngine;
using UnityEngine.SceneManagement;

namespace Game.Replay
{
    public class LeaveReplayButton : MonoBehaviour
    {
        public void Leave()
        {
            SceneManager.LoadScene("MainMenu");
        }
    }
}
