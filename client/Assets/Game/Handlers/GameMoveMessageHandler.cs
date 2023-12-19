
using UnityEngine;

public class GameMoveMessageHandler : MonoBehaviour
{
    [SerializeField]
    private MessageDecoderSO messageDecoderSo;

    [SerializeField]
    private GameBoardSO gameBoardSo;

    [SerializeField]
    private GameObject opponentGamePiecePrefab;

    private void OnEnable()
    {
        messageDecoderSo.OnGameMoveResponseMessage += HandleGameMoveMessage;
    }

    private void OnDisable()
    {
        messageDecoderSo.OnGameMoveResponseMessage -= HandleGameMoveMessage;
    }

    private void HandleGameMoveMessage(ResponseMessage<GameMoveResponseMessageContent> message)
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
