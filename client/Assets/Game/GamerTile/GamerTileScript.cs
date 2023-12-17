
using UnityEngine;

public class GamerTileScript : MonoBehaviour
{
    [SerializeField]
    private GameManagerApiSO _gameManagerApiSo;
    
    [SerializeField]
    private SelectedGamerTileSO _selectedGamerTileSo;

    private bool _armed = false;
    
    private void OnMouseEnter()
    {
        Debug.Log($"Mouse entered {name}");

        _selectedGamerTileSo.Value = gameObject;
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
            _gameManagerApiSo.PlacePieceOnTile(this);
        }
        
        _armed = false;
    }
}
