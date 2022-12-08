package InvoicePackage;

import CustomerPackage.CustomerManagement;
import RoomPackage.RoomManagement;

import java.util.Date;
import java.util.Scanner;

public class InvoiceManagementMenu {
    Scanner sc = new Scanner(System.in);
    InvoiceManagement invoiceManagement = InvoiceManagement.getInvoiceManagement();
    RoomManagement roomManagement = RoomManagement.getRoomManagement();
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    public void menu() {
        displayMenu();
        int choice = sc.nextInt();
        while (choice != 0) {
            switch (choice) {
                case 1 -> addI();
                case 2 -> removeI();
                case 3 -> searchI();
                case 4 -> System.out.println(invoiceManagement);
                default -> System.out.println("Số nhập vào không hợp lệ. Vui lòng chọn lại");
            }
            displayMenu();
            choice = sc.nextInt();
        }
    }

    private void searchI() {
        System.out.println("Bạn chọn tìm hoá đơn. Vui lòng nhập ID hoá đơn cần tìm:");
        sc.nextLine();
        String id = sc.nextLine();
        Invoice searchInvoice = InvoiceManagement.getInvoiceManagement().searchInvoice(id);
        if (searchInvoice != null) {
            System.out.println(searchInvoice);
        } else {
            System.out.println("Hoá đơn không tồn tại");
        }
    }

    private void removeI() {
        System.out.println("Bạn chọn xoá hoá đơn. Vui lòng nhập ID hoá đơn cần xoá:");
        sc.nextLine();
        String id = sc.nextLine();
        Invoice searchInvoice = InvoiceManagement.getInvoiceManagement().searchInvoice(id);
        if (searchInvoice != null) {
            if (InvoiceManagement.getInvoiceManagement().removeInvoice(id)) {
                System.out.println("Hoá đơn đã được xoá");
            }
        } else {
            System.out.println("Hoá đơn không tồn tại");
        }
    }

    private static void displayMenu() {
        System.out.println("***** MENU QUẢN LÝ HOÁ ĐƠN *****");
        System.out.println("1. Thêm hoá đơn");
        System.out.println("2. Xoá hoá đơn");
        System.out.println("3. Tra cứu hoá đơn");
        System.out.println("4. Xem danh sách hoá đơn");
        System.out.println("0. Thoát");
    }

    private void addI() {
        System.out.println("Bạn chọn tạo hoá đơn mới. Nhập ID hoá đơn:");
        sc.nextLine();
        String invoiceId = sc.nextLine();
        boolean checkInvoiceId = false;
        while (!checkInvoiceId) {
            if (invoiceManagement.isInvoiceIdExist(invoiceId)) {
                System.out.println("ID hoá đơn đã trùng. Vui lòng nhập lại");
                invoiceId = sc.nextLine();
            } else {
                checkInvoiceId = true;
            }
        }
        System.out.println("Nhập ID khách hàng: ");
        String customerId = sc.nextLine();
        boolean checkCustomerId = false;
        while (!checkCustomerId) {
            if (customerManagement.searchCustomerById(customerId) == null) {
                System.out.println("ID khách hàng không tồn tại. Vui lòng nhập lại");
                customerId = sc.nextLine();
            } else {
                checkCustomerId = true;
            }
        }
        Date createdInvoiceDate = new Date();
        Invoice invoice = new Invoice(createdInvoiceDate,invoiceId,customerId);
        int choice;
        do {
            System.out.println("Nhập ID phòng khách hàng đã sử dụng:");
            System.out.println(roomManagement);
            String roomId = sc.next();
            sc.nextLine();
            System.out.println("Nhập số giờ khách hàng đã chơi:");
            long playedTime = sc.nextLong();
            sc.nextLine();
            invoice.addRoom(roomId, playedTime);
            System.out.print("Khách hàng còn sử dụng phòng nào không? Nhấn số bất kì để chọn tiếp hoặc nhấn số 0 để thoát.");
            choice = sc.nextInt();
            sc.nextLine();
        } while (choice != 0);
        invoiceManagement.addInvoice(invoice);
    }
}
