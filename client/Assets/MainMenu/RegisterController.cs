using System;
using Game.GameState;
using ServerConnection.Messages;
using TMPro;
using UnityEngine;
namespace MainMenu
{
    public class RegisterController : MonoBehaviour
    {
        [SerializeField] private MessageSenderSO messageSenderSo;
        [SerializeField] private UsernameSO usernameSo;
        [SerializeField] private TMP_InputField field;

        private void OnEnable()
        {
            if (usernameSo.Value != "")
            {
                gameObject.SetActive(false);
            }
            usernameSo.OnValueChanged += UsernameChanged;
        }

        private void OnDisable()
        {
            usernameSo.OnValueChanged -= UsernameChanged;
        }

        private void UsernameChanged(string newValue)
        {
            gameObject.SetActive(false);
        }

        public void Submit()
        {
            messageSenderSo.SendMessage(new RegisterRequestMessage(field.text));
        }
    }
}
