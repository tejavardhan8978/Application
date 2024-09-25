package Categories;

public class Dairy {
    private int id;
    void setID(int newID){
        id = newID;
    }
    int getID(){
        return id;
    }
    String type;
    void SetType(String Newtype) {
        type = Newtype;
    }
    String getType(){
        return type;
    }
    String name;
    void SetName(String NewName){
        name = NewName;
    }
    String GetName(){
        return name;
    }
    int quantity;
    void SetQuantity(int newQuantity){
        quantity = newQuantity
    }
    int GetQuantity(){
        return quantity;
    }
    int expiry_date;
    void SetExpiry(int NewExpiry){
        expiry_date = NewExpiry;
    }
    int getExpiry_date(){
        return expiry_date;
    }
    enum storage{
        PANTRY,
        FRIDGE,
        FREEZER,
    }
    void SetStorage(){

    }
}