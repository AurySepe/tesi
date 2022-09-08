using ServerFrontEnd.Model;
using ServerFrontEnd.Operations;
using ServerFrontEnd.Operations.Interfaces;
using ServerFrontEnd.ViewModel;

namespace ServerFrontEnd.OperationManager;

public class OperationManager
{
    private Dictionary<String, Queue<String>> operations = new Dictionary<string, Queue<String>>();

    private RepositoryStorage repositoryStorage;

    private OperationConfigurationStorage operationConfigurationStorage;

    private List<Repository> allActiveRepository = new List<Repository>();

    private String allOperationType;

    public bool isWorking = false;

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
            if (oldOp.State == RepositoryOperation.CREATED )
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
            if (allActiveRepository.Count != 0 && allActiveRepository[0].OriginUrl.Equals(repo.OriginUrl))
            {
                repo.Status = Repository.PENDING;
                repositoryStorage.NotifyOnChange();
            }
            else
            {
                isWorking = false;
            }

            if (remainingOperations.Count == 0)
            {
                operations.Remove(repo.OriginUrl);
                if (allActiveRepository.Count != 0 && allActiveRepository[0].OriginUrl.Equals(repo.OriginUrl))
                {
                    repo.Status = Repository.SUCCEDED;
                    allActiveRepository.RemoveAt(0);
                    if (allActiveRepository.Count != 0)
                    {
                        AddOperation(allActiveRepository[0],allOperationType);
                    }
                    else
                    {
                        isWorking = false;
                    }
                    repositoryStorage.NotifyOnChange();
                }
                
                return;
            }
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
            if (allActiveRepository.Count != 0 && allActiveRepository[0].OriginUrl.Equals(repo.OriginUrl))
            {
                repo.Status = Repository.FAILED;
                allActiveRepository.RemoveAt(0);
                if (allActiveRepository.Count != 0)
                {
                    AddOperation(allActiveRepository[0],allOperationType);
                }
                else
                {
                    isWorking = false;
                }
                repositoryStorage.NotifyOnChange();
            }
            
        }
        else if (update.State == RepositoryOperation.SUCCEDED)
        {
            remainingOperations.Dequeue();
            if (remainingOperations.Count == 0)
            {
                operations.Remove(repo.OriginUrl);
                if (allActiveRepository.Count != 0 && allActiveRepository[0].OriginUrl.Equals(repo.OriginUrl))
                {
                    repo.Status = Repository.SUCCEDED;
                    allActiveRepository.RemoveAt(0);
                    if (allActiveRepository.Count != 0)
                    {
                        AddOperation(allActiveRepository[0],allOperationType);
                    }
                    else
                    {
                        isWorking = false;
                    }
                    repositoryStorage.NotifyOnChange();
                }
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

    public void startAll(String OperationType)
    {
        allActiveRepository = new List<Repository>();
        foreach (Repository repository in repositoryStorage.Items.Values)
        {
            if (repository.Status == Repository.CREATED)
            {
                allActiveRepository.Add(repository);
                repository.Status = Repository.READY;
            }
        }

        if (allActiveRepository.Count != 0)
        {
            allOperationType = OperationType;
            AddOperation(allActiveRepository[0],allOperationType);
            isWorking = true;
        }
    }

}