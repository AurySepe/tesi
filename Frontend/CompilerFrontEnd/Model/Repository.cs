using System.Text.RegularExpressions;

namespace CompilerFrontEnd.Model;

public class Repository
{
    public Dictionary<String, RepositoryOperation> operations { get; set; } =
        new Dictionary<string, RepositoryOperation>();

    public Repository(string originUrl, string author, string name)
    {
        OriginUrl = originUrl;
        Author = author;
        Name = name;
    }

    public String OriginUrl { get; set; }

    public String Author { get; set; }

    public String Name { get; set; }

    public static Repository UrlToRepo(String url)
    {
        Regex pattern = new Regex("^https://github.com/\\w+/\\w+.git$");
        if (!pattern.IsMatch(url))
        {
            throw new ArgumentException("invalid url");
        }
        Regex authorRegex = new Regex("https://github.com/(.*)/\\w+.git");
        var authorMathc = authorRegex.Match(url);
        string author = authorMathc.Groups[1].ToString();
        Regex nameRegex = new Regex("https://github.com/\\w+/(.*).git");
        var nameMathc = nameRegex.Match(url);
        string name =  nameMathc.Groups[1].ToString();
        Repository result = new Repository(originUrl:url,author:author,name:name);
        return result;
    }
    

}