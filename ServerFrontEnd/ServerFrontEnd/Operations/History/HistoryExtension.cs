using ServerFrontEnd.Model;

namespace ServerFrontEnd.Operations.History;

public static class HistoryExtension
{
    public static HistoryOperation History(this Repository repository)
    {
        return (HistoryOperation)repository.operations[HistoryOperation.TYPE];
    }
    
    public static void History(this Repository repository,HistoryOperation value)
    {
        repository.operations[HistoryOperation.TYPE] = value;
    }
}