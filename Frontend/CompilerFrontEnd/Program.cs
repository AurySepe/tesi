using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using CompilerFrontEnd;
using CompilerFrontEnd.Cloning;
using CompilerFrontEnd.Messages;
using CompilerFrontEnd.Model;
using CompilerFrontEnd.OperationManager;
using CompilerFrontEnd.Operations;
using CompilerFrontEnd.Operations.Compiling;
using CompilerFrontEnd.Operations.Detecting;
using CompilerFrontEnd.Operations.History;
using CompilerFrontEnd.Operations.Metrics;
using CompilerFrontEnd.Operations.ReleasesCloning;
using CompilerFrontEnd.Releases;
using CompilerFrontEnd.ViewModel;

var builder = WebAssemblyHostBuilder.CreateDefault(args);
builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

builder.Services.AddScoped(sp => new HttpClient { BaseAddress = new Uri(builder.Configuration["backupHost"]) });
builder.Services.AddSingleton<OperationConfigurationStorage>();
builder.Services.AddSingleton<RepositoryStorage>();
builder.Services.AddSingleton<MessageHandler>();
builder.Services.AddSingleton<OperationManager>();

var host = builder.Build();



RepositoryStorage repositoryStorage = host.Services.GetRequiredService<RepositoryStorage>();
HttpClient client = host.Services.GetRequiredService<HttpClient>();

MessageHandler handler = host.Services.GetRequiredService<MessageHandler>();

OperationConfigurationStorage configurationStorage = host.Services.GetRequiredService<OperationConfigurationStorage>();
configurationStorage.Configurations[CloningOperation.TYPE] = new OperationConfiguration(new CloningStarter(handler),
    new CloningMaker(), new CloningConverter(), new[] { CloningOperation.TYPE });

configurationStorage.Configurations[ReleasesOperation.TYPE] = new OperationConfiguration(new ReleasesStarter(handler),
    new ReleasesMaker(), new ReleasesConverter(), new[] { CloningOperation.TYPE, ReleasesOperation.TYPE });

configurationStorage.Configurations[ReleasesCloningOperation.TYPE] = new OperationConfiguration(
    new ReleasesCloningStarter(handler), new ReleasesCloningMaker(), new ReleaseCloningConverter(),
    new[] { CloningOperation.TYPE, ReleasesOperation.TYPE, ReleasesCloningOperation.TYPE });

configurationStorage.Configurations[CompileOperation.TYPE] = new OperationConfiguration(new CompileStarter(handler),
    new CompileMaker(), new CompileConverter(),
    new[] { CloningOperation.TYPE, ReleasesOperation.TYPE, ReleasesCloningOperation.TYPE, CompileOperation.TYPE });

configurationStorage.Configurations[DetectOperation.TYPE] = new OperationConfiguration(new DetectStarter(handler),
    new DetectMaker(), new DetectConverter(),
    new[]
    {
        CloningOperation.TYPE, ReleasesOperation.TYPE, ReleasesCloningOperation.TYPE, CompileOperation.TYPE,
        DetectOperation.TYPE
    });

configurationStorage.Configurations[HistoryOperation.TYPE] = new OperationConfiguration(new HistoryStarter(handler),
    new HistoryMaker(), new HistoryConverter(), new[]
    {
        CloningOperation.TYPE, ReleasesOperation.TYPE, ReleasesCloningOperation.TYPE, CompileOperation.TYPE,
        DetectOperation.TYPE, HistoryOperation.TYPE
    });

configurationStorage.Configurations[MetricsOperation.TYPE] = new OperationConfiguration(new MetricsStarter(handler),
    new MetricsMaker(), new MetricsConverter(), new[]
    {
        CloningOperation.TYPE, ReleasesOperation.TYPE, ReleasesCloningOperation.TYPE, CompileOperation.TYPE,
        DetectOperation.TYPE, HistoryOperation.TYPE, MetricsOperation.TYPE
    });
OperationManager operationManager = host.Services.GetRequiredService<OperationManager>();

await repositoryStorage.initAsync(client);

await handler.startConnection(operationManager);

await host.RunAsync();