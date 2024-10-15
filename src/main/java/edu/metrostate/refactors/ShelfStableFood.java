package edu.metrostate.refactors;



public abstract class ShelfStableFood extends Food implements NutritionLabeled {
    private NutritionalChart nutritionalChart;

    public ShelfStableFood (int id, String name, NutritionalChart nutritionalChart) {
        super(id, name);
        this.nutritionalChart = nutritionalChart;
    }

    @Override
    public NutritionalChart getNutritionalChart () {
        return nutritionalChart;
    }
}