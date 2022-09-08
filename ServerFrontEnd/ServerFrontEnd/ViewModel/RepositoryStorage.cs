using System.Text.Json;
using ServerFrontEnd.Model;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.ViewModel;

public class RepositoryStorage : StateStorage
{
    public Dictionary<string,Repository> Items { get; set; } = new ();

    private OperationConfigurationStorage configurationStorage;


    public RepositoryStorage(OperationConfigurationStorage configurationStorage)
    {
        this.configurationStorage = configurationStorage;
    }
    
    public void AddItem(Repository item)
    {
        Items.Add(item.OriginUrl,item);
        item.operations = MakeInitialDictionary(item);
        NotifyOnChange();
    }

    public void AddOperation(RepositoryOperation operation)
    {
        Repository repository = Items[operation.Repository];
        repository.operations[operation.Type] = operation;
        NotifyOnChange();
    }

    public void RemoveItem(Repository item)
    {
        Items.Remove(item.OriginUrl);
        NotifyOnChange();
    }

    public void ResetList()
    {
        Items.Clear();
        NotifyOnChange();
    }

    // public async Task initAsync(HttpClient client)
    // {
    //     var repos = await client.GetFromJsonAsync<Repository[]>("getAllRepos");
    //     foreach (Repository repo in repos)
    //     {
    //         System.Console.WriteLine(repo);
    //         AddItem(repo);
    //     }
    //     
    //
    //     var cloningOperations = await client.GetFromJsonAsync<RepositoryOperation[]>("getAllOperations");
    //     foreach (RepositoryOperation operation in cloningOperations)
    //     {
    //         String operationS = JsonSerializer.Serialize(operation);
    //         RepositoryOperation newOperation = configurationStorage.Configurations[operation.Type].Converter.Convert(operationS);
    //         AddOperation(newOperation);            
    //     }
    //
    //
    // }

    private Dictionary<String, RepositoryOperation> MakeInitialDictionary(Repository repo)
    {
        Dictionary<String, RepositoryOperation> result = new Dictionary<string, RepositoryOperation>();
        foreach (String type in OperationConfigurationStorage.TYPES)
        {
            result[type] = configurationStorage.Configurations[type].Maker.MakeCreatedOperation(repo);
        }

        return result;
    }

}