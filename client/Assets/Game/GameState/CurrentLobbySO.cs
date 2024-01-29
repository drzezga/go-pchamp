using ServerConnection.Messages;
using UnityEngine;

namespace Game.GameState
{
    [CreateAssetMenu(fileName = "Data", menuName = "GO/CurrentLobbySO", order = 1)]
    public class CurrentLobbySO : SingleValueSO<LobbyDetails>
    { }
}