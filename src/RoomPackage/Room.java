package RoomPackage;

public class Room {
    private String id;
    private long pricePerHour;
    private String description;

    public Room(String id, long pricePerHour, String description) {
        this.id = id;
        this.pricePerHour = pricePerHour;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(long pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "=====PHÒNG " + id + "=====" + "\n" +
                "Giá chơi theo giờ: " + pricePerHour + "\n" +
                "Mô tả phòng: " + description + "\n" +
                "===================" + "\n";
    }

    public String toFile() {
        return id + "," + pricePerHour + "," + description;
    }
}
