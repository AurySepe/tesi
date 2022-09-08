using System.Text.RegularExpressions;
using ServerFrontEnd.Operations.Interfaces;

namespace ServerFrontEnd.Model;

public class Repository
{
    public Dictionary<String, RepositoryOperation> operations { get; set; } =
        new Dictionary<string, RepositoryOperation>();

    public Repository(string originUrl, string author, string name, string status)
    {
        OriginUrl = originUrl;
        Author = author;
        Name = name;
        Status = status;
    }

    public String OriginUrl { get; set; }

    public String Author { get; set; }

    public String Name { get; set; }
    
    public String Status { get; set; }

    public static Repository UrlToRepo(String url)
    {
        Regex pattern = new Regex("^https://github.com/[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+.git$");
        if (!pattern.IsMatch(url))
        {
            throw new ArgumentException("invalid url");
        }
        Regex authorRegex = new Regex("https://github.com/(.*)/[A-Za-z0-9_.-]+.git");
        var authorMathc = authorRegex.Match(url);
        string author = authorMathc.Groups[1].ToString();
        Regex nameRegex = new Regex("https://github.com/[A-Za-z0-9_.-]+/(.*).git");
        var nameMathc = nameRegex.Match(url);
        string name =  nameMathc.Groups[1].ToString();
        Repository result = new Repository(originUrl:url,author:author,name:name,CREATED);
        return result;
    }
    
    public const String CREATED = "CREATED";
    
    public const String READY = "READY";
    
    public const String FAILED = "FAILED";
    
    public const String PENDING = "PENDING";

    public const String SUCCEDED = "SUCCEDED";


}