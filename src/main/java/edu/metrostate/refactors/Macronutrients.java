package edu.metrostate.refactors;

import java.util.ArrayList;
import java.util.List;


public class Macronutrients {

    private List<Macronutrient> macronutrients;

    public Macronutrients() {
        macronutrients = new ArrayList<Macronutrient>();
    }

    public List<Macronutrient> getMacronutrients() {
        return macronutrients;
    }

    public void add(Macronutrient macronutrient) {
        if (!macronutrients.contains(macronutrient))
            macronutrients.add(macronutrient);
    }

    public void remove(Macronutrient macronutrient) {
        if(macronutrients.contains(macronutrient))
            remove(macronutrient);
    }
}