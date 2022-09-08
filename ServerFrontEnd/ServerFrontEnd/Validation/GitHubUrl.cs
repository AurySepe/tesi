using System.ComponentModel.DataAnnotations;

namespace ServerFrontEnd.Validation;

public class GitHubUrl
{
    [Required]
    [RegularExpression("^https://github.com/[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+.git$")]
    public String Url { get; set; }
}