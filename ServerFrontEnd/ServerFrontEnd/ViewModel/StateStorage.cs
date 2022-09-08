namespace ServerFrontEnd.ViewModel;

public class StateStorage
{
    public event Action? OnChange;

    public void NotifyOnChange()
    {
        OnChange?.Invoke();
    }
}