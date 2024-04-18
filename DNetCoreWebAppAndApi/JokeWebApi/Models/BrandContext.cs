using JokeWebApi.Controllers;
using Microsoft.EntityFrameworkCore;

namespace JokeWebApi.Models
{
    public class BrandContext : DbContext
    {
        public BrandContext(DbContextOptions<BrandContext> options) : base(options)
        {
            
        }

        public DbSet<Brand> Brands {  get; set; }
        
    }
}
