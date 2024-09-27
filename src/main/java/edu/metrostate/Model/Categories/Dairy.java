package edu.metrostate.Model.Categories;

public class Dairy extends Ingredient {
    private int id;
    void setID(int newID){
        id = newID;
    }
    int getID(){
        return id;
    }
    String type;
    String name;
    int quantity;
    int expiry_date;
    boolean requires_refrigeration;
    boolean isFrozen;

    void NewDairy(int newID, String NewType, String NewName, int NewQuantity, String NewCategory){

    }

}