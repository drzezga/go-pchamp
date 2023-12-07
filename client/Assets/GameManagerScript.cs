using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManagerScript : MonoBehaviour
{
    public PieceScript piece;

    public GameObject piecePrefab;
    
    [SerializeField]
    private GameManagerApiSO _gameManagerApiSo;

    private void OnEnable()
    {
        _gameManagerApiSo.OnPlacePieceOnTileRequested += PlacePieceOnTile;
    }

    private void OnDisable()
    {
        _gameManagerApiSo.OnPlacePieceOnTileRequested -= PlacePieceOnTile;
    }

    public void PlacePieceOnTile(GamerTileScript tile)
    {
        // piece.PlayShakeAnimation();
        //
        piece.PlaceItselfOnTile(tile.gameObject);
        
        GameObject newPieceGameObject = Instantiate(piecePrefab);
        
        piece = newPieceGameObject.GetComponent<PieceScript>();
    }

}
