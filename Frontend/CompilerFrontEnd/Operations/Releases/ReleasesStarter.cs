using System.Text.Json;
using CompilerFrontEnd.Messages;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.Operations;

namespace CompilerFrontEnd.Releases;

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