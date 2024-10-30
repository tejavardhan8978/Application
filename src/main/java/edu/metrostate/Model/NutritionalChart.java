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
    private String[] nutrientList1;
    private String[] nutrientList2;
    private int[] value1;
    private int[] value2;


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
        nutrientList1 = new String[3];
        nutrientList2 = new String[6];
        value1 = new int[4];
        value2 = new int[5];
    }

    public NutritionalChart() {

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

    public int getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(int dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String[] getNutrientList1() {
        this.nutrientList1[0] = "SERVING SIZE";
        this.nutrientList1[1] = "CALORIES";
        this.nutrientList1[2] = "CARBOHYDRATES";
        return this.nutrientList1;
    }

    public String[] getNutrientList2() {
        this.nutrientList2[0] = "FAT";
        this.nutrientList2[1] = "PROTEIN";
        this.nutrientList2[2] = "SUGAR";
        this.nutrientList2[3] = "SODIUM";
        this.nutrientList2[4] = "DIETARY FIBER";
        this.nutrientList2[5] = "CHOLESTEROL";
        return nutrientList2;
    }

    public int[] getValue1() {
        this.value1[0] = this.getServingSize();
        this.value1[1] = this.getCalories();
        this.value1[2] = this.getTotalCarbohydrates();
        this.value1[3] = this.getTotalFat();
        return value1;
    }

    public int[] getValue2() {

        this.value2[0] = this.getTotalProtein();
        this.value2[1] = this.getTotalSugars();
        this.value2[2] = this.getTotalSodium();
        this.value2[3] = this.getDietaryFiber();
        this.value2[4] = this.getCholesterol();
        return value2;
    }
}