using System.Collections.Generic;
using JetBrains.Annotations;
using Unity.VisualScripting;
using UnityEngine;

namespace Game.GameState
{
    [CreateAssetMenu(menuName = "GO/GameBoardSO")]
    public class GameBoardSO : ScriptableObject
    {
        private readonly Dictionary<Vector2Int, GamerTileScript> _registeredTiles = new();
        
        private readonly Dictionary<Vector2Int, GameObject> _placedPieces = new();

        public void RegisterGamerTile(GamerTileScript tile)
        {
            _registeredTiles[tile.boardIndex] = tile;
        }

        public void RegisterPlacedPiece(Vector2Int tileIndex, GameObject piece)
        {
            _placedPieces[tileIndex] = piece;
        }

        public void RemovePlacedPiece(Vector2Int tileIndex)
        {
            if (_placedPieces[tileIndex] == null) return;
            _placedPieces.Remove(tileIndex);
            Destroy(_placedPieces[tileIndex]);

        }

        [CanBeNull]
        public GamerTileScript GetTileByIndex(Vector2Int index)
        {
            return _registeredTiles[index];
        }
    }
}
