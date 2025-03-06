package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class ShoppingCart implements IShoppingCart, IRepository{
    ArrayList<Product> products;
    private XmlRepository repository;

    public ShoppingCart() {
        this.repository = new XmlRepository();
        this.products = repository.loadProducts();
    }

    @Override
    public void getProducts() {
        repository.loadProducts();
    }

    // Add products to list
    public void addProduct(Product product) {
        products.add(product);
        repository.saveProducts(products);
    }

    // Remove products from list
    public void removeProduct(int id) {
        try{
            if (products.removeIf(product -> Objects.equals(product.getId(), id))) {
                System.out.println("Id: " + id + " was removed.");
                repository.saveProducts(products);
            } else {
                throw new Exception("Id: " + id + " can not be found.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    // Sum prices
    public double getTotalCost() {
        double totalCost = 0;

        for (Product product : products) {
            totalCost+=product.getPrice();
        }

        return totalCost;
    }
}