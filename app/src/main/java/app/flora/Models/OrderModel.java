package app.flora.Models;

public class OrderModel {

    String date , title, city , status , order_num , price;

    public OrderModel(String date, String desc, String city, String status, String order_num, String price) {
        this.date = date;
        this.title = desc;
        this.city = city;
        this.status = status;
        this.order_num = order_num;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getStatus() {
        return status;
    }

    public String getOrder_num() {
        return order_num;
    }

    public String getPrice() {
        return price;
    }
}
