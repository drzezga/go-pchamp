
using Game.GameState;
using UnityEngine;
using UnityEngine.Serialization;

[RequireComponent(typeof(PieceAnimationController))]
public class CurrentPlayerPieceScript : MonoBehaviour
{

    [SerializeField] private SelectedGamerTileSO selectedGamerTileSo;
    [SerializeField] private GameSettingsSO gameSettingsSo;
    [SerializeField] private UsernameSO usernameSo;

    private PieceAnimationController _pieceAnimationController;

    private void Start()
    {
        _pieceAnimationController = GetComponent<PieceAnimationController>();
        SetPlayerColor(usernameSo.Value);
    }

    private void OnEnable()
    {
        selectedGamerTileSo.OnValueChanged += HandleSelectedGamerTileChanged;
    }

    private void OnDisable()
    {
        selectedGamerTileSo.OnValueChanged -= HandleSelectedGamerTileChanged;
    }

    private void HandleSelectedGamerTileChanged(GameObject newSelection)
    {
        Vector3 gamerTilePosition = newSelection.transform.position;
        Vector3 endPiecePosition = new Vector3(gamerTilePosition.x, transform.position.y, gamerTilePosition.z);
        _pieceAnimationController.MoveToPosition(endPiecePosition);
    }

    private void SetPlayerColor(string username)
    {
        GetComponent<MeshRenderer>().material.color = gameSettingsSo.GetColorOfPlayer(username);
    }
}
