package RoomPackage;

import RoomPackage.Room;

import java.util.Comparator;

public class ComparatorRoomAlphabet implements Comparator<Room> {
    @Override
    public int compare(Room r1, Room r2) {
        return r1.getId().compareTo(r2.getId());
    }
}
