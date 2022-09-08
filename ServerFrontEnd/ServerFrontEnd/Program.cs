using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;
using ServerFrontEnd.Messages;
using ServerFrontEnd.OperationManager;
using ServerFrontEnd.Operations.Cloning;
using ServerFrontEnd.Operations.Compiling;
using ServerFrontEnd.Operations.Detecting;
using ServerFrontEnd.Operations.History;
using ServerFrontEnd.Operations.Interfaces;
using ServerFrontEnd.Operations.Metrics;
using ServerFrontEnd.Operations.Releases;
using ServerFrontEnd.Operations.ReleasesCloning;
using ServerFrontEnd.Operations.ReleasesDeleter;
using ServerFrontEnd.ViewModel;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages();
builder.Services.AddServerSideBlazor();
// builder.Services.AddScoped(sp => new HttpClient { BaseAddress = new Uri("http://repository:5000/") });
builder.Services.AddSingleton<OperationConfigurationStorage>();
builder.Services.AddSingleton<RepositoryStorage>();
builder.Services.AddSingleton<MessageHandler>();
builder.Services.AddSingleton<OperationManager>();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseRouting();

app.MapBlazorHub();
app.MapFallbackToPage("/_Host");

RepositoryStorage repositoryStorage = app.Services.GetRequiredService<RepositoryStorage>();
// HttpClient client = app.Services.GetRequiredService<HttpClient>();

MessageHandler handler = app.Services.GetRequiredService<MessageHandler>();

OperationConfigurationStorage configurationStorage = app.Services.GetRequiredService<OperationConfigurationStorage>();
configurationStorage.Configurations[CloningOperation.TYPE] = new OperationConfiguration(new CloningStarter(handler),
    new CloningMaker(), new CloningConverter(), new[] { CloningOperation.TYPE });

configurationStorage.Configurations[ReleasesOperation.TYPE] = new OperationConfiguration(new ReleasesStarter(handler),
    new ReleasesMaker(), new ReleasesConverter(), new[] { CloningOperation.TYPE, ReleasesOperation.TYPE });

configurationStorage.Configurations[HistoryOperation.TYPE] = new OperationConfiguration(new HistoryStarter(handler),
    new HistoryMaker(), new HistoryConverter(), new[]
    {
        CloningOperation.TYPE, ReleasesOperation.TYPE, HistoryOperation.TYPE
    });

configurationStorage.Configurations[ReleasesCloningOperation.TYPE] = new OperationConfiguration(
    new ReleasesCloningStarter(handler), new ReleasesCloningMaker(), new ReleaseCloningConverter(),
    new[] { CloningOperation.TYPE, ReleasesOperation.TYPE,HistoryOperation.TYPE, ReleasesCloningOperation.TYPE });

configurationStorage.Configurations[CompileOperation.TYPE] = new OperationConfiguration(new CompileStarter(handler),
    new CompileMaker(), new CompileConverter(),
    new[] { CloningOperation.TYPE, ReleasesOperation.TYPE,HistoryOperation.TYPE, ReleasesCloningOperation.TYPE, CompileOperation.TYPE });

configurationStorage.Configurations[DetectOperation.TYPE] = new OperationConfiguration(new DetectStarter(handler),
    new DetectMaker(), new DetectConverter(),
    new[]
    {
        CloningOperation.TYPE, ReleasesOperation.TYPE,HistoryOperation.TYPE, ReleasesCloningOperation.TYPE, CompileOperation.TYPE,
        DetectOperation.TYPE
    });

configurationStorage.Configurations[MetricsOperation.TYPE] = new OperationConfiguration(new MetricsStarter(handler),
    new MetricsMaker(), new MetricsConverter(), new[]
    {
        CloningOperation.TYPE, ReleasesOperation.TYPE,HistoryOperation.TYPE, ReleasesCloningOperation.TYPE, CompileOperation.TYPE,
        DetectOperation.TYPE, MetricsOperation.TYPE
    });

configurationStorage.Configurations[ReleasesDeleterOperation.TYPE] = new OperationConfiguration(new ReleasesDelterStarter(handler),new ReleasesDeleterMaker(),new ReleasesDeleterConverter(),new []{
    CloningOperation.TYPE, ReleasesOperation.TYPE,HistoryOperation.TYPE, ReleasesCloningOperation.TYPE, CompileOperation.TYPE,
    DetectOperation.TYPE, MetricsOperation.TYPE, ReleasesDeleterOperation.TYPE
});
OperationManager operationManager = app.Services.GetRequiredService<OperationManager>();
MessageBackgroundService messageBackgroundService = new MessageBackgroundService(handler, operationManager);

Thread thread1 = new Thread(messageBackgroundService.DoWork);
thread1.Start();

// await repositoryStorage.initAsync(client);


app.Run();