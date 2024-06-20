namespace JokeWebApi.Models
{
	public class Brand
	{
		public int Id { get; set; }
		public string? Name { get; set; }
		public string Category { get; set; }
		public int IsActive { get; set; }

	}



	public class Rootobject
	{
		public Quiz quiz { get; set; }
	}

	public class Quiz
	{
		public Sport sport { get; set; }
		public Maths maths { get; set; }
	}

	public class Sport
	{
		public Q1 q1 { get; set; }
	}

	public class Q1
	{
		public string question { get; set; }
		public string[] options { get; set; }
		public string answer { get; set; }
	}

	public class Maths
	{
		public Q11 q1 { get; set; }
		public Q2 q2 { get; set; }
	}

	public class Q11
	{
		public string question { get; set; }
		public string[] options { get; set; }
		public string answer { get; set; }
	}

	public class Q2
	{
		public string question { get; set; }
		public string[] options { get; set; }
		public string answer { get; set; }
	}

/*
{
    "quiz": {
        "sport": {
            "q1": {
                "question": "Which one is correct team name in NBA?",
                "options": [
                    "New York Bulls",
                    "Los Angeles Kings",
                    "Golden State Warriros",
                    "Huston Rocket"
                ],
                "answer": "Huston Rocket"
            }
        },
        "maths": {
	"q1": {
		"question": "5 + 7 = ?",
                "options": [

					"10",
                    "11",
                    "12",
                    "13"
                ],
                "answer": "12"

			},
            "q2": {
		"question": "12 - 8 = ?",
                "options": [

					"1",
                    "2",
                    "3",
                    "4"
                ],
                "answer": "4"

			}
		}
    }
}
*/
}



