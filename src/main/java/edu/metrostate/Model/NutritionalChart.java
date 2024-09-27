package edu.metrostate.Model;

public class NutritionalChart {

    private int servingSize;
    private int calories;
    private int totalCarbohydrates;
    private int totalFat;
    private int totalProtein;
    private int totalSodium;
    private int totalSugars;
    private int dietaryFibre;
    private int cholesterol;

    public NutritionalChart(int servingSize, int calories, int totalCarbohydrates, int totalFat,
                            int cholesterol, int dietaryFibre, int totalProtein, int totalSodium, int totalSugars) {
        this.servingSize = servingSize;
        this.calories = calories;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalFat = totalFat;
        this.totalProtein = totalProtein;
        this.totalSodium = totalSodium;
        this.totalSugars = totalSugars;
        this.dietaryFibre = dietaryFibre;
        this.cholesterol = cholesterol;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(int totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(int totalProtein) {
        this.totalProtein = totalProtein;
    }

    public int getTotalSodium() {
        return totalSodium;
    }

    public void setTotalSodium(int totalSodium) {
        this.totalSodium = totalSodium;
    }

    public int getTotalSugars() {
        return totalSugars;
    }

    public void setTotalSugars(int totalSugars) {
        this.totalSugars = totalSugars;
    }

    public int getDietaryFibre() {
        return dietaryFibre;
    }

    public void setDietaryFibre(int dietaryFibre) {
        this.dietaryFibre = dietaryFibre;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }
}