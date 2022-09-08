using ServerFrontEnd.Model;

namespace ServerFrontEnd.Operations.Cloning;

public static class CloningExtension
{
    public static CloningOperation Cloning(this Repository repository)
    {
        return (CloningOperation)repository.operations[CloningOperation.TYPE];
    }
    
    public static void Cloning(this Repository repository,CloningOperation value)
    {
        repository.operations[CloningOperation.TYPE] = value;
    }
    
}