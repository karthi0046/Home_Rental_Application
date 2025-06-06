package model;

public class Property {


    private int propertyId;
    private int userId;  // refers to User.userId
    private String title;
    private String description;
    private String address;
    private double rent;
    private int rooms;
    private boolean isAvailable;

     public Property() {}

    public Property(int propertyId, int userId, String title, String description, String address,
                    double rent, int rooms, boolean isAvailable) {
        this.propertyId = propertyId;
        this.userId = userId;
        this.title = title;
        this.address = address;
        this.description = description;
        this.rent = rent;
        this.rooms = rooms;
        this.isAvailable = isAvailable;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", rent=" + rent +
                ", rooms=" + rooms +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
