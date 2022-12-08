package RoomPackage;

import java.util.Scanner;

public class RoomManagementMenu {
    public Scanner sc = new Scanner(System.in);
    RoomManagement roomManagement = RoomManagement.getRoomManagement();
    public RoomManagementMenu() {
    }
    public void menu() {
        displayMenu();
        int choice = sc.nextInt();
        while (choice != 0) {
            switch (choice) {
                case 1 -> addR();
                case 2 -> removeR();
                case 3 -> searchR();
                case 4 -> editRID();
                case 5 -> editRPricePerHour();
                case 6 -> editRDescription();
                case 7 -> System.out.println(roomManagement.sortAscPrice());
                case 8 -> System.out.println(roomManagement.sortDesPrice());
                case 9 -> System.out.println(roomManagement.sortAscAlphabet());
                case 10 -> System.out.println(roomManagement.sortDesAlphabet());
                case 11 -> displayRooms();
                default -> System.out.println("Số nhập vào không hợp lệ. Vui lòng chọn lại");
            }
            displayMenu();
            choice = sc.nextInt();
        }
    }

    private void editRDescription() {
        System.out.println("Bạn chọn sửa mô tả phòng. Nhập ID tài khoản cần sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (roomManagement.searchRoom(id) != null) {
            System.out.println("Nhập mô tả mới thay thế:");
            String newDes = sc.nextLine();
            roomManagement.editDescription(id,newDes);
        } else {
            System.out.println("Phòng không tồn tại");
        }
    }

    private void editRPricePerHour() {
        System.out.println("Bạn chọn sửa giá tiền theo giờ của phòng. Nhập ID phòng cần sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (roomManagement.searchRoom(id) != null) {
            System.out.println("Nhập giá tiền mới thay thế:");
            long newPPH = sc.nextLong();
            roomManagement.editPricePerHour(id,newPPH);
        } else {
            System.out.println("Phòng không tồn tại");
        }
    }

    private void editRID() {
        System.out.println("Bạn chọn sửa ID phòng. Nhập ID phòng cần sửa:");
        sc.nextLine();
        String id = sc.next();
        if (roomManagement.searchRoom(id) != null) {
            System.out.println("Nhập ID mới thay thế:");
            String newID = sc.nextLine();
            roomManagement.editId(id,newID);
        } else {
            System.out.println("Phòng không tồn tại");
        }
    }

    private void displayRooms() {
        System.out.println(roomManagement);
    }

    private void searchR() {
        System.out.println("Bạn chọn tìm phòng theo ID phòng. Vui lòng nhập ID phòng bạn cần tím");
        sc.nextLine();
        String id = sc.nextLine();
        Room searchRoom = roomManagement.searchRoom(id);
        if (searchRoom != null) {
            System.out.println("Phòng bạn cần tìm là:");
            System.out.println(searchRoom);
        } else
            System.out.println("Phòng không tồn tại");
    }

    private void removeR() {
        System.out.println("Bạn chọn xoá phòng. Vui lòng nhập ID phòng bạn cần xoá");
        sc.nextLine();
        String id = sc.nextLine();
        Room searchRoom = roomManagement.searchRoom(id);
        if (searchRoom != null) {
            if (roomManagement.removeRoom(id)) {
                System.out.println("Phòng đã bị xoá");
            }
        } else
            System.out.println("Phòng không tồn tại");
    }

    private void addR() {
        System.out.println("Bạn chọn thêm phòng mới. Vui lòng nhập ID phòng:");
        sc.nextLine();
        String id = sc.nextLine();
        System.out.println("Nhập tiền theo giờ cho phòng:");
        long pricePerHour = sc.nextLong();
        sc.nextLine();
        System.out.println("Nhập mô tả phòng:");
        String description = sc.nextLine();
        Room room = new Room(id,pricePerHour,description);
        roomManagement.addRoom(room);
        System.out.println("Phòng mới đã được thêm!");
    }

    private static void displayMenu() {
        System.out.println("***** MENU QUẢN LÝ PHÒNG CHƠI *****");
        System.out.println("1. Thêm phòng");
        System.out.println("2. Xoá phòng");
        System.out.println("3. Tìm phòng");
        System.out.println("4. Sửa ID phòng");
        System.out.println("5. Sửa giá tiền theo giờ của phòng");
        System.out.println("6. Sửa mô tả phòng");
        System.out.println("7. Sắp xếp danh sách phòng theo giá, thứ tự tăng dần");
        System.out.println("8. Sắp xếp danh sách phòng theo giá, thứ tự giảm dần");
        System.out.println("9. Sắp xếp danh sách phòng theo giá, thứ tự tăng dần");
        System.out.println("10. Sắp xếp danh sách phòng theo giá, thứ tự giảm dần");
        System.out.println("11. Hiển thị danh sách phòng");
        System.out.println("0. Thoát");
    }
}
