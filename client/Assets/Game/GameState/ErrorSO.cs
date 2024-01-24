using UnityEngine;

namespace Game.GameState
{
    [CreateAssetMenu(fileName = "Data", menuName = "GO/ErrorSO", order = 1)]
    public class ErrorSO : SingleValueSO<string>
    {
        public void DisplayError(string errorMessage)
        {
            Value = errorMessage;
        }
    }
}