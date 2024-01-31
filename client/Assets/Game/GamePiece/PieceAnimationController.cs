using System.Collections;
using System.Collections.Generic;
using DG.Tweening;
using UnityEngine;

public class PieceAnimationController : MonoBehaviour
{
    [Range(0.0f, 1.0f)]
    public float tileTransitionDuration = 0.5f;
    
    [Range(0.0f, 1.0f)]
    public float shakeDuration = 0.5f;

    [Range(0.0f, 1.0f)]
    public float placePieceDuration = 0.5f;

    public void MoveToPosition(Vector3 targetPosition)
    {
        transform.DOMove(targetPosition, tileTransitionDuration).SetEase(Ease.OutCubic);
    }
    
    public void PlayShakeAnimation()
    {
        transform.DOShakePosition(shakeDuration, new Vector3(1, 0, 0));
    }

    public void PlacePiece(Vector3 targetPosition)
    {
        transform.position = targetPosition + Vector3.up;
        transform.DOMove(targetPosition, placePieceDuration).SetEase(Ease.OutCubic);
    }
}
