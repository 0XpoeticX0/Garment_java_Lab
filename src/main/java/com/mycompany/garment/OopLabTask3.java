package com.mycompany.garment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Garment {
    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
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
        return price - (price * (discountPercentage / 100));
    }
}

class Fabric {
    public String id;
    public String type;
    public String color;
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
    public String id;
    public String name;
    public String contactInfo;
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
        for (Garment g : garments) {
            System.out.println("Name: " + g.name + ", Price: " + g.price + ", Description: " + g.description);
        }
        System.out.println("Total Amount: " + totalAmount);
    }
}

class Customer {
    public String customerId;
    public String name;
    public String email;
    public String phone;

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
        for (Garment g : garments) {
            if (g.id.equals(id)) return g;
        }
        return null;
    }
}

public class OopLabTask3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        List<Order> orders = new ArrayList<>();
        
        while (true) {
            System.out.println("\n--- Garment Management System ---");
            System.out.println("1. Add Garment to Inventory");
            System.out.println("2. View Garment in Inventory");
            System.out.println("3. Place an Order");
            System.out.println("4. View Orders");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Garment ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Size: ");
                    String size = scanner.nextLine();
                    System.out.print("Enter Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int stockQuantity = scanner.nextInt();
                    scanner.nextLine();

                    Garment garment = new Garment(id, name, description, size, color, price, stockQuantity);
                    inventory.addGarment(garment);
                    System.out.println("Garment added to inventory.");
                    break;

                case 2:
                    System.out.print("Enter Garment ID to view: ");
                    id = scanner.nextLine();
                    Garment foundGarment = inventory.findGarment(id);
                    if (foundGarment != null) {
                        System.out.println("Name: " + foundGarment.name + ", Price: " + foundGarment.price + ", Stock: " + foundGarment.stockQuantity);
                    } else {
                        System.out.println("Garment not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Order ID: ");
                    String orderId = scanner.nextLine();
                    Order order = new Order(orderId);

                    System.out.print("Enter Garment ID to add to order: ");
                    id = scanner.nextLine();
                    Garment garmentToOrder = inventory.findGarment(id);
                    if (garmentToOrder != null) {
                        order.addGarment(garmentToOrder);
                        orders.add(order);
                        System.out.println("Garment added to order.");
                    } else {
                        System.out.println("Garment not found.");
                    }
                    break;

                case 4:
                    for (Order ord : orders) {
                        ord.printOrderDetails();
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }
}
