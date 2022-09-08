using System.Text.Json;
using ServerFrontEnd.Messages;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Cloning;
using ServerFrontEnd.Operations.Detecting;
using ServerFrontEnd.Operations.Interfaces;
using ServerFrontEnd.Operations.Releases;

namespace ServerFrontEnd.Operations.History;

public class HistoryStarter : IOperationStarter
{
    
    private MessageHandler handler;

    public HistoryStarter(MessageHandler handler)
    {
        this.handler = handler;
    }

    public void Start(Repository repository)
    {
        String clonePath = Path.Join(repository.Cloning().Data.ResultName,repository.Name);
        HistoryRequest request = new HistoryRequest(repository.OriginUrl,repository.Releases().Data.Commits,repository.Cloning().Data.ResultName,clonePath);
        String requestJson = JsonSerializer.Serialize(request);
        handler.requestOperation(requestJson, "history");
    }
}