package edu.metrostate.Model;

import java.sql.Date;

public class DailyMealPlanner {
	public DailyMealPlanner(String day, Date date, Recipe breakfast, Recipe lunch, Recipe dinner, Recipe snack,
			Recipe drink) {
		this.day = day;
		this.date = date;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		this.snack = snack;
		this.drink = drink;
	}
	private String day;
	private Date date;
	private Recipe breakfast;
	private Recipe lunch;
	private Recipe dinner;
	private Recipe snack;
	private Recipe drink;
}
