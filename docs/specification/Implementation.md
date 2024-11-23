# Phase1

## Add to the Inventory
Our application is satisfying the functional requirement by utilizing 2 views, 2 controllers, and 3 models. 
Press inventory to see one of the views we created which is for displaying information about the items in your inventory called the Inventory-Home. 
This is filled with default information for testing. From here you can select the add to recipe view which is our second view called AddToInventory.
At this moment all information has be entered in correctly. We plan to include exception handling in a different milestone for a non-functional 
requirement. After entering the information and pressing save it is passed to the AddIngredientToInventoryController
where it creates a item and nutritional chart. It then passes this item back to the InventoryController where it is then 
added to a singleton list.

### Process:
1) Navigate to the Inventory page.
2) Select the option to add a new item, leading to a new view.
3) Enter the required information and press Save (You can also hit cancel to abort.)
4) Your item should be in your inventory now.

## Add to the Recipe
We were able to implement a similar process for adding an recipe. Again using 2 views, 2 controllers, and 3 models.
From the launch hit Recipes to see one of the views which displays information about the recipes called Recipe-Home.
From here you can select add new recipe which takes you to our second view AddToRecipe. Once again please enter all 
information correctly. Then hit save once more information entered is passed to the AddToRecipeController where a recipe
item is created and then passed to the RecipeController to be added to the list

### Process:
1) Navigate to the Recipe page.
2) Select the option to add a new recipe, leading to a new view.
3) Enter the required information and press Save (You can also hit cancel to abort.)
4) Your recipe should be in your recipe table now.

# Phase 2

## Delete From Recipes

## Edit/Delete from Ingredients
Within the Inventory menu, you will notice a Delete button. To delete an item, select it by clicking on it once in the table, 
then click the Delete button to remove it. To make this functional, we collect the selected ingredient's ID. We then create 
a SQL statement “DELETE FROM IngredientTable WHERE ingredientID = ?“. After establishing the database connection, we update 
the ? placeholder with the selected ingredient’s ID, execute the statement, and finally, ensure that the table view is updated 
to reflect the change. Since we configured the ingredient and nutritional items to delete on cascade both will be deleted.

Editing an ingredient's inventory follows a very similar process. First, select the item you want to edit by clicking on it 
once in the table. Then, click the Edit button to open a new view. Enter the new quantity and click Save. Again, we collect the 
ingredient using the selected ID, then construct an SQL statement “UPDATE IngredientTable SET quantity = ? WHERE ingredientID = ?”. 
We get the connection, and update the ? placeholders with the new quantity and ingredient ID, execute the statement and refresh 
the table to show the updated inventory.

### Process Remove
1) Navigate to the inventory table
2) Select an item by selecting an ingredient by hitting it once
3) then press the delete button

### Process Edit
1) Navigate to the inventory table
2) Select an item by selecting an ingredient by hitting it once
3) Then press the edit button
4) Enter your new desired quantity and then hit save

# Phase 3

## Search the Database
Our application is satisfying the functional requirement by first creating a sql query, then get a connection to the database
then gathering the information that is similar to the search query and adding it to a the recipeTable results. In order 
to implement this we had to ensure that the fields that were allowed to null were handled properly when trying to gather 
them from the database. 

### Process
1) Launch the application and select either recipes or inventory
2) Find the search bar and search for something by the name
3) As you keep entering characters your search will become more accurate

## Check Inventory for Recipe Ingredients
