using System.Collections;
using System.Collections.Generic;
using ServerConnection.Messages;
using UnityEngine;

public class PassTurnButton : MonoBehaviour
{
    [SerializeField] private MessageSenderSO messageSenderSo;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void PassTurn()
    {
        messageSenderSo.SendMessage(new GamePassTurnRequestMessage());
    }
}
