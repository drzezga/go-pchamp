using System.Collections.Generic;
using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.Serialization;

namespace MainMenu
{
    public class LobbyListDisplay : MonoBehaviour
    {
        [SerializeField] private LobbyListSO lobbyListSo;

        [SerializeField] private GameObject elementContainer;

        [SerializeField] private GameObject listElementPrefab;
        private void OnEnable()
        {
            lobbyListSo.OnValueChanged += Handle;
        }

        private void Handle(List<Lobby> lobbies)
        {
            foreach (Transform child in elementContainer.transform)
            {
                if (child != null)
                    Destroy(child.gameObject);
            }

            foreach (var lobby in lobbies)
            {
                var child = Instantiate(listElementPrefab, elementContainer.transform, false);
                child.GetComponent<LobbyListElement>().SetText(lobby.name, lobby.playerCount.ToString());
            }
        }

    }
}
