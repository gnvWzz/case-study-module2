package CustomerPackage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerManagement {
    List<Customer> customers;
    private static final CustomerManagement customerManagement = new CustomerManagement();

    private CustomerManagement() {
        customers = new ArrayList<>();
        readFile();
    }

    public static CustomerManagement getCustomerManagement() {
        return customerManagement;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        saveToFile();
    }

    public boolean removeCustomer(String id) {
        for (Customer customer :
                customers) {
            if (customer.getId().equals(id)) {
                customers.remove(customer);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Customer searchCustomerById(String id) {
        for (Customer customer :
                customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> searchCustomerByName(String name) {
        List<Customer> customerList = new ArrayList<>();
        for (Customer customer :
                customers) {
            if (customer.getName().equals(name)) {
                customerList.add(customer);
            }
        }
        return customerList;
    }

    public void editCustomerPassword(String id, String password) {
        for (Customer customer :
                customers) {
            if (customer.getId().equals(id)) {
                customer.setPassword(password);
                saveToFile();
            }
        }
    }

    public void editCustomerName(String id, String name) {
        for (Customer customer :
                customers) {
            if (customer.getId().equals(id)) {
                customer.setName(name);
                saveToFile();
            }
        }
    }

    public void editCustomerPhoneNumber(String id, String phoneNumber) {
        for (Customer customer :
                customers) {
            if (customer.getId().equals(id)) {
                customer.setPhoneNumber(phoneNumber);
                saveToFile();
            }
        }
    }

    public void editCustomerIdentityCardNumber(String id, String identityCardNumber) {
        for (Customer customer :
                customers) {
            if (customer.getId().equals(id)) {
                customer.setIdentityCardNumber(identityCardNumber);
                saveToFile();
            }
        }
    }

    public void addCustomerPlayMoney(String id, long playMoney) {
        for (Customer customer :
                customers) {
            if (customer.getId().equals(id)) {
                customer.setPlayMoney(customer.getPlayMoney() + playMoney);
            }
        }
        saveToFile();
    }

    public boolean isAccountIdExist(String accountId){
        for (Customer customer: customers) {
            if (customer.getId().equals(accountId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPhoneNumberExist(String phoneNumber){
        for (Customer customer: customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIdentityCardNumberExist(String identityCardNumber){
        for (Customer customer: customers) {
            if (customer.getIdentityCardNumber().equals(identityCardNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Customer customer :
                customers) {
            result.append(customer);
        }
        return result.toString();
    }

    public void saveToFile() {
        try {
            File file = new File("Customers.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Customer c: customers) {
                bufferedWriter.write(c.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        customers.clear();
        try {
            File file = new File("Customers.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            Customer customer;
            while ((line = bufferedReader.readLine()) != null) {
                customer = getCustomer(line);
                customers.add(customer);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static Customer getCustomer(String line) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Customer customer;
        String[] ss;
        ss = line.split(",");
        customer = new Customer(ss[0],ss[1],ss[2],ss[3],ss[4],Long.parseLong(ss[6]));
        try {
            Date createdDate = simpleDateFormat.parse(ss[5]);
            customer.setCreatedDate(createdDate);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return customer;
    }
}
