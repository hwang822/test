using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using TestAppWeb.Data;
using TestAppWeb.Models;
using Microsoft.AspNetCore.Authorization;

namespace TestAppWeb.Controllers
{
    public class StudentParentsController : Controller
    {
/*
        private readonly ApplicationDbContext _context;

        public StudentParentsController(ApplicationDbContext context)
        {
            _context = context;
        }

        // GET: StudentParent
        public async Task<IActionResult> Index()
        {
              return _context.StudentParent != null ? 
                          View(await _context.StudentParent.ToListAsync()) :
                          Problem("Entity set 'ApplicationDbContext.StudentParent'  is null.");
        }

        // GET: StudentParent/Search
        public async Task<IActionResult> Search()
        {
            return View(await _context.StudentParent.ToListAsync());
        }

        // POST: StudentParent/Results
        public async Task<IActionResult> Results(String searchPhrase)
        {
            return View("Index", await _context.StudentParent.Where( j => j.Name.Contains
            (searchPhrase)).ToListAsync());
        }

        // GET: StudentParent/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null || _context.StudentParent == null)
            {
                return NotFound();
            }

            var studentParents = await _context.StudentParent
                .FirstOrDefaultAsync(m => m.Id == id);
            if (studentParents == null)
            {
                return NotFound();
            }

            return View(studentParents);
        }

        // GET: StudentParent/Create
        [Authorize]
        public IActionResult Create()
        {
            return View();
        }

        // POST: StudentParent/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [Authorize]
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Name,Infomation")] StudentParent studentParents)
        {
            if (ModelState.IsValid)
            {
                _context.Add(studentParents);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(studentParents);
        }

        // GET: StudentParent/Edit/5
        [Authorize]
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null || _context.StudentParent == null)
            {
                return NotFound();
            }

            var studentParents = await _context.StudentParent.FindAsync(id);
            if (studentParents == null)
            {
                return NotFound();
            }
            return View(studentParents);
        }

        // POST: StudentParent/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [Authorize]
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Name,Infomation")] StudentParent studentParents)
        {
            if (id != studentParents.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(studentParents);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!StudentParentsExists(studentParents.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(studentParents);
        }

        // GET: StudentParent/Delete/5
        [Authorize]
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null || _context.StudentParent == null)
            {
                return NotFound();
            }

            var studentParents = await _context.StudentParent
                .FirstOrDefaultAsync(m => m.Id == id);
            if (studentParents == null)
            {
                return NotFound();
            }

            return View(studentParents);
        }

        // POST: StudentParent/Delete/5
        [Authorize]
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (_context.StudentParent == null)
            {
                return Problem("Entity set 'ApplicationDbContext.StudentParent'  is null.");
            }
            var studentParents = await _context.StudentParent.FindAsync(id);
            if (studentParents != null)
            {
                _context.StudentParent.Remove(studentParents);
            }
            
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool StudentParentsExists(int id)
        {
          return (_context.StudentParent?.Any(e => e.Id == id)).GetValueOrDefault();
        }
*/
    }

}
