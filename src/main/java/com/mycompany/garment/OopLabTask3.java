package com.mycompany.garment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Garment {

    public String id, name, description, size, color;
    public double price;
    public int stockQuantity;

    public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    void updateStock(int quantity) {
        this.stockQuantity = quantity;
    }

    double calculateDiscountPrice(double discountPercentage) {
        return price - (price * discountPercentage / 100);
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
        return pricePerMeter * meters;
    }
}

class Supplier {

    public String id, name, contactInfo;
    List<Fabric> suppliedFabric = new ArrayList<>();

    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

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

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderDate = new Date();
    }

    void addGarment(Garment garment) {
        garments.add(garment);
        totalAmount += garment.price;
    }

    double calculateTotalAmount() {
        return totalAmount;
    }

    void printOrderDetails() {
        System.out.println("Order ID: " + orderId + ", Date: " + orderDate);
        garments.forEach(g -> System.out.println("Name: " + g.name + ", Price: " + g.price + ", Description: " + g.description));
        System.out.println("Total Amount: " + totalAmount);
    }
}

class Customer {

    public String customerId, name, email, phone;

    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    void placeOrder(Order order) {
        order.printOrderDetails();
        System.out.println("Order Placed Successfully.");
    }
}

class Inventory {

    List<Garment> garments = new ArrayList<>();

    void addGarment(Garment garment) {
        garments.add(garment);
    }

    void removeGarment(String id) {
        garments.removeIf(g -> g.id.equals(id));
    }

    Garment findGarment(String id) {
        return garments.stream().filter(g -> g.id.equals(id)).findFirst().orElse(null);
    }
}

public class OopLabTask3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        List<Order> orders = new ArrayList<>();

        while (true) {
            System.out.println("1. Add Garment\n2. View Garment\n3. Place Order\n4. View Orders\n");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Size: ");
                    String size = scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Stock Quantity: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    Garment garment = new Garment(id, name, desc, size, color, price, stock);
                    inventory.addGarment(garment);
                    System.out.println("Garment added.");
                }
                case 2 -> {
                    System.out.print("Garment ID to View: ");
                    String garmentId = scanner.nextLine();
                    Garment garment = inventory.findGarment(garmentId);
                    if (garment != null) {
                        System.out.println("Name: " + garment.name);
                        System.out.println("Description: " + garment.description);
                        System.out.println("Price: " + garment.price);
                        System.out.println("Stock Quantity: " + garment.stockQuantity);
                        System.out.print("Enter discount percentage: ");
                        double discount = scanner.nextDouble();
                        System.out.println("Discounted Price: " + garment.calculateDiscountPrice(discount));
                    } else {
                        System.out.println("Garment not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Order ID: ");
                    String orderId = scanner.nextLine();
                    Order order = new Order(orderId);
                    while (true) {
                        System.out.print("Enter Garment ID to add to order or type 'done': ");
                        String garmentId = scanner.nextLine();
                        if (garmentId.equals("done")) {
                            break;
                        }
                        Garment garment = inventory.findGarment(garmentId);
                        if (garment != null) {
                            order.addGarment(garment);
                            System.out.println("Garment added to order.");
                        } else {
                            System.out.println("Garment not found.");
                        }
                    }
                    orders.add(order);
                    System.out.println("Order Total: " + order.calculateTotalAmount());
                }
                case 4 -> {
                    System.out.println("--- Orders ---");
                    for (Order order : orders) {
                        order.printOrderDetails();
                    }
                }
                default ->
                    System.out.println("Invalid choice.");
            }
        }
    }
}
