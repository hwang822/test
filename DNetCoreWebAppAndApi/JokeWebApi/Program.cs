using JokeWebApi.Data;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();


// Add services to the container.
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection") ?? throw new InvalidOperationException("Connection string 'DefaultConnection' not found.");
    builder.Services.AddDbContext<BrandContext>(options =>
        options.UseSqlServer(connectionString));

// Add services to the container.
//builder.Services.AddDbContext<JockContext>(options =>
//    options.UseSqlServer(connectionString));

builder.Services.AddSwaggerGen(c =>
{
	c.AddSecurityDefinition("ApiKey", new OpenApiSecurityScheme
	{
		Description = "ApiKey to secure the webapi.sevices.",
		Type = SecuritySchemeType.ApiKey,
		Name = "ApiKey",
		In = ParameterLocation.Header,
		Scheme = "ApiKeyScheme"
	});

	var scheme = new OpenApiSecurityScheme()
	{
		Reference = new OpenApiReference()
		{
			Type = ReferenceType.SecurityScheme,
			Id = "ApiKey",
		},
		In = ParameterLocation.Header,

	};

	var requirement = new OpenApiSecurityRequirement()

		{
			{scheme, new List<string>(){""} }
		};

	c.AddSecurityRequirement(requirement);

});


var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
