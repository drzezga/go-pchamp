using System.Collections.Generic;
using ServerConnection.Messages;
using UnityEngine;

namespace Game.GameState
{
    [CreateAssetMenu(menuName = "GO/GameScoreSO", order = 1)]
    public class GameScoreSO : SingleValueSO<List<ScorePlayer>>
    {
    
    }
}
