using System.Collections;
using System.Collections.Generic;
using ServerConnection.Messages;
using UnityEngine;

[CreateAssetMenu(fileName = "Data", menuName = "GO/ReplayListSO", order = 1)]
public class ReplayListSO : SingleValueSO<List<ReplayBrief>>
{
}
