namespace ServerFrontEnd.Operations.Interfaces;

public class RepositoryOperation
{
    public String Repository { get; set; }
    public String State { get; set; }
    public String Type { get; set; }

    private String result;
    public String Result
    {
        get
        {
            result = ResultToJson();
            return result;
        }
        set
        {
            result = value;
            JsonToResult(value);
        }
    }

    public RepositoryOperation(string repository, string state, string type)
    {
        Repository = repository;
        State = state;
        Type = type;
    }

    public RepositoryOperation()
    {
    }

    protected virtual void JsonToResult(String value)
    {
    }

    protected virtual String ResultToJson()
    {
        return result;
    }
    
    public const String CREATED = "CREATED";
    public const String READY = "READY";
    public const String PENDING = "PENDING";
    public const String SUCCEDED = "SUCCEDED";
    public const String FAILED = "FAILED";
}