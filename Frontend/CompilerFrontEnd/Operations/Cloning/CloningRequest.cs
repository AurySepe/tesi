namespace CompilerFrontEnd.Model.Requests;

public class CloningRequest
{
    public String Origin { get; set; }

    public CloningRequest(string origin)
    {
        Origin = origin;
    }
}