using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using NativeWebSocket;
using UnityEngine;

public class GameManagerScript : MonoBehaviour
{
    public PieceScript piece;

    public GameObject piecePrefab;
    
    [SerializeField]
    private GameManagerApiSO _gameManagerApiSo;

    private WebSocket _socket;

    private async void Start()
    {
        _socket = new WebSocket("ws://localhost:8000");
        _socket.OnMessage += message =>
        {
            Debug.Log(message);
        };
        
        _socket.OnOpen += async () =>
        {
            Debug.Log("Connection open!");
            await _socket.SendText("Hello world");
        };
        
        await _socket.Connect();

    }

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
