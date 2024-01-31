using ServerConnection.Messages;
using UnityEngine;

namespace Game.GameState
{
    [CreateAssetMenu(fileName = "Data", menuName = "GO/CurrentReplaySO", order = 1)]
    public class CurrentReplaySO : SingleValueSO<ReplayGetResponseMessageContent> {
    
    }
}
