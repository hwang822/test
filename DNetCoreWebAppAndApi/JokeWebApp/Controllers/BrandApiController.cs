//using JokeWebApp.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using JokeWebApi.Models;

namespace JokeWebApp.Controllers
{
    public class BrandApiController : Controller
    {
        Uri baseAddress = new Uri("https://localhost:7281/api");

        private readonly HttpClient _client;

        public BrandApiController()
        {
            _client = new HttpClient();
            _client.BaseAddress = baseAddress;
        }

        [HttpGet]
        public IActionResult Index()
        {
            List<Brand> brandList = new List<Brand>(); //brand data from api\modles\brand
            HttpResponseMessage respone = _client.GetAsync(_client.BaseAddress + "/Brand/GetBrands").Result;
            if (respone.IsSuccessStatusCode)
            {
                string data = respone.Content.ReadAsStringAsync().Result;
                brandList = JsonConvert.DeserializeObject<List<Brand>>(data);
            }
            return View(brandList);
        }
    }
}
