package edu.metrostate.refactors;

public record NutritionalChart (
    int servingSize,
    int totalCarbohydrates,
    int totalFat,
    int totalProtein,
    int totalSodium,
    int totalSugars,
    int dietaryFiber,
    int cholesterol
) {

    public int getCalories() {
        return servingSize * (
            totalCarbohydrates
            + totalFat
            + totalProtein
            + totalSodium
            + totalSugars
            + dietaryFiber
            + cholesterol
            + totalSugars
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int servingSize;
        private int totalCarbohydrates;
        private int totalFat;
        private int totalProtein;
        private int totalSodium;
        private int totalSugars;
        private int dietaryFiber;
        private int cholesterol;

        public Builder () {
        }

        public Builder servingSize (int servingSize) {
            this.servingSize = servingSize;
            return this;
        }

        public Builder totalCarbohydrates (int totalCarbohydrates) {
            this.totalCarbohydrates = totalCarbohydrates;
            return this;
        }

        public Builder totalFat (int totalFat) {
            this.totalFat = totalFat;
            return this;
        }

        public Builder totalProtein (int totalProtein) {
            this.totalProtein = totalProtein;
            return this;
        }

        public Builder totalSodium (int totalSodium) {
            this.totalSodium = totalSodium;
            return this;
        }

        public Builder totalSugars (int totalSugars) {
            this.totalSugars = totalSugars;
            return this;
        }

        public Builder dietaryFiber (int dietaryFiber) {
            this.dietaryFiber = dietaryFiber;
            return this;
        }

        public Builder cholesterol (int cholesterol) {
            this.cholesterol = cholesterol;
            return this;
        }

        public NutritionalChart build() {
            return new NutritionalChart(
                servingSize,
                totalCarbohydrates,
                totalFat,
                totalProtein,
                totalSodium,
                totalSugars,
                dietaryFiber,
                cholesterol
            );
        }
    }
}