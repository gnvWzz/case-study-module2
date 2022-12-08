package InvoicePackage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InvoiceManagement {
    List<Invoice> invoiceList;

    private static final InvoiceManagement invoiceManagement = new InvoiceManagement();

    private InvoiceManagement() {
        invoiceList = new ArrayList<>();
        readFile();
    }

    public static InvoiceManagement getInvoiceManagement() {
        return invoiceManagement;
    }

    public void addInvoice(Invoice invoice) {
        invoiceList.add(invoice);
        invoice.setLeftMoneyAccount();
        saveToFile();
    }

    public boolean removeInvoice(String id) {
        for (Invoice invoice :
                invoiceList) {
            if (invoice.getInvoiceId().equals(id)) {
                invoiceList.remove(invoice);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Invoice searchInvoice(String id) {
        for (Invoice invoice :
                invoiceList) {
            if (invoice.getInvoiceId().equals(id)) {
                return invoice;
            }
        }
        return null;
    }

    public boolean isInvoiceIdExist(String id) {
        for (Invoice invoice :
                invoiceList) {
            if (invoice.getInvoiceId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Danh sách hoá đơn: " + "\n" +
                invoiceList + "\n";
    }

    public void saveToFile() {
        try {
            File file = new File("Invoices.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Invoice invoice: invoiceList) {
                bufferedWriter.write(invoice.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        invoiceList.clear();
        try {
            File file = new File("Invoices.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            Invoice invoice;
            while ((line = bufferedReader.readLine()) != null) {
                invoice = getInvoice(line);
                invoiceList.add(invoice);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Invoice getInvoice(String line) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Invoice invoice;
        String[] ss;
        ss = line.split(",");
        invoice = new Invoice(simpleDateFormat.parse(ss[0]),ss[1],ss[3]);
        for (int i = 4; i <= ss.length - 5; i+=6) {
            invoice.addRoom(ss[i],Long.parseLong(ss[i+3]));
        }
        invoice.setCustomerName(ss[2]);
        return invoice;
    }
}
