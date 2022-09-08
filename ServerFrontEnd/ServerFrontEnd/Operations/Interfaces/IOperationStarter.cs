using ServerFrontEnd.Model;

namespace ServerFrontEnd.Operations.Interfaces;

public interface IOperationStarter
{
    public void Start(Repository repository);
}