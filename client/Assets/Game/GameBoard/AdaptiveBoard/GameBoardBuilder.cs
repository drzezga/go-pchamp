using System.Collections;
using System.Collections.Generic;
using Game.GamerTile;
using Game.GameState;
using UnityEngine;
using UnityEngine.Serialization;

public class GameBoardBuilder : MonoBehaviour
{
    [SerializeField] private float tileSize = 0.125f;
    [SerializeField] private float backboardMargin = 0.75f;
    [SerializeField] private float lineWidth = 0.015f;

    [SerializeField] private GameObject horizontalLinePrefab;
    [SerializeField] private GameObject verticalLinePrefab;
    [SerializeField] private GameObject dotPrefab;
    [SerializeField] private GameObject tilePrefab;
    [SerializeField] private GameObject backboard;

    [SerializeField] private GameBoardSO gameBoardSo;
    
    private int _boardSize = 19;
    
    public void Build(int newBoardSize)
    {
        _boardSize = newBoardSize;
        
        ScaleBackboard();

        CreateTiles();
        
        CreateVerticalLines();
        
        CreateHorizontalLines();

        ScaleBoard();
    }

    private void ScaleBackboard()
    {
        var backboardSize = (_boardSize - 1) / (tileSize * 10f) + backboardMargin / GetGlobalScaleFactor();
        backboard.transform.localScale = new Vector3(backboardSize, 1, backboardSize);
    }

    private void CreateTiles()
    {
        var self = gameObject;

        for (var y = 0; y < _boardSize; y++) // down -> -z
        {
            for (var x = 0; x < _boardSize; x++) // right -> +x
            {
                var tile = Instantiate(tilePrefab, self.transform, false);
                tile.transform.position = new Vector3(x - (_boardSize - 1) / 2f, 0, (_boardSize - 1) / 2f - y);
                
                var tileScript = tile.GetComponent<GamerTileScript>();

                tileScript.boardIndex = new Vector2Int(x, y);

                gameBoardSo.RegisterGamerTile(tileScript);
            }
        }
    }

    private void CreateVerticalLines()
    {
        var self = gameObject;

        for (var x = 0; x < _boardSize; x++) // right -> +x
        {
            var tile = Instantiate(verticalLinePrefab, self.transform, false);
            tile.transform.position = new Vector3(x - (_boardSize - 1) / 2f, 0, 0);
            
            var scale = tile.transform.localScale;
            scale.z = (_boardSize - 1) / (tileSize * 10f) + lineWidth;
            tile.transform.localScale = scale;
        }
    }
    
    private void CreateHorizontalLines()
    {
        var self = gameObject;

        for (var y = 0; y < _boardSize; y++) // down -> -z
        {
            var tile = Instantiate(horizontalLinePrefab, self.transform, false);
            tile.transform.position = new Vector3(0, 0, y - (_boardSize - 1) / 2f);
            
            var scale = tile.transform.localScale;
            scale.z = (_boardSize - 1) / (tileSize * 10f) + lineWidth;
            tile.transform.localScale = scale;
        }
    }

    private float GetGlobalScaleFactor()
    {
        return 19f / _boardSize;
    }

    private void ScaleBoard()
    {
        const float scaleFactor = 0.75f;
        transform.localScale = new Vector3(GetGlobalScaleFactor() * scaleFactor, GetGlobalScaleFactor() * scaleFactor, GetGlobalScaleFactor() * scaleFactor);
    }
}
