using System.Collections;
using System.Collections.Generic;
using JetBrains.Annotations;
using Unity.VisualScripting;
using UnityEngine;

[CreateAssetMenu(menuName = "GO/GameBoardSO")]
public class GameBoardSO : ScriptableObject
{
    private Dictionary<Vector2Int, GamerTileScript> _registeredTiles = new();

    public void RegisterGamerTile(GamerTileScript tile)
    {
        _registeredTiles[tile.boardIndex] = tile;
    }

    [CanBeNull]
    public GamerTileScript GetTileByIndex(Vector2Int index)
    {
        return _registeredTiles[index];
    }
}
