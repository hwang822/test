using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using JokeWebApi.Models;

namespace JokeWebApi.Controllers
{


    [Route("api/[controller]")]
    [ApiController]
    public class JokesController : ControllerBase
    {
        [HttpGet]

        //public async Task<IActionResult> GetAllJokes()
        public async Task<ActionResult<List<Joke>>> GetAllJokes()
        {
            var jokes = new List<Joke>{
                new Joke
                {
                    Id = 1,
                    JokeQuestion = "Who is John?",
                    JokeAnswer = "He is a dog"
                }
            };
            return Ok(jokes);
        }
    }
}
