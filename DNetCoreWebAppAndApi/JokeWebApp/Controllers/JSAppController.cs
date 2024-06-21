using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using JokeWebApp.Data;
using JokeWebApp.Models;

namespace JokeWebApp.Controllers
{
    public class JSAppController : Controller
    {
        // GET: Jokes
        public async Task<IActionResult> Index()
        {
            return View();
        }

    }
}
