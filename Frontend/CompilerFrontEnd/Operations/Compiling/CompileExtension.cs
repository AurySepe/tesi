using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Operations.Compiling;

public static class CompileExtension
{
    public static CompileOperation Compile(this Repository repository)
    {
        return (CompileOperation)repository.operations[CompileOperation.TYPE];
    }
    
    public static void Compile(this Repository repository,CompileOperation value)
    {
        repository.operations[CompileOperation.TYPE] = value;
    }
}