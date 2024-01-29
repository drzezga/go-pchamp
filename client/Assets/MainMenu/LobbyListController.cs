using System;
using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu
{
    public class LobbyListController : MonoBehaviour
    {
        [SerializeField] private MessageSenderSO messageSenderSo;
        [SerializeField] private UsernameSO usernameSo;
        private void OnEnable()
        {
            Refresh();
        }

        public void Refresh()
        {
            messageSenderSo.SendMessage(new LobbyListRequestMessage());
        }

        public void CreateLobby()
        {
            messageSenderSo.SendMessage(new LobbyJoinRequestMessage(usernameSo + "'s lobby"));
        }
    }
}
