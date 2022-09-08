using System.Text.Json;
using CompilerFrontEnd.Messages;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.Releases;
using CompilerFrontEnd.Services.RepositoryExtensions;

namespace CompilerFrontEnd.Operations.ReleasesCloning;

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
        String[] releases = repository.Releases().Data.Commits;
        ReleasesCloningRequest request =
            new ReleasesCloningRequest(repository.OriginUrl, repositoryName, releases);
        String requestJson = JsonSerializer.Serialize(request);
        messageHandler.requestOperation(requestJson, "releaseCloning");
    }
}