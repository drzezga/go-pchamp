
using System;
using System.Text;
using System.Threading;
using DG.Tweening;
using UnityEngine;

[RequireComponent(typeof(PieceAnimationController))]
public class CurrentPlayerPieceScript : MonoBehaviour
{

    [SerializeField]
    private SelectedGamerTileSO _selectedGamerTileSo;

    [SerializeField]
    private Color _color;

    private PieceAnimationController _pieceAnimationController;

    public void PlaceItselfOnTile(GameObject tile)
    {
        if(!enabled)
        {
            return;
        }
        
        _pieceAnimationController.PlacePiece(tile.transform.position);
        enabled = false;
    }

    private void OnEnable()
    {
        _pieceAnimationController = GetComponent<PieceAnimationController>();
        GetComponent<MeshRenderer>().material.color = _color;
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
