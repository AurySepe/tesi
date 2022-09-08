namespace CompilerFrontEnd.ViewModel;

public class StateStorage
{
    public event Action? OnChange;

    protected void NotifyOnChange()
    {
        OnChange?.Invoke();
    }
}