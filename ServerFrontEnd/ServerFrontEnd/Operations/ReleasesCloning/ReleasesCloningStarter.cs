using System.Text.Json;
using ServerFrontEnd.Messages;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Cloning;
using ServerFrontEnd.Operations.History;
using ServerFrontEnd.Operations.Interfaces;
using ServerFrontEnd.Operations.Releases;

namespace ServerFrontEnd.Operations.ReleasesCloning;

public class ReleasesCloningStarter : IOperationStarter
{
    
    private MessageHandler messageHandler;

    public ReleasesCloningStarter(MessageHandler messageHandler)
    {
        this.messageHandler = messageHandler;
    }
    
    public void Start(Repository repository)
    {
        String repositoryName = repository.Cloning().Data.ResultName;
        ReleasesCloningRequest request =
            new ReleasesCloningRequest(repository.OriginUrl, repositoryName,repository.History().Data.OrderedReleasePath ,repository.Name);
        String requestJson = JsonSerializer.Serialize(request);
        messageHandler.requestOperation(requestJson, "releaseCloning");
    }
}