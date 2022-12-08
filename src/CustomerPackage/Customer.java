package CustomerPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String identityCardNumber;
    private Date createdDate;
    private long playMoney;

    public Customer(String id, String password, String name, String phoneNumber, String identityCardNumber, long playMoney) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.identityCardNumber = identityCardNumber;
        this.createdDate = new Date();
        this.playMoney = playMoney;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getPlayMoney() {
        return playMoney;
    }

    public void setPlayMoney(long playMoney) {
        this.playMoney = playMoney;
    }

    public String stringCreatedDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.format(getCreatedDate());
    }

    @Override
    public String toString() {
        return "=====THÀNH VIÊN=====" + "\n" +
                "ID: " + id + "\n" +
                "Mật khẩu: " + password + "\n" +
                "Họ và tên: " + name + "\n" +
                "Số điện thoại: " + phoneNumber + "\n" +
                "Số CCCD/CMND: " + identityCardNumber + "\n" +
                "Ngày tạo tài khoản: " + stringCreatedDay() + "\n" +
                "Số tiền trong tài khoản: " + playMoney + "\n" +
                "===================" + "\n";
    }

    public String toFile() {
        return id + "," + password + "," + name + "," + phoneNumber + "," + identityCardNumber + "," + stringCreatedDay() + "," + playMoney;
    }
}
