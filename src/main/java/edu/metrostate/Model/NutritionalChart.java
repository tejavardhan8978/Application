package edu.metrostate.Model;

public class NutritionalChart {

    private int id;
    private int servingSize;
    private int calories;
    private int totalCarbohydrates;
    private int totalFat;
    private int totalProtein;
    private int totalSodium;
    private int totalSugars;
    private int dietaryFiber;
    private int cholesterol;

    public NutritionalChart(int id, int servingSize, int calories, int totalCarbohydrates, int totalFat,
                            int cholesterol, int dietaryFiber, int totalProtein, int totalSodium, int totalSugars) {
        this.id = id;
        this.servingSize = servingSize;
        this.calories = calories;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalFat = totalFat;
        this.totalProtein = totalProtein;
        this.totalSodium = totalSodium;
        this.totalSugars = totalSugars;
        this.dietaryFiber = dietaryFiber;
        this.cholesterol = cholesterol;
    }

    public NutritionalChart() {

    }

    public int getServingSize() {
        return servingSize;
    }

    public int getCalories() {
        return calories;
    }

    public int getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public int getTotalSodium() {
        return totalSodium;
    }

    public int getTotalSugars() {
        return totalSugars;
    }

    public int getDietaryFiber() {
        return dietaryFiber;
    }

    public int getCholesterol() {
        return cholesterol;
    }

}