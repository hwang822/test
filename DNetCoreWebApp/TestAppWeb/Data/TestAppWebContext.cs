using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using TestAppWeb.Models;

namespace TestAppWeb.Data
{
    public class TestAppWebContext : DbContext
    {
        public TestAppWebContext (DbContextOptions<TestAppWebContext> options)
            : base(options)
        {
        }

        public DbSet<TestAppWeb.Models.Joke> Joke { get; set; } = default!;
    }
}
