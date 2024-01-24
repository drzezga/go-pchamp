using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;

namespace MainMenu.Handlers
{
    public class LobbyListMessageHandler : MonoBehaviour
    {
        [SerializeField]
        private MessageReceiverSO messageReceiverSo;

        [SerializeField] private LobbyListSO lobbyListSo;

        private void OnEnable()
        {
            messageReceiverSo.OnLobbyListResponseMessage += HandleMessage;
        }

        private void OnDisable()
        {
            messageReceiverSo.OnLobbyListResponseMessage -= HandleMessage;
        }

        private void HandleMessage(LobbyListResponseMessage message)
        {
            lobbyListSo.Value = message.content;
        }
    }
}