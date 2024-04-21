using JokeWebApi.Controllers;
using JokeWebApi.Models;
using Microsoft.EntityFrameworkCore;

namespace JokeWebApi.Data
{
    public class BrandContext : DbContext
    {
        public BrandContext(DbContextOptions<BrandContext> options) : base(options)
        {

        }

        public DbSet<Brand> Brands { get; set; }

    }
}
