
using NativeWebSocket;
using UnityEngine;

public class GameManagerScript : MonoBehaviour
{
    public PieceScript piece;

    public GameObject piecePrefab;

    [SerializeField]
    private GameManagerApiSO gameManagerApiSo;

    [SerializeField]
    private ServerConnectionChannelSO serverServerConnectionChannelSo;

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
        Debug.Log($"Received message {message}");
    }

    public void PlacePieceOnTile(GamerTileScript tile)
    {
        // piece.PlayShakeAnimation();
        //
        piece.PlaceItselfOnTile(tile.gameObject);
        
        GameObject newPieceGameObject = Instantiate(piecePrefab);
        
        piece = newPieceGameObject.GetComponent<PieceScript>();
    }

}
