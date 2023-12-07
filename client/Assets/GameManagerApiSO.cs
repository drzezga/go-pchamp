using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu(fileName = "Data", menuName = "GO/GameManagerApiSO", order = 1)]
public class GameManagerApiSO : ScriptableObject
{
    public delegate void PlacePieceOnTileHandler(GamerTileScript tile);
    public event PlacePieceOnTileHandler OnPlacePieceOnTileRequested; 
    public void PlacePieceOnTile(GamerTileScript tile) { OnPlacePieceOnTileRequested?.Invoke(tile); }
}
