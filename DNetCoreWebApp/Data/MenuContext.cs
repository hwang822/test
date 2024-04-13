using JokeWebApp.Models;
using Microsoft.EntityFrameworkCore;

namespace JokeWebApp.Data
{
/*
    public class MenuContext : DbContext
    {
        public MenuContext(DbContextOptions <MenuContext> options ) : base(options)
        {

        }
        protected override void OnModelCreating (ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<DishIngredient>().HasKey(di => new
            {
                di.DishId,
                di.IngredientId

            });
         
            modelBuilder.Entity<DishIngredient>().HasOne(d => d.Dish).WithMany(di => di.DashIngredients).HasForeignKey(d => d.DishId);
            modelBuilder.Entity<DishIngredient>().HasOne(i => i.Ingredient).WithMany(di => di.DashIngredients).HasForeignKey(i => i.DishId);

            modelBuilder.Entity<Dish>().HasData(
                new Dish { Id = 1, Name = "Margheritta", Price = 7.50, 
                ImageUrl = "https://foodbyjonister.com/wp-content/uploads/2020/01/MargheritaPizza.jpg" });

            modelBuilder.Entity<Ingredient>().HasData(
                new Ingredient{Id = 1, Name = "Tomato Sauce"},
                new Ingredient { Id = 1, Name = "Mozzarella" });

            modelBuilder.Entity<DishIngredient>().HasData(

                new DishIngredient { DishId = 1, IngredientId = 1 },
                new DishIngredient { DishId = 1, IngredientId = 2 });

            base.OnModelCreating (modelBuilder);
        }

        public DbSet<Dish> Dishes { get; set; }
        public DbSet<Ingredient> Ingredients { get; set; }
        public DbSet<DishIngredient> DashIngredients { get; set; }
    }
*/

}
