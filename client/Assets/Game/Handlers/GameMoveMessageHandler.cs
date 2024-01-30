using Game.GameState;
using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.Serialization;

public class GameMoveMessageHandler : MonoBehaviour
{
    [SerializeField]
    private MessageReceiverSO messageReceiverSo;

    [SerializeField]
    private GameBoardSO gameBoardSo;

    [SerializeField]
    private GameObject boardPiecePrefab;

    [SerializeField] private GameSettingsSO gameSettingsSo;
    
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
        var newOpponentPiece = Instantiate(boardPiecePrefab);

        var colorChanger = newOpponentPiece.GetComponent<ColorChangerScript>();
        
        colorChanger.ChangeColor(gameSettingsSo.GetColorOfPlayer(message.content.player));

        var animationController = newOpponentPiece.GetComponent<PieceAnimationController>();

        var gameTileIndex = new Vector2Int(
            message.content.position[0],
            message.content.position[1]
        );
        
        var gamerTile = gameBoardSo.GetTileByIndex(gameTileIndex)!;
        animationController.PlacePiece(gamerTile.transform.position);
    }

}
