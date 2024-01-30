using Game.GameState;
using TMPro;
using UnityEngine;

using DG.Tweening;

namespace MainMenu
{
    public class ErrorToast : MonoBehaviour
    {
        [SerializeField] private TMP_Text errorTextObject;
        [SerializeField] private ErrorSO errorSo;

        private Sequence sequence;
    
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

            sequence?.Kill();
            sequence = DOTween.Sequence();
            sequence.Append(errorTextObject.DOFade(1.0f, 0.5f));
            sequence.AppendInterval(3.0f);
            sequence.Append(errorTextObject.DOFade(0.0f, 2.0f));
        }
    }
}
