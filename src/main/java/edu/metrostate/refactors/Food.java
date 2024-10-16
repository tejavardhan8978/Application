package edu.metrostate.refactors;


public abstract class Food {
    private final int id;
    private final String name;

    public Food (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId () {
        return id;
    }

    public String getName () {
        return name;
    }
    //    public Food (FoodBuilder<?, ?> builder) {
//        this.id = builder.id;
//        this.name = builder.name;
//    }
//
//    @Override
//    public boolean equals (Object object) {
//        if (object == this) return true;
//        if (object == null) return false;
//        if (object instanceof Food food)
//            return id == food.getId() && name.equals(food.getName());
//        return false;
//    }
//
//    public abstract static class FoodBuilder<C extends Food, B extends FoodBuilder<C, B>> {
//        private int id;
//        private String name;
//        private int quantityInStock;
//        private int reorderThreshold;
//        private LocalDate purchaseDate;
//
//        protected abstract B self();
//
//        public B id (int id) {
//            this.id = id;
//            return self();
//        }
//
//        public B name (String name) {
//            this.name = name;
//            return self();
//        }
//
//        public B quantityInStock (int quantityInStock) {
//            this.quantityInStock = quantityInStock;
//            return self();
//        }
//
//        public B reorderThreshold (int reorderThreshold) {
//            this.reorderThreshold = reorderThreshold;
//            return self();
//        }
//
//        public abstract C build ();
//    }
}