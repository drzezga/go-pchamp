using System;
using Game.GameState;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;

namespace Game.Replay
{
    public class ReplayController : MonoBehaviour
    {
        [SerializeField]
        private CurrentReplaySO currentReplaySo;

        [SerializeField] private GameBoardSO gameBoardSo;

        [SerializeField] private GameObject boardPiecePrefab;

        [SerializeField] private TMP_Text currentTurnText;
    
        private int _turnIndex = 0;

        private void Start()
        {
            ApplyCurrentTurn();
        }

        public void NextTurn()
        {
            if (currentReplaySo.Value.moves.Count <= _turnIndex) return;
            _turnIndex++;
            ApplyCurrentTurn();
        }

        public void PreviousTurn()
        {
            if (_turnIndex <= 0) return;
            _turnIndex--;
            ApplyCurrentTurn();
        }

        private void ApplyCurrentTurn()
        {
            currentTurnText.text = (_turnIndex + 1).ToString();
            gameBoardSo.ClearAllPieces();

            var boardState = currentReplaySo.Value.boardStates[_turnIndex];

            foreach (var white in boardState.white)
            {
                CreatePiece(new Vector2Int(white[0], white[1]), Color.white);
            }
            
            foreach (var black in boardState.black)
            {
                CreatePiece(new Vector2Int(black[0], black[1]), Color.black);
            }
        }

        private void CreatePiece(Vector2Int index, Color color)
        {
            var newOpponentPiece = Instantiate(boardPiecePrefab);

            var colorChanger = newOpponentPiece.GetComponent<ColorChangerScript>();
        
            colorChanger.ChangeColor(color);

            var animationController = newOpponentPiece.GetComponent<PieceAnimationController>();

            var gamerTile = gameBoardSo.GetTileByIndex(index)!;
            animationController.PlacePiece(gamerTile.transform.position);
        
            gameBoardSo.RegisterPlacedPiece(index, newOpponentPiece);
        }
    }
}
