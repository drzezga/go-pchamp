using System.Collections.Generic;
using Game.GameState;
using ServerConnection.Messages;
using TMPro;
using UnityEngine;

namespace MainMenu
{
    public class ScoreController : MonoBehaviour
    {
        [SerializeField] private GameScoreSO gameScoreSo;

        [SerializeField] private TMP_Text firstPlayer;
        [SerializeField] private TMP_Text secondPlayer;
    
        private void OnEnable()
        {
            Debug.Log(gameScoreSo);
            if (gameScoreSo.Value.Count == 0 || gameScoreSo.Value == null)
            {
                gameObject.SetActive(false);
            }
            else
            {
                firstPlayer.text = gameScoreSo.Value[0].name + ": " + gameScoreSo.Value[0].score;
                secondPlayer.text = gameScoreSo.Value[1].name + ": " + gameScoreSo.Value[1].score;
            }
        }

        public void ClosePopup()
        {
            gameScoreSo.Value = new List<ScorePlayer>();
            gameObject.SetActive(false);
        }
    }
}
