package Page;

import CustomerPackage.CustomerManagementMenu;
import InvoicePackage.InvoiceManagementMenu;
import RoomPackage.RoomManagementMenu;

import java.util.Scanner;

public class AdminPage {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        RoomManagementMenu roomManagementMenu = new RoomManagementMenu();
        CustomerManagementMenu customerManagementMenu = new CustomerManagementMenu();
        InvoiceManagementMenu invoiceManagementMenu = new InvoiceManagementMenu();
        int choice;
        do {
            System.out.println("~~~~~ QUẢN LÝ PHÒNG INTERNET ~~~~~");
            System.out.println("1. Quản lý tài khoản");
            System.out.println("2. Quản lý phòng chơi");
            System.out.println("3. Quản lý hoá đơn");
            System.out.println("0. Thoát");
            choice = input.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
                case 1 -> customerManagementMenu.menu();
                case 2 -> roomManagementMenu.menu();
                case 3 -> invoiceManagementMenu.menu();
            }
            System.out.println("Bạn có muốn chọn tiếp không? Nhấn số bất kì để tiếp tục chương trình hoặc nhấn số 0 để thoát chương trình");
            input.nextLine();
            choice = input.nextInt();
        } while (choice != 0);
    }
}