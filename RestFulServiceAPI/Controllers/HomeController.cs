using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestFulServiceAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class HomeController : ControllerBase
    {
        private static readonly string[] Names = new[]
        {
            "Henry", "Wang", "Delan", "Yin"
        };

        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        public ActionResult<IEnumerable<string>> Get()
        {
            return Names;
        }

        [HttpGet("{id}")]
        public ActionResult<string> Get(int id)
        {
            return Names[id];
        }

        [HttpPost]
        public void Post(string name)
        {
            return;
        }

        [HttpPut("{id}")]
        public void Put(int id, string name)
        {
            Names[id] = name;
            return;
        }

        [HttpDelete("{id}")]
        public ActionResult<string> Delete(int id)
        {
            return Names[id];
        }

    }

}
