using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(Renderer))]
public class ColorChangerScript : MonoBehaviour
{
    [SerializeField]
    private Color color = Color.white;

    private void OnEnable()
    {
        ChangeColor(color);
    }

    public void ChangeColor(Color newColor)
    {
        color = newColor;
        GetComponent<Renderer>().material.color = color;
    }
}
