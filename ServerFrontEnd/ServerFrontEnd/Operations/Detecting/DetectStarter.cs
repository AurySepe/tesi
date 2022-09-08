using System.Text.Json;
using ServerFrontEnd.Messages;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Cloning;
using ServerFrontEnd.Operations.Compiling;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Operations.Detecting;

public class DetectStarter : IOperationStarter
{

    private MessageHandler handler;

    public DetectStarter(MessageHandler handler)
    {
        this.handler = handler;
    }

    public void Start(Repository repository)
    {
        DetectRequest request = new DetectRequest(repository.Compile().Data.BuildPath,repository.OriginUrl,repository.Cloning().Data.ResultName);
        String requestJson = JsonSerializer.Serialize(request);
        handler.requestOperation(requestJson, "detect");
    }
}