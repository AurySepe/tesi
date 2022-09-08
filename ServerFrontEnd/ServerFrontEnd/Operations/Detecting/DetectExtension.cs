using ServerFrontEnd.Model;

namespace ServerFrontEnd.Operations.Detecting;

public static class DetectExtension
{
    public static DetectOperation Detecting(this Repository repository)
    {
        return (DetectOperation)repository.operations[DetectOperation.TYPE];
    }
    
    public static void Detecting(this Repository repository,DetectOperation value)
    {
        repository.operations[DetectOperation.TYPE] = value;
    }
}