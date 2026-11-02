package schoolinventory;

public class Equipment {

    private String equipmentID;
    private String equipmentName;
    private String category;
    private int quantity;
    private String availability;

    public Equipment(String equipmentID, String equipmentName,
                     String category, int quantity,
                     String availability) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.category = category;
        this.quantity = quantity;
        this.availability = availability;
    }

}