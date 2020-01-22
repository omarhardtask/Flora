package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCustomer {

    @SerializedName("customers")
    @Expose
    private List<Customer> customers = null;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @SerializedName("customer")
    @Expose
    private Customer customer = null;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @SerializedName("errors")
    @Expose
    private Errors_ errors;

    public Errors_ getErrors() {
        return errors;
    }

    public void setErrors(Errors_ errors) {
        this.errors = errors;
    }
}
