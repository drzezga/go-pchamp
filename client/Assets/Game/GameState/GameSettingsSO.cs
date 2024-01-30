using ServerConnection.Messages;
using UnityEngine;

namespace Game.GameState
{
    [CreateAssetMenu(fileName = "Data", menuName = "GO/GameSettingsSO", order = 1)]
    public class GameSettingsSO : SingleValueSO<GameSettings>
    {
        public bool inGame;

        public Color GetColorOfPlayer(string playerName)
        {
            return Value.startingPlayer == playerName ? Color.black : Color.white;
        }
    }
}