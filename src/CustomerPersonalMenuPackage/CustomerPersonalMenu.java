package CustomerPersonalMenuPackage;

import CustomerPackage.Customer;
import CustomerPackage.CustomerManagement;
import CustomerPackage.ValidateIdentityCardNumber;
import CustomerPackage.ValidatePhoneNumber;

import java.util.Scanner;

public class CustomerPersonalMenu {
    public Scanner sc = new Scanner(System.in);
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    ValidatePhoneNumber validatePhoneNumber = new ValidatePhoneNumber();
    ValidateIdentityCardNumber validateIdentityCardNumber = new ValidateIdentityCardNumber();
    public CustomerPersonalMenu() {
    }

    public void menu() {
        int choice;
        do {
            displayMenu();
            choice = sc.nextInt();
            if (choice == 0)
                break;
            switch (choice) {
                case 1 -> editCPassword();
                case 2 -> editCName();
                case 3 -> editCPhoneNumber();
                case 4 -> editCIdentityCardNumber();
                case 5 -> checkAccountInformation();
                default -> System.out.println("Số nhập vào không hợp lệ. Vui lòng chọn lại");
            }
            System.out.println("Bạn có muốn chọn tiếp không? Nhấn số bất kì để tiếp tục chương trình hoặc nhấn số 0 để thoát chương trình");
            displayMenu();
            choice = sc.nextInt();
        } while (choice != 0);
    }

