using CompilerFrontEnd.Model;

namespace CompilerFrontEnd.Releases;

public static class ReleasesExtension
{
    
        public static ReleasesOperation Releases(this Repository repository)
        {
            return (ReleasesOperation)repository.operations[ReleasesOperation.TYPE];
        }
    
        public static void Releases(this Repository repository,ReleasesOperation value)
        {
            repository.operations[ReleasesOperation.TYPE] = value;
        }
    
    
}