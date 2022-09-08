using System.Text.Json;
using ServerFrontEnd.Messages;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Releases;

public class ReleasesStarter : IOperationStarter
{
    private MessageHandler messageHandler;

    public ReleasesStarter(MessageHandler messageHandler)
    {
        this.messageHandler = messageHandler;
    }

    public void Start(Repository repository)
    {
        ReleasesRequest request = new ReleasesRequest(repository.OriginUrl);
        String requestJson = JsonSerializer.Serialize(request);
        messageHandler.requestOperation(requestJson, "release");
    }
}