using ServerConnection.Messages;
using TMPro;
using UnityEngine;

namespace MainMenu
{
    public class LobbyListElement : MonoBehaviour
    {
        [SerializeField]
        private TMP_Text lobbyNameText;
        
        [SerializeField]
        private TMP_Text playerCountText;

        [SerializeField] private MessageSenderSO messageSenderSO;

        private string lobbyName;

        public void SetText(string lobbyName, string playerCount)
        {
            lobbyNameText.text = lobbyName;
            playerCountText.text = playerCount;
            this.lobbyName = lobbyName;
        }

        public void JoinLobby()
        {
            messageSenderSO.SendMessage(new LobbyJoinRequestMessage(lobbyName));
        }
    }
}