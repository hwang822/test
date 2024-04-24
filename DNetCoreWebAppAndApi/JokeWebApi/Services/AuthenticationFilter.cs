using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.Azure.AppService.ApiApps.Service;
namespace JokeWebApi.Services
{
	public class AuthorizeAttribute : Attribute, IAsyncAuthorizationFilter
	{
		public async Task OnAuthorizationAsync(AuthorizationFilterContext context)
		{

			var providedApiKey = context.HttpContext.Request.Headers["ApiKey"].FirstOrDefault();
			var isValed = true; // IsValidApiKey(providedApiKey);
			if (!isValed)
			{
				context.Result = new UnauthorizedObjectResult("Invalid Authertication");
			}
			return;

		}

		private bool IsValidApiKey(string providedApiKey)
		{
			if (string.IsNullOrEmpty(providedApiKey))
			{
				return false;
			}

			var config = new ConfigurationBuilder()
				.SetBasePath(Directory.GetCurrentDirectory())
				.AddJsonFile("appsettings.json").Build();
			var validApiKey = config["AuthenticationKey"];
			return string.Equals(validApiKey, providedApiKey, StringComparison.Ordinal);
		}

		private static async Task GeneerateResponse(HttpContext context, int httpStatusCode, string msg)
		{
			context.Response.StatusCode = httpStatusCode;
			await context.Response.WriteAsync(msg);
		}


	}



}
