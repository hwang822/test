namespace JokeWebApp.Models
{
    public class DishIngredient
    {
        public int Id { get; set; }
        public int DishId { get; set; }
        public int IngredientId { get; set; }

        public Dish Dish { get; set; }
        public Ingredient Ingredient { get; set; } = new Ingredient();

        public DishIngredient() { }        
    }
}
