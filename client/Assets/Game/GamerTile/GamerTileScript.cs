
using System;
using UnityEngine;

public class GamerTileScript : MonoBehaviour
{
    [SerializeField]
    private GameManagerApiSO gameManagerApiSo;
    
    [SerializeField]
    private SelectedGamerTileSO selectedGamerTileSo;

    [SerializeField]
    private GameBoardSO gameBoardSo;
    
    public Vector2Int boardIndex;

    private bool _armed = false;

    private void OnEnable()
    {
        gameBoardSo.RegisterGamerTile(this);
    }

    private void OnMouseEnter()
    {
        selectedGamerTileSo.Value = gameObject;
    }

    private void OnMouseExit()
    {
        _armed = false;
    }

    private void OnMouseDown()
    {
        _armed = true;
    }
    
    private void OnMouseUp()
    {
        if(_armed)
        {
            gameManagerApiSo.PlacePieceOnTile(this);
        }
        
        _armed = false;
    }
}
