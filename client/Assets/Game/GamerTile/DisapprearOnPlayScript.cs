using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DisapprearOnPlayScript : MonoBehaviour
{
    void Start()
    {
        GetComponent<Renderer>().enabled = false;
    }
}
