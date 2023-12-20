
using UnityEngine;

[RequireComponent(typeof(PieceAnimationController))]
public class CurrentPlayerPieceScript : MonoBehaviour
{

    [SerializeField]
    private SelectedGamerTileSO _selectedGamerTileSo;

    [SerializeField]
    private Color _color;

    private PieceAnimationController _pieceAnimationController;

    private void Start()
    {
        _pieceAnimationController = GetComponent<PieceAnimationController>();
        GetComponent<MeshRenderer>().material.color = _color;
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
        _pieceAnimationController.MoveToPosition(endPiecePosition);
    }
}
