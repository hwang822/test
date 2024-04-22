using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using JokeWebApp.Models;

namespace JokeWebApp.Data
{
	public class ApplicationDbContext : IdentityDbContext
	{
		public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
			: base(options)
		{
		}
	    public DbSet<JokeWebApp.Models.JokeApp> Joke { get; set; } = default!;
    }
}
