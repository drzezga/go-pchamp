
using DG.Tweening;
using UnityEngine;

public class PieceScript : MonoBehaviour
{
    [Range(0.0f, 1.0f)]
    public float tileTransitionDuration = 0.5f;
    
    [Range(0.0f, 1.0f)]
    public float shakeDuration = 0.5f;

    [Range(0.0f, 1.0f)]
    public float placePieceDuration = 0.5f;

    [SerializeField]
    private SelectedGamerTileSO _selectedGamerTileSo;

    public void PlaceItselfOnTile(GameObject tile)
    {
        if(!enabled)
        {
            return;
        }
        
        transform.DOMove(tile.transform.position, tileTransitionDuration).SetEase(Ease.OutCubic);
        enabled = false;
    }

    public void PlayShakeAnimation()
    {
        transform.DOShakePosition(shakeDuration, new Vector3(1, 0, 0));
    }
    
    private void OnEnable()
    {
        _selectedGamerTileSo.OnValueChanged += HandleSelectedGamerTileChanged;
    }

    private void OnDisable()
    {
        _selectedGamerTileSo.OnValueChanged -= HandleSelectedGamerTileChanged;
    }

    private void HandleSelectedGamerTileChanged(GameObject newSelection)
    {
        Vector3 gamerTilePosition = newSelection.transform.position;
        Vector3 endPiecePosition = new Vector3(gamerTilePosition.x, transform.position.y, gamerTilePosition.z);
        transform.DOMove(endPiecePosition, tileTransitionDuration).SetEase(Ease.OutCubic);
    }
}
