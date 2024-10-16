package edu.metrostate.refactors;

public class PackagedFoods extends Food implements NutritionLabeled {
    private NutritionalChart nutritionalChart;

    public PackagedFoods (int id, String name, NutritionalChart nutritionalChart) {
        super(id, name);
        this.nutritionalChart = nutritionalChart;
    }


    @Override
    public NutritionalChart getNutritionalChart () {
        return nutritionalChart;
    }
}