using JokeWebApp.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace JokeWebApp.Controllers
{
    public class JokeApiController : Controller
    {
        Uri baseAddress = new Uri("https://localhost:7281/api");

        private readonly HttpClient _client;

        public JokeApiController()
        {
            _client = new HttpClient();
            _client.BaseAddress = baseAddress;
        }

        [HttpGet]
        public IActionResult Index()
        {
            List<JokeApi> jokeList = new List<JokeApi>();
            HttpResponseMessage respone = _client.GetAsync(_client.BaseAddress + "/Jokes/GetAllJokes").Result;
            //api url action format  [Route("api/[controller]/[action]")] 

            if (respone.IsSuccessStatusCode)
            {
                string data = respone.Content.ReadAsStringAsync().Result;
                jokeList = JsonConvert.DeserializeObject<List<JokeApi>>(data);
            }
            return View(jokeList);
        }
    }
}
