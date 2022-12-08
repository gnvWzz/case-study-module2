package RoomPackage;

import java.util.Comparator;

public class ComparatorRoomPrice implements Comparator<Room> {
    @Override
    public int compare(Room r1, Room r2) {
        return Long.compare(r1.getPricePerHour(),r2.getPricePerHour());
    }
}
