package RoomPackage;

import java.io.*;
import java.util.*;

public class RoomManagement {
    List<Room> rooms;
    private static final RoomManagement roomManagement = new RoomManagement();

    private RoomManagement() {
        rooms = new ArrayList<>();
        readFile();
    }

    public static RoomManagement getRoomManagement() {
        return roomManagement;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        saveToFile();
    }

    public boolean removeRoom(String id) {
        for (Room room:
             rooms) {
            if (room.getId().equals(id)) {
                rooms.remove(room);
                return true;
            }
        }
        return false;
    }

    public Room searchRoom(String id) {
        for (Room room:
                rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    public void editId(String id, String newId) {
        for (Room room:
                rooms) {
            if (room.getId().equals(id)) {
                room.setId(newId);
            }
        }
    }

    public void editPricePerHour(String id, long newPrice) {
        for (Room room:
                rooms) {
            if (room.getId().equals(id)) {
                room.setPricePerHour(newPrice);
            }
        }
    }

    public void editDescription(String id, String newDescription) {
        for (Room room:
                rooms) {
            if (room.getId().equals(id)) {
                room.setDescription(newDescription);
            }
        }
    }

    public List<Room> sortAscPrice() {
        ComparatorRoomPrice comparatorRoomPrice = new ComparatorRoomPrice();
        rooms.sort(comparatorRoomPrice);
        return rooms;
    }

    public List<Room> sortDesPrice() {
        ComparatorRoomPrice comparatorRoomPrice = new ComparatorRoomPrice();
        rooms.sort(comparatorRoomPrice.reversed());
        return rooms;
    }

    public List<Room> sortAscAlphabet() {
        ComparatorRoomAlphabet comparatorRoomAlphabet = new ComparatorRoomAlphabet();
        rooms.sort(comparatorRoomAlphabet);
        return rooms;
    }

    public List<Room> sortDesAlphabet() {
        ComparatorRoomAlphabet comparatorRoomAlphabet = new ComparatorRoomAlphabet();
        rooms.sort(comparatorRoomAlphabet.reversed());
        return rooms;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Room room :
                rooms) {
            result.append(room);
        }
        return result.toString();
    }

    public void saveToFile() {
        try {
            File file = new File("Rooms.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Room room: rooms) {
                bufferedWriter.write(room.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        rooms.clear();
        try {
            File file = new File("Rooms.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            Room room;
            while ((line = bufferedReader.readLine()) != null) {
                room = getRoom(line);
                rooms.add(room);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static Room getRoom(String line) {
        Room room;
        String[] ss;
        ss = line.split(",");
        StringBuilder ssDes = new StringBuilder();
        for (int i = 2; i < ss.length; i++) {
            ssDes.append(ss[i]);
        }
        room = new Room(ss[0],Long.parseLong(ss[1]),ssDes.toString());
        return room;
    }
}
