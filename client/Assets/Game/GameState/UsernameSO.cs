using UnityEngine;

namespace Game.GameState
{
    [CreateAssetMenu(fileName = "Data", menuName = "GO/UsernameSO", order = 1)]
    public class UsernameSO : SingleValueSO<string>
    { }
}