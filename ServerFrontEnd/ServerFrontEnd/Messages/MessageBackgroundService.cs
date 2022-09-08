namespace ServerFrontEnd.Messages;

public class MessageBackgroundService
{
    private readonly MessageHandler _handler;

    private readonly OperationManager.OperationManager OperationManager;

    public MessageBackgroundService(MessageHandler handler, OperationManager.OperationManager operationManager)
    {
        _handler = handler;
        OperationManager = operationManager;
    }


    public void DoWork()
    {
        _handler.startConnection(OperationManager);
    }
}