package edu.metrostate.Model.Categories;

import edu.metrostate.Model.MacroNutrient;
import edu.metrostate.Model.NutritionalChart;
import edu.metrostate.Model.Storage;

import java.util.Date;

public class Meat extends Ingredient {

    public Meat(int id, String name, Date expiryDate, NutritionalChart nutrition, MacroNutrient primaryMacroNutrient, Storage storage) {
        super(id, name, expiryDate, nutrition, primaryMacroNutrient, storage);
    }
}