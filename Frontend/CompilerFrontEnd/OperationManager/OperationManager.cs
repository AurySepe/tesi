using CompilerFrontEnd.Model;
using CompilerFrontEnd.Operations;
using CompilerFrontEnd.ViewModel;

namespace CompilerFrontEnd.OperationManager;

public class OperationManager
{
    private Dictionary<String, Queue<String>> operations = new Dictionary<string, Queue<String>>();

    private RepositoryStorage repositoryStorage;

    private OperationConfigurationStorage operationConfigurationStorage;

    public OperationManager(RepositoryStorage repositoryStorage,OperationConfigurationStorage operationConfigurationStorage)
    {
        this.repositoryStorage = repositoryStorage;
        this.operationConfigurationStorage = operationConfigurationStorage;
    }

    public void AddOperation(Repository repo, String OperationType)
    {
        Queue<String> remainingOperations = new Queue<string>();
        foreach (String remaningOperation in operationConfigurationStorage.Configurations[OperationType].OperationDependency)
        {
            RepositoryOperation oldOp = repo.operations[remaningOperation];
            if (oldOp.State != RepositoryOperation.SUCCEDED)
            {
                RepositoryOperation operation = operationConfigurationStorage.Configurations[remaningOperation].Maker
                    .MakeReadyOperation(repo);
                repositoryStorage.AddOperation(operation);
                remainingOperations.Enqueue(remaningOperation);
            }

            
        }

        operations[repo.OriginUrl] = remainingOperations;
        RepositoryOperation op = new RepositoryOperation();
        op.Repository = repo.OriginUrl;
        op.State = "";
        NotifyOperation(op);

    }

    public void NotifyOperation(RepositoryOperation update)
    {
        Repository repo = repositoryStorage.Items[update.Repository];
        Queue<String> remainingOperations = operations[repo.OriginUrl];
        if (update.State == "")
        {
            
            String operationType = remainingOperations.Peek();
            operationConfigurationStorage.Configurations[operationType].Starter.Start(repo);
            RepositoryOperation o = repo.operations[operationType];
            o.State = RepositoryOperation.PENDING;
            repositoryStorage.AddOperation(o);
            return;
        }

        repositoryStorage.AddOperation(update);
        if (update.State == RepositoryOperation.PENDING)
        {
            return;
        }
        else if (update.State == RepositoryOperation.FAILED)
        {
            operations.Remove(repo.OriginUrl);
        }
        else if (update.State == RepositoryOperation.SUCCEDED)
        {
            remainingOperations.Dequeue();
            if (remainingOperations.Count == 0)
            {
                operations.Remove(repo.OriginUrl);
                return;
            }
            String nextOperation = remainingOperations.Peek();
            operationConfigurationStorage.Configurations[nextOperation].Starter.Start(repo);
            RepositoryOperation o = repo.operations[nextOperation];
            o.State = RepositoryOperation.PENDING;
            repositoryStorage.AddOperation(o);
            return;
        }

    }

}