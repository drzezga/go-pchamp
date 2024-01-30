using System;
using System.Collections;
using System.Collections.Generic;
using DG.Tweening;
using Game.GameState;
using TMPro;
using UnityEngine;

public class ErrorToast : MonoBehaviour
{
    [SerializeField] private TMP_Text errorTextObject;
    [SerializeField] private ErrorSO errorSo;
    
    private void OnEnable()
    {
        errorSo.OnValueChanged += DisplayError;
    }

    private void OnDisable()
    {
        errorSo.OnValueChanged -= DisplayError;
    }

    private void DisplayError(string errorText)
    {
        errorTextObject.text = errorText;
    }
}
