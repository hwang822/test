using JokeWebApi.Data;
using JokeWebApi.Models;
using JokeWebApi.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;

namespace JokeWebApi.Controllers
{
    //[ServiceFilter(typeof(AuthorizeAttribute))]
    [Authorize]
	[Route("api/[controller]/[action]")]
    [ApiController]
    public class BrandController : ControllerBase
    {
        private readonly BrandContext _dbContext;

        public BrandController(BrandContext dbContext)
        {
            _dbContext = dbContext;
        }

        [HttpGet]
        public async Task<ActionResult<Brand>> GetBrands()
        {
            if (_dbContext.Brands == null)
            {
                return NotFound();
            }

            var brand = await _dbContext.Brands.ToListAsync();    ;

            if ((brand == null) || brand.Count == 0)
            {
                return NotFound();
            }
            return Ok(brand); ;
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Brand>> GetBrand(int id)
        {
            if (_dbContext.Brands == null)
            {
                return NotFound();
            }
            var brand = await _dbContext.Brands.FindAsync(id);

            if (brand == null)
            {
                return NotFound();
            }

            return brand;
        }

        [HttpPost]
        public async Task<ActionResult<Brand>> PostBrand(Brand brand)
        {
            if (brand == null)
            {
                return NotFound();
            }
            _dbContext.Brands.Add(brand);
            await _dbContext.SaveChangesAsync();

            return brand;
        }

        [HttpPut("{id}")]
        public async Task<ActionResult<Brand>> PutBrand(int id, Brand brand)
        {
            if (brand == null)
            {
                return BadRequest();
            }

            if(id != brand.Id)
            {
                return BadRequest();
            }

            _dbContext.Entry(brand).State = EntityState.Modified;

            try
            {
                await _dbContext.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (BrandAvaiable(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return Ok();
        }



        private bool BrandAvaiable(int id)
        {
            return (_dbContext.Brands?.Any(x => x.Id == id)).GetValueOrDefault();
        }

        [HttpDelete("{id}")]
        public async Task<ActionResult<Brand>> DeleteBrand(int id)
        {
            if (_dbContext.Brands == null)
            {
                return NotFound();
            }

            var brand = await _dbContext.Brands.FindAsync(id);

            if (brand == null)
            {
                return NotFound();
            }

            _dbContext.Brands.Remove(brand);

            await _dbContext.SaveChangesAsync();

            return Ok();
        }
    }

}
