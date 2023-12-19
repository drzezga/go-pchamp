
using NativeWebSocket;
using UnityEngine;

public class GameManagerScript : MonoBehaviour
{
    public CurrentPlayerPieceScript currentPlayerPiece;

    public GameObject piecePrefab;

    [SerializeField]
    private GameManagerApiSO gameManagerApiSo;

    [SerializeField]
    private ServerConnectionChannelSO serverServerConnectionChannelSo;

    [SerializeField]
    private MessageSenderSO messageSenderSo;

    private WebSocket _socket;

    private void OnEnable()
    {
        serverServerConnectionChannelSo.OnMessageReceived += ReceiveMessage;
        gameManagerApiSo.OnPlacePieceOnTileRequested += PlacePieceOnTile;
    }

    private void OnDisable()
    {
        serverServerConnectionChannelSo.OnMessageReceived -= ReceiveMessage;
        gameManagerApiSo.OnPlacePieceOnTileRequested -= PlacePieceOnTile;
    }
    
    private void ReceiveMessage(string message)
    {
        
    }

    public void PlacePieceOnTile(GamerTileScript tile)
    {
        // piece.PlayShakeAnimation();
        //
        // currentPlayerPiece.PlaceItselfOnTile(tile.gameObject);
        //
        // GameObject newPieceGameObject = Instantiate(piecePrefab);
        //
        // currentPlayerPiece = newPieceGameObject.GetComponent<CurrentPlayerPieceScript>();

        var message = new GameTryMoveRequestMessage(tile.boardIndex);

        messageSenderSo.SendMessage(message);
        
    }

}
