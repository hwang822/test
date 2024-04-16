using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace JokeWebApp.Models
{
	public class JokeApp
	{

        [Required]
        public int Id { get; set; }

        [Required]
        [DisplayName("Joke Question")]

        public string JokeQuestion { get; set; }

        [Required]
        [DisplayName("Joke Answer")]
        public string JokeAnswer { get; set; }


	}
}
