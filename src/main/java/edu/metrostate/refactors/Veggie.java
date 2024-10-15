package edu.metrostate.refactors;

import java.time.LocalDate;
import java.util.Date;

public final class Veggie extends PerishableFood implements MacroNutrientable {
    public static final long SHELF_LIFE = 14;
    private Macronutrients macronutrients;

    public Veggie (int id, String name, long shelfLifeDays, LocalDate expirationDate) {
        super(id, name, shelfLifeDays, expirationDate);
        macronutrients = new Macronutrients();
    }

    @Override
    public Macronutrients getMacronutrients () {
        return macronutrients;
    }

//    public Veggie (VegetableBuilder builder) {
//        super(id,
//            builder.name,
//            SHELF_LIFE
//         );
//        macroNutrient = builder.macroNutrient;
//    }
//
//    @Override
//    public long getShelfLifeDays () {
//        return SHELF_LIFE;
//    }
//
//    @Override
//    public LocalDate getExpirationDate () {
//        return getPurchaseDate().plusDays(SHELF_LIFE);
//    }
//
//    @Override
//    public boolean hasExpired () {
//        return false;
//    }
//
//    @Override
//    public Macronutrients getMacronutrients () {
//        return null;
//    }
//
//    public static class VegetableBuilder {
//        private int id;
//        private String name;
//        private NutritionalChart nutritionalChart;
//        private Macronutrient macroNutrient;
//
//        public VegetableBuilder () {}
//
//        public VegetableBuilder idd (int id) {
//            this.id = id;
//            return this;
//        }
//
//        public VegetableBuilder name (String name) {
//            this.name = name;
//            return this;
//        }
//
//        public VegetableBuilder quantityInStock (int quantityInStock) {
//            this.quantityInStock = quantityInStock;
//            return this;
//        }
//
//        public VegetableBuilder reorderThreshold (int reorderThreshold) {
//            this.reorderThreshold = reorderThreshold;
//            return this;
//        }
//
//        public VegetableBuilder purchaseDate (Date purchaseDate) {
//            this.purchaseDate = purchaseDate;
//            return this;
//        }
//
//        public VegetableBuilder nutritionalChart (NutritionalChart nutritionalChart) {
//            this.nutritionalChart = nutritionalChart;
//            return this;
//        }
//
//        public VegetableBuilder macroNutrient (Macronutrient macroNutrient) {
//            this.macroNutrient = macroNutrient;
//            return this;
//        }
//
//        public Veggie build () {
//            return new Veggie(this);
//        }
//    }
}