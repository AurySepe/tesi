namespace ServerFrontEnd.Operations.Cloning;

public class CloningRequest
{
    public String Origin { get; set; }

    public CloningRequest(string origin)
    {
        Origin = origin;
    }
}