    private void checkAccountInformation() {
        System.out.println("Bạn chọn xem thông tin tài khoản. Nhập ID tài khoản:");
        sc.nextLine();
        String id = sc.nextLine();
        Customer customer = customerManagement.searchCustomerById(id);
        if (customer != null) {
            System.out.println("Thông tin tài khoản:");
            System.out.println(customer);
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void editCIdentityCardNumber() {
        System.out.println("Bạn chọn sửa số cmnd/cccd. Nhập ID tài khoản để sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Nhập số cmnd/cccd thay thế:");
            String newIdentityCardNumber = sc.nextLine();
            boolean checkIdentityCardNumber = false;
            while (!checkIdentityCardNumber) {
                if (customerManagement.isIdentityCardNumberExist(newIdentityCardNumber)) {
                    System.out.println("Số cmnd/cccd đã trùng. Vui lòng nhập lại.");
                    newIdentityCardNumber = sc.nextLine();
                } else {
                    if (!validateIdentityCardNumber.validate(newIdentityCardNumber)) {
                        System.out.println("""
                            Số cmnd/cccd nhập vào phải đúng định dạng. Số căn cước mỗi cá nhân (cũng là số định danh) gồm dãy 12 chữ số. Trong đó, 6 số đầu là mã quy định, chỉ cần căn cứ trên 6 số này là có thể biết người đó sinh trong thế kỷ 20 hay 21, sinh năm nào, khai sinh ở đâu và là nam hay nữ. Riêng 6 số cuối là những con số ngẫu nhiên "định danh" từng cá nhân.

                            Cụ thể:

                            * 3 chữ số đầu tiên là mã tỉnh nơi công dân đăng ký khai sinh. Mỗi tỉnh, TP có mã số khác nhau gồm 3 chữ số (Ví dụ: TP Hà Nội là 001, TP.HCM là 079…).

                            * 1 chữ số tiếp theo là mã giới tính của công dân. Với người sinh trong thế kỷ 20, giới tính nam là số 0 và nữ là số 1. Với người sinh ở thế kỷ 21, giới tính nam là 2 và nữ là 3.

                            * 2 chữ số tiếp là mã năm sinh (viết tắt 2 số cuối) của công dân.

                            * 6 chữ số cuối: số ngẫu nhiên.

                            Ví dụ: Số CCCD 079215000001""");
                        newIdentityCardNumber = sc.nextLine();
                    } else {
                        checkIdentityCardNumber = true;
                    }
                }
            }
            System.out.println("Để đảm bảo an toàn, vui lòng nhập lại mật khẩu của bạn để tiến hành sửa đổi:");
            String confirmPassword = sc.nextLine();
            boolean checkConfirmPassword = false;
            while (!checkConfirmPassword) {
                if (customerManagement.searchCustomerById(id).getPassword().equals(confirmPassword)) {
                    customerManagement.editCustomerIdentityCardNumber(id,newIdentityCardNumber);
                    System.out.println("Số cmnd/cccd đã được sửa");
                    checkConfirmPassword = true;
                } else {
                    System.out.println("Sai mật khẩu. Vui lòng nhập lại");
                    confirmPassword = sc.nextLine();
                }
            }
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void editCPhoneNumber() {
        System.out.println("Bạn chọn sửa số điện thoại. Nhập ID tài khoản để sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Nhập số điện thoại thay thế:");
            String newPhoneNumber = sc.nextLine();
            boolean checkPhoneNumber = false;
            while (!checkPhoneNumber) {
                if (customerManagement.isPhoneNumberExist(newPhoneNumber)) {
                    System.out.println("Số cmnd/cccd đã trùng. Vui lòng nhập lại.");
                    newPhoneNumber = sc.nextLine();
                } else {
                    if (!validatePhoneNumber.validate(newPhoneNumber)) {
                        System.out.println("Số điện thoại nhập vào phải đúng định dạng, với số đầu tiên là số 0, số tiếp theo là 1 trong các số từ 5 đến 9, và 8 hoặc 9 số tiếp theo nhập theo ý người dùng. Ví dụ: 092345678 hoặc 0523456789");
                        newPhoneNumber = sc.nextLine();
                    } else {
                        checkPhoneNumber = true;
                    }
                }
            }
            System.out.println("Để đảm bảo an toàn, vui lòng nhập lại mật khẩu của bạn để tiến hành sửa đổi:");
            String confirmPassword = sc.nextLine();
            boolean checkConfirmPassword = false;
            while (!checkConfirmPassword) {
                if (customerManagement.searchCustomerById(id).getPassword().equals(confirmPassword)) {
                    customerManagement.editCustomerPhoneNumber(id,newPhoneNumber);
                    System.out.println("Số điện thoại đã được sửa");
                    checkConfirmPassword = true;
                } else {
                    System.out.println("Sai mật khẩu. Vui lòng nhập lại");
                    confirmPassword = sc.nextLine();
                }
            }
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void editCName() {
        System.out.println("Bạn chọn sửa họ và tên. Nhập ID tài khoản để sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Nhập họ và tên thay thế:");
            String newName = sc.nextLine();
            System.out.println("Để đảm bảo an toàn, vui lòng nhập lại mật khẩu của bạn để tiến hành sửa đổi:");
            String confirmPassword = sc.nextLine();
            boolean checkConfirmPassword = false;
            while (!checkConfirmPassword) {
                if (customerManagement.searchCustomerById(id).getPassword().equals(confirmPassword)) {
                    customerManagement.editCustomerName(id,newName);
                    System.out.println("Tên đã được sửa");
                    checkConfirmPassword = true;
                } else {
                    System.out.println("Sai mật khẩu. Vui lòng nhập lại");
                    confirmPassword = sc.nextLine();
                }
            }
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private void editCPassword() {
        System.out.println("Bạn chọn sửa mật khẩu. Nhập ID tài khoản để sửa:");
        sc.nextLine();
        String id = sc.nextLine();
        if (customerManagement.searchCustomerById(id) != null) {
            System.out.println("Để đảm bảo an toàn, vui lòng nhập lại mật khẩu cũ của bạn để tiến hành sửa đổi:");
            String confirmPassword = sc.nextLine();
            boolean checkConfirmPassword = false;
            while (!checkConfirmPassword) {
                if (customerManagement.searchCustomerById(id).getPassword().equals(confirmPassword)) {
                    System.out.println("Nhập mật khẩu mới thay thế:");
                    String newPassword = sc.nextLine();
                    System.out.println("Nhập lại mật khẩu mới thay thế:");
                    String confirmNewPassword = sc.nextLine();
                    boolean checkConfirmNewPassword = false;
                    while (!checkConfirmNewPassword) {
                        if (newPassword.equals(confirmNewPassword)) {
                            customerManagement.editCustomerPassword(id,newPassword);
                            System.out.println("Mật khẩu đã được sửa");
                            checkConfirmNewPassword = true;
                        } else {
                            System.out.println("Xác nhận lại mật khẩu mới không thành công. Vui lòng nhập lại:");
                            confirmNewPassword = sc.nextLine();
                        }
                    }
                    checkConfirmPassword = true;
                } else {
                    System.out.println("Sai mật khẩu cũ. Vui lòng nhập lại");
                    confirmPassword = sc.nextLine();
                }
            }
        } else {
            System.out.println("Tài khoản không tồn tại");
        }
    }

    private static void displayMenu() {
        System.out.println("===== MENU QUẢN LÝ TÀI KHOẢN (dành cho khách hàng) =====");
        System.out.println("1. Sửa mật khẩu tài khoản");
        System.out.println("2. Sửa họ và tên chủ tài khoản");
        System.out.println("3. Sửa số điện thoại của tài khoản");
        System.out.println("4. Sửa số cmnd/cccd của chủ tài khoản");
        System.out.println("5. Hiển thị thông tin tài khoản");
        System.out.println("0. Thoát");
    }
}
