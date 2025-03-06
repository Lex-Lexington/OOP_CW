package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("---------------------------");

            System.out.println("1.Get Product");
            System.out.println("2.Add Product");
            System.out.println("3.Delete Product");
            System.out.println("4.Exit");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter number: ");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter product name: ");
                    cart.getProducts();
                    break;
                case 2:
                    //Id
                    Scanner productId = new Scanner(System.in);
                    System.out.println("Enter product id: ");

                    int id = productId.nextInt();  // Read user input
                    System.out.println("Product id is: " + id);

                    //Name
                    Scanner productName = new Scanner(System.in);
                    System.out.println("Enter product name: ");

                    String name = productName.nextLine();  // Read user input
                    System.out.println("Product name is: " + name);

                    //Price
                    Scanner productPrice = new Scanner(System.in);
                    System.out.println("Enter product price: ");

                    double price = productPrice.nextDouble();  // Read user input
                    System.out.println("Product price is: " + price);

                    Product newProduct = new Product(id, name, price);

                    cart.addProduct(newProduct);

                    break;

                case 3:
                    Scanner productRemoveId = new Scanner(System.in);
                    System.out.println("Enter product name: ");

                    int removeId = productRemoveId.nextInt();
                    System.out.printf("Product %d is removed", removeId);

                    cart.removeProduct(removeId);

                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println("---------------------------");
        }
    }
}