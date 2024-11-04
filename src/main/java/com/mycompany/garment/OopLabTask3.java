package com.mycompany.garment;
//importing list and array list

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

class Garment {

    public String id, name, description, size, color;
    public double price;
    public int stockQuantity;

    void updateStock(int quantity) {
        this.stockQuantity = quantity;
    }
    
    public Garment(String id, String name, String size, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    double calculateDiscountPrice(double discountPercentage) {
        double discount = price * (discountPercentage / 100);
        return discount;
    }
}

class Fabric {

    public String id, type, color;
    public double pricePerMeter;
    
    public Fabric(String id, String type, String color, double pricePerMeter) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pricePerMeter = pricePerMeter;
    }

    double calculateCost(double meters) {
        double newPrice = pricePerMeter * meters;
        return newPrice;
    }
}

class Supplier {

    public String id, name, contactInfo;
    
    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }


    List<Fabric> suppliedFabric = new ArrayList<>();

    void addFabric(Fabric fabric) {
        suppliedFabric.add(fabric);
    }

    List<Fabric> getSuppliedFabrics() {
        return suppliedFabric;
    }
}

class Order {

    public String orderId;
    public Date orderDate;
    public List<Garment> garments = new ArrayList<>();
    private double totalAmount;

    void addGarment(Garment garment) {
        garments.add(garment);
    }

    double calculateTotalAmount() {
        for (Garment g : garments) {
            totalAmount = totalAmount + g.price;
        }
        return totalAmount;
    }

    void printOrderDetails() {
        System.out.println("--------------------------");
        System.out.println("Order Details");
        System.out.println("--------------------------");
        for (Garment g : garments) {
            System.out.println("Name: " + g.name);
            System.out.println("Price: " + g.price);
            System.out.println("Description: " + g.description);
            System.out.println("--------------------------");
        }
    }
}

class Customer {

    public String customerId;
    public String name;
    public String email;
    public String phone;

    void placeOrder(Order order) {
        order.printOrderDetails();
        System.out.println("Order Placed");
    }

    List<Order> viewOrders() {
        
        return null;
        
    }
}

class Inventory {

    List<Garment> garments;

    void addGarment(Garment garment) {
        garments.add(garment);
    }

    void removeGarment(String id) {
        garments.remove(id);
    }

    Garment findGarment(String id) {
        for (Garment g : garments) {
            if(g.id == id)
                return g;
        }
        return null;
    }
}

public class OopLabTask3 {

    public static void main(String[] args) {
        
    }

}