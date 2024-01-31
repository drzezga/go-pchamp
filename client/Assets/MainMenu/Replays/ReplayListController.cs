using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu.Replays
{
    public class ReplayListController : MonoBehaviour
    {
        [SerializeField] private MessageSenderSO messageSenderSo;
        private void OnEnable()
        {
            Refresh();
        }

        public void Refresh()
        {
            messageSenderSo.SendMessage(new ReplayListRequestMessage());
        }
    }
}
