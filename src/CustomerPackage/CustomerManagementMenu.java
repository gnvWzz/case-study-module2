package CustomerPackage;

import java.util.List;
import java.util.Scanner;

public class CustomerManagementMenu {
    public Scanner sc = new Scanner(System.in);
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    ValidatePhoneNumber validatePhoneNumber = new ValidatePhoneNumber();
    ValidateIdentityCardNumber validateIdentityCardNumber = new ValidateIdentityCardNumber();
    public CustomerManagementMenu() {
    }

    public void menu() {
        displayMenu();
        int choice = sc.nextInt();
        while (choice != 0) {
            switch (choice) {
                case 1 -> addC();
                case 2 -> removeC();
                case 3 -> searchByCId();
                case 4 -> searchByCName();
                case 5 -> editCPassword();
                case 6 -> editCName();
                case 7 -> editCPhoneNumber();
                case 8 -> editCIdentityCardNumber();
                case 9 -> addCPlayMoney();
                case 10 -> displayCustomers();
                case 11 -> checkAccountBalance();
                default -> System.out.println("Số nhập vào không hợp lệ. Vui lòng chọn lại");
            }
            displayMenu();
            choice = sc.nextInt();
        }
    }

    private void checkAccountBalance() {
        System.out.println("Bạn chọn kiểm tra số dư tài khoản của khách hàng. Nhập ID tài khoản để kiểm tra:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Số dư trong tài khoản hiện tại là: ");
            System.out.println(customerManagement.searchCustomerById(id).getPlayMoney());
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void addCPlayMoney() {
        System.out.println("Bạn chọn thêm tiền chơi cho khách hàng. Nhập ID tài khoản để thêm:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Nhập số tiền khách hàng nạp thêm:");
            long addingPlayMoney = sc.nextLong();
            sc.nextLine();
            customerManagement.addCustomerPlayMoney(id,addingPlayMoney);
            System.out.println("Đã nạp thêm tiền cho khách hàng");
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void editCIdentityCardNumber() {
        System.out.println("Bạn chọn sửa số cmnd/cccd khách hàng. Nhập ID tài khoản để sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Nhập số cmnd/cccd mới thay thế:");
            String newIDCN = sc.nextLine();
            customerManagement.editCustomerIdentityCardNumber(id,newIDCN);
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void editCPhoneNumber() {
        System.out.println("Bạn chọn sửa sđt khách hàng. Nhập ID tài khoản để sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Nhập sđt mới thay thế:");
            String newPN = sc.nextLine();
            customerManagement.editCustomerPhoneNumber(id,newPN);
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void editCName() {
        System.out.println("Bạn chọn sửa tên khách hàng. Nhập ID tài khoản để sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Nhập tên mới thay thế:");
            String newName = sc.nextLine();
            customerManagement.editCustomerName(id,newName);
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void editCPassword() {
        System.out.println("Bạn chọn sửa mật khẩu. Nhập ID tài khoản để sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Nhập mật khẩu mới thay thế:");
            String newPW = sc.nextLine();
            customerManagement.editCustomerPassword(id,newPW);
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void displayCustomers() {
        System.out.println(customerManagement);
    }

    private void searchByCName() {
        System.out.println("Bạn chọn tìm tài khoản theo tên khách hàng. Vui lòng nhập tên khách hàng bạn cần tìm");
        sc.nextLine();
        String customerName = sc.next();
        List<Customer> searchCustomerByName = customerManagement.searchCustomerByName(customerName);
        if (!searchCustomerByName.isEmpty()) {
            System.out.println("Danh sách những tài khoản tìm được theo tên:");
            System.out.println(customerManagement.searchCustomerByName(customerName));
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void searchByCId() {
        System.out.println("Bạn chọn tìm tài khoản theo ID tài khoản. Vui lòng nhập ID tài khoản bạn cần tím");
        sc.nextLine();
        String customerId = sc.nextLine();
        Customer searchCustomerById = customerManagement.searchCustomerById(customerId);
        if (searchCustomerById != null) {
            System.out.println("Tài khoản bạn cần tìm là:");
            System.out.println(searchCustomerById);
        } else
            System.out.println("Tài khoản không tồn tại");
    }

    private void removeC() {
        System.out.println("Bạn chọn xoá tài khoản. Vui lòng nhập ID tài khoản bạn cần xoá");
        sc.nextLine();
        String customerId = sc.nextLine();
        Customer searchCustomerById = customerManagement.searchCustomerById(customerId);
        if (searchCustomerById != null) {
            if (customerManagement.removeCustomer(customerId)) {
                System.out.println("Tài khoản đã bị xoá");
            }
        } else
            System.out.println("Tài khoản không tồn tại");
    }

    private void addC() {
        System.out.println("Bạn chọn tạo tài khoản mới. Vui lòng nhập ID tài khoản:");
        sc.nextLine();
        String id = sc.nextLine();
        boolean checkAccountId = false;
        while (!checkAccountId) {
            if (customerManagement.isAccountIdExist(id)) {
                checkAccountId = true;
                System.out.println("ID đã bị trùng. Vui lòng nhập lại");
                id = sc.nextLine();
            } else {
                break;
            }
        }
        System.out.println("Nhập mật khẩu tài khoản:");
        String password = sc.nextLine();
        System.out.println("Nhập họ và tên khách hàng");
        String name = sc.nextLine();
        System.out.println("Nhập số điện thoại khách hàng:");
        String phoneNumber = sc.nextLine();
        boolean checkPhoneNumber = false;
        while (!checkPhoneNumber) {
            if (customerManagement.isPhoneNumberExist(phoneNumber)) {
                System.out.println("Số điện thoại đã trùng. Vui lòng nhập lại.");
                phoneNumber = sc.nextLine();
            } else {
                if (!validatePhoneNumber.validate(phoneNumber)) {
                    System.out.println("Số điện thoại nhập vào phải đúng định dạng, với số đầu tiên là số 0, số tiếp theo là 1 trong các số từ 5 đến 9, và 8 hoặc 9 số tiếp theo nhập theo ý người dùng. Ví dụ: 012345678 hoặc 0123456789");
                    phoneNumber = sc.nextLine();
                } else {
                    checkPhoneNumber = true;
                }
            }
        }
        System.out.println("Nhập số cmnd/cccd khách hàng:");
        String identityCardNumber = sc.nextLine();
        boolean checkIdentityCardNumber = false;
        while (!checkIdentityCardNumber) {
            if (customerManagement.isIdentityCardNumberExist(identityCardNumber)) {
                System.out.println("Số cmnd/cccd đã trùng. Vui lòng nhập lại.");
                identityCardNumber = sc.nextLine();
            } else {
                if (!validateIdentityCardNumber.validate(identityCardNumber)) {
                    System.out.println("""
                            Số cmnd/cccd nhập vào phải đúng định dạng. Số căn cước mỗi cá nhân (cũng là số định danh) gồm dãy 12 chữ số. Trong đó, 6 số đầu là mã quy định, chỉ cần căn cứ trên 6 số này là có thể biết người đó sinh trong thế kỷ 20 hay 21, sinh năm nào, khai sinh ở đâu và là nam hay nữ. Riêng 6 số cuối là những con số ngẫu nhiên "định danh" từng cá nhân.

                            Cụ thể:

                            * 3 chữ số đầu tiên là mã tỉnh nơi công dân đăng ký khai sinh. Mỗi tỉnh, TP có mã số khác nhau gồm 3 chữ số (Ví dụ: TP Hà Nội là 001, TP.HCM là 079…).

                            * 1 chữ số tiếp theo là mã giới tính của công dân. Với người sinh trong thế kỷ 20, giới tính nam là số 0 và nữ là số 1. Với người sinh ở thế kỷ 21, giới tính nam là 2 và nữ là 3.

                            * 2 chữ số tiếp là mã năm sinh (viết tắt 2 số cuối) của công dân.

                            * 6 chữ số cuối: số ngẫu nhiên.

                            Ví dụ: Số CCCD 079215000001""");
                    identityCardNumber = sc.nextLine();
                } else {
                    checkIdentityCardNumber = true;
                }
            }
        }
        System.out.println("Nhập số tiền khách hàng nạp:");
        long playMoney = sc.nextLong();
        sc.nextLine();
        Customer customer = new Customer(id,password,name,phoneNumber,identityCardNumber,playMoney);
        customerManagement.addCustomer(customer);
        System.out.println("Tài khoản đã được tạo!");
    }

    private static void displayMenu() {
        System.out.println("===== MENU QUẢN LÝ TÀI KHOẢN =====");
        System.out.println("1. Thêm tài khoản");
        System.out.println("2. Xoá tài khoản");
        System.out.println("3. Tìm tài khoản bằng ID tài khoản");
        System.out.println("4. Tìm tài khoản bằng tên khách hàng");
        System.out.println("5. Sửa mật khẩu tài khoản");
        System.out.println("6. Sửa tên khách hàng");
        System.out.println("7. Sửa số điện thoại khách hàng");
        System.out.println("8. Sửa số cmnd/cccd của khách hàng");
        System.out.println("9. Thêm tiền chơi cho tài khoản");
        System.out.println("10. Hiển thị danh sách khách hàng");
        System.out.println("11. Kiểm tra số dư tài khoản khách hàng");
        System.out.println("0. Thoát");
    }
}
