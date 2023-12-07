
using UnityEngine;

public class SingleValueSO<T> : ScriptableObject
{
    [TextArea] public string description = "N/A";
    
    public T Value {
        get => value;
        set
        {
            this.value = value;
            OnValueChanged?.Invoke(value);
        }
    }
    [SerializeField] protected T value;

    public delegate void OnValueChangedDelegate(T newValue);
    public event OnValueChangedDelegate OnValueChanged;
}

[CreateAssetMenu(fileName = "Data", menuName = "SingleValueSO/IntegerSO", order = 1)]
public class IntegerSO : SingleValueSO<int> {}


[CreateAssetMenu(fileName = "Data", menuName = "SingleValueSO/DoubleSO", order = 1)]
public class DoubleSO : SingleValueSO<double> {}


[CreateAssetMenu(fileName = "Data", menuName = "SingleValueSO/BooleanSO", order = 1)]
public class BooleanSO : SingleValueSO<bool> {}


[CreateAssetMenu(fileName = "Data", menuName = "SingleValueSO/StringSO", order = 1)]
public class StringSO : SingleValueSO<bool> {}

[CreateAssetMenu(fileName = "Data", menuName = "SingleValueSO/ColorSO", order = 1)]
public class ColorSO : SingleValueSO<Color> {}