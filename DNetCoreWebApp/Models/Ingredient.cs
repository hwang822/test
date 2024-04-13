namespace JokeWebApp.Models
{
    public class Ingredient
    {
        public int Id { get; set; }
        public string Name { get; set; }

        public List<DishIngredient> DashIngredients { get; set; }
    }
}
