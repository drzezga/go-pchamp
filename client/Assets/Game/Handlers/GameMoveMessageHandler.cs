using ServerConnection.Messages;
using UnityEngine;

public class GameMoveMessageHandler : MonoBehaviour
{
    [SerializeField]
    private MessageReceiverSO messageReceiverSo;

    [SerializeField]
    private GameBoardSO gameBoardSo;

    [SerializeField]
    private GameObject opponentGamePiecePrefab;

    private void OnEnable()
    {
        messageReceiverSo.OnGameMoveResponseMessage += HandleGameMoveMessage;
    }

    private void OnDisable()
    {
        messageReceiverSo.OnGameMoveResponseMessage -= HandleGameMoveMessage;
    }

    private void HandleGameMoveMessage(GameMoveResponseMessage message)
    {
        GameObject newOpponentPiece = Instantiate(opponentGamePiecePrefab);

        var animationController = newOpponentPiece.GetComponent<PieceAnimationController>();

        var gameTileIndex = new Vector2Int(
            message.content.position[0],
            message.content.position[1]
        );
        
        GamerTileScript gameTile = gameBoardSo.GetTileByIndex(gameTileIndex)!;
        animationController.PlacePiece(gameTile.transform.position);
    }

}
