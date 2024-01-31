using System.Collections;
using System.Collections.Generic;
using ServerConnection.Messages;
using UnityEngine;
using UnityEngine.Serialization;

public class ReplayListDisplay : MonoBehaviour
{
    [SerializeField] private ReplayListSO replayListSo;

    [SerializeField] private GameObject elementContainer;

    [SerializeField] private GameObject listElementPrefab;
    private void OnEnable()
    {
        replayListSo.OnValueChanged += Handle;
    }
    
    private void OnDisable()
    {
        replayListSo.OnValueChanged -= Handle;
    }

    private void Handle(List<ReplayBrief> replays)
    {
        foreach (Transform child in elementContainer.transform)
        {
            Destroy(child.gameObject);
        }

        foreach (var replay in replays)
        {
            var child = Instantiate(listElementPrefab, elementContainer.transform, false);
            child.GetComponent<ReplayListElement>().SetText(
                replay.id,
                replay.players[0].name,
                replay.players[1].name,
                replay.players[0].score,
                replay.players[1].score
            );
        }
    }

}
