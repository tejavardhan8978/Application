CREATE TABLE NutritionalChart(
nutritionID INTEGER PRIMARY KEY AUTOINCREMENT,
ingredientID INTEGER,
servingSize INTEGER NULL,
calories INTEGER NULL,
totalCarbohydrates INTEGER NULL,
totalFat INTEGER NULL,
totalProtein INTEGER NULL,
totalSodium INTEGER NULL,
totalSugars INTEGER NULL,
dietaryFiber INTEGER NULL,
cholesterol INTEGER NULL,
FOREIGN KEY (ingredientID) REFERENCES IngredientTable(ingredientID) ON DELETE CASCADE
);