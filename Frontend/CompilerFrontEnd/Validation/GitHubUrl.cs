using System.ComponentModel.DataAnnotations;

namespace CompilerFrontEnd.Validation;

public class GitHubUrl
{
    [Required]
    [RegularExpression("^https://github.com/\\w+/\\w+.git$")]
    public String Url { get; set; }
}