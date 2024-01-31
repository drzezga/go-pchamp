using System.Collections.Generic;
using System.Linq;
using Game.GamerTile;
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
            var piece = _placedPieces[tileIndex];
            _placedPieces.Remove(tileIndex);
            Destroy(piece);
        }

        public void ClearAllPieces()
        {
            var toBeDestroyed = _placedPieces.Select(piece => piece.Value).ToList();
            _placedPieces.Clear();
            foreach (var element in toBeDestroyed)
            {
                Destroy(element);
            }
        }

        [CanBeNull]
        public GamerTileScript GetTileByIndex(Vector2Int index)
        {
            return _registeredTiles[index];
        }
    }
}
