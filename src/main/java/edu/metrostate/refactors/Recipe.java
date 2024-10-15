package edu.metrostate.refactors;


import edu.metrostate.Model.Cuisine;

public final class Recipe {

    private final int id;
    private String title;
    private Cuisine cuisine;
    private String description;
    private int cookingMinutes;
    private int numberOfServings;
    private final Ingredients ingredients;


    public Recipe (RecipeBuilder builder) {
        id = builder.id;
        title = builder.title;
        cuisine = builder.cuisine;
        description = builder.description;
        cookingMinutes = builder.cookingMinutes;
        numberOfServings = builder.numberOfServings;
        ingredients = new Ingredients();
    }

    public int getId () {
        return id;
    }

    public String getTitle () {
        return title;
    }

    public Cuisine getCuisine () {
        return cuisine;
    }

    public String getDescription () {
        return description;
    }

    public int getCookingMinutes () {
        return cookingMinutes;
    }

    public int getNumberOfServings () {
        return numberOfServings;
    }

    public Ingredients getIngredients () {
        return ingredients;
    }

    public int getMealCalories () {
        return ingredients.getTotalCalories();
    }

    @Override
    public boolean equals (Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (object instanceof Recipe recipe)
            return id == recipe.getId() && title.equalsIgnoreCase(recipe.getTitle());
        return false;
    }

    public static RecipeBuilder builder () {
        return new RecipeBuilder();
    }

    public static class RecipeBuilder {
        private int id;
        private String title;
        private Cuisine cuisine;
        private String description;
        private int cookingMinutes;
        private int numberOfServings;

        public RecipeBuilder id (int id) {
            this.id = id;
            return this;
        }

        public RecipeBuilder title (String title) {
            this.title = title;
            return this;
        }

        public RecipeBuilder cuisine (Cuisine cuisine) {
            this.cuisine = cuisine;
            return this;
        }

        public RecipeBuilder description (String description) {
            this.description = description;
            return this;
        }

        public RecipeBuilder cookingMinutes (int cookingMinutes) {
            this.cookingMinutes = cookingMinutes;
            return this;
        }

        public RecipeBuilder numberOfServings (int numberOfServings) {
            this.numberOfServings = numberOfServings;
            return this;
        }

        public Recipe build() {return new Recipe(this);}
    }
}