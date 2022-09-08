namespace ServerFrontEnd.Operations.Interfaces;

public class OperationConfiguration
{
    public IOperationStarter Starter { get; set; }
    
    public IOperationMaker Maker { get; set; }
    
    public IOperationConverter Converter { get; set; }
    
    public String[] OperationDependency { get; set; }

    public OperationConfiguration(IOperationStarter starter, IOperationMaker maker, IOperationConverter converter, string[] operationDependency)
    {
        Starter = starter;
        Maker = maker;
        Converter = converter;
        OperationDependency = operationDependency;
    }
    
    
}