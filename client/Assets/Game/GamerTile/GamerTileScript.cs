
using System;
using UnityEngine;

public class GamerTileScript : MonoBehaviour
{
    [SerializeField]
    private MessageSenderSO messageSenderSo;
    
    [SerializeField]
    private SelectedGamerTileSO selectedGamerTileSo;

    [SerializeField]
    private GameBoardSO gameBoardSo;
    
    public Vector2Int boardIndex;

    private bool _armed = false;

    private void OnEnable()
    {
        boardIndex = new Vector2Int(
            Int32.Parse(name.Substring(4)),
            Int32.Parse(transform.parent.name)
        );
        
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
            messageSenderSo.SendMessage(new GameTryMoveRequestMessage(boardIndex));
        }
        
        _armed = false;
    }
}
