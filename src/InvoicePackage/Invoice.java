package InvoicePackage;

import CustomerPackage.CustomerManagement;
import RoomPackage.RoomManagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Invoice {
    RoomManagement roomManagement = RoomManagement.getRoomManagement();
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    private String invoiceId;
    private String customerId;
    private String customerName;
    private String roomId;
    private Date date;
    private final Map<String,Long> roomsList = new HashMap<>();

    public Invoice() {
    }

    public Invoice(Date date, String invoiceId, String customerId) {
        this.date = date;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void addRoom(String roomId, long quantity) {
        roomsList.put(roomId,quantity);
    }

    public StringBuilder displayProduct() {
        StringBuilder result = new StringBuilder();
        for (String key: roomsList.keySet()) {
            result.append(roomManagement.searchRoom(key).getId()).append("           ").append(roomManagement.searchRoom(key).getPricePerHour()).append("                   ").append(roomsList.get(key)).append("                ").append(roomManagement.searchRoom(key).getPricePerHour() * roomsList.get(key)).append("\n");
        }
        return result;
    }

    public String StringMap() {
        StringBuilder result = new StringBuilder();
        for (String key: roomsList.keySet()) {
            result.append(roomManagement.searchRoom(key).getId()).append(",").append(roomManagement.searchRoom(key).getPricePerHour()).append(",x,").append(roomsList.get(key)).append(",=,").append(roomManagement.searchRoom(key).getPricePerHour() * roomsList.get(key)).append(",");
        }
        return result.toString();
    }

    public long totalPrice() {
        long totalPrice = 0;
        for (String key: roomsList.keySet()) {
            totalPrice += roomsList.get(key) * roomManagement.searchRoom(key).getPricePerHour();
        }
        return totalPrice;
    }

    public void setLeftMoneyAccount() {
        customerManagement.searchCustomerById(customerId).setPlayMoney(customerManagement.searchCustomerById(customerId).getPlayMoney() - totalPrice());
        customerManagement.saveToFile();
    }

    public String stringGetDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.format(getDate());
    }

    @Override
    public String toString() {
        return "=====HO?? ????N D???CH V???=====" + "\n" +
                "Ng??y l???p ho?? ????n: " + stringGetDate() + "\n" +
                "ID ho?? ????n: " + invoiceId + "\n" +
                "ID kh??ch h??ng: " + getCustomerId() + "\n" +
                "Danh s??ch ph??ng ???? s??? d???ng:" + "\n" +
                "T??n ph??ng" + " | " + "????n gi?? (theo gi???)" + " | " + " " + "S??? gi??? ???? s??? d???ng" + " | " + " " + "T???ng ti???n ph??ng" + "\n" +
                displayProduct() + "\n" +
                "T???ng ti???n ho?? ????n: " + totalPrice() + "\n" +
                "===================" + "\n";
    }

    public String toFile() {
        return stringGetDate() + "," + invoiceId + "," + customerId + "," + StringMap() + "," + totalPrice();
    }
}
