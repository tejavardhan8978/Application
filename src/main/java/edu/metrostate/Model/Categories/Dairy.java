package Categories;

public class Dairy {

    private int id;
    private String type;
    private String name;
    private int quantity;
    private int expiry_date;

    enum storage {
        PANTRY,
        FRIDGE,
        FREEZER,
    }

    public void setID(int newID) {
        id = newID;
    }

    public int getID() {
        return id;
    }


    public void SetType(String Newtype) {
        type = Newtype;
    }

    public String getType() {
        return type;
    }


    public void SetName(String NewName) {
        name = NewName;
    }

    public String GetName() {
        return name;
    }


    public void SetQuantity(int newQuantity) {
        quantity = newQuantity
    }

    public int GetQuantity() {
        return quantity;
    }


    public void SetExpiry(int NewExpiry) {
        expiry_date = NewExpiry;
    }

    public int getExpiry_date() {
        return expiry_date;
    }


    public void SetStorage() {

    }
}