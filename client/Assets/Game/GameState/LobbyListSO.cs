using System.Collections.Generic;
using ServerConnection.Messages;
using UnityEngine;

namespace Game.GameState
{
    [CreateAssetMenu(fileName = "Data", menuName = "GO/LobbyListSO", order = 1)]
    public class LobbyListSO : SingleValueSO<List<Lobby>>
    { }
}