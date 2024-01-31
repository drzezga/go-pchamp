using System;
using Game.GameState;
using UnityEngine;

namespace Game.GameBoard.AdaptiveBoard
{
    [RequireComponent(typeof(GameBoardBuilder))]
    public class BoardInitializer : MonoBehaviour
    {
        // private GameBoardBuilder builder;

        // [SerializeField] private int boardSize = 19;

        [SerializeField] private GameSettingsSO gameSettingsSo;
        private void Awake()
        {
            var builder = GetComponent<GameBoardBuilder>();

            builder.Build(gameSettingsSo.Value.size);
        }
    }
}
