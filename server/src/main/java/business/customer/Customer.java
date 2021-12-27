package business.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Customer {

    private long customerId;
    private String customerName;
    private String address;
    private String phone;
    private String email;
    private String ccNumber;
    private Date ccExpDate;

    public Customer(long customerId, String customerName, String address, String phone, String email, String ccNumber, Date ccExpDate) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.ccNumber = ccNumber;
        this.ccExpDate = ccExpDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoneDisplay() {
        String phoneDigits = phone.replaceAll("[\\s)(-]", "");
        return "(" + phoneDigits.substring(0, 3) + ") " + phoneDigits.substring(3, 6) + "-" + phoneDigits.substring(6);
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getCcNumber() {
        return ccNumber;
    }

    public String getCcNumberDisplay() {
        return ccNumber.replaceAll("[\\s-]", "").replaceAll(".(?=.{4})", "*");
    }

    public Date getCcExpDate() {
        return ccExpDate;
    }

    public String getCcExpDateDisplay() {
        return (ccExpDate.getMonth() < 9 ? "0" : "") + (ccExpDate.getMonth() + 1) + "/" + (ccExpDate.getYear() + 1900);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpDate=" + ccExpDate +
                '}';
    }
}
