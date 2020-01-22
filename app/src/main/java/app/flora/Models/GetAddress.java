package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetAddress {
    @SerializedName("addresses")
    @Expose
    private ArrayList<Address> addresses = null;

    @SerializedName("address")
    @Expose
    private Address address = null;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }
}
