package org.example;

public interface IShoppingCart {
    void addProduct(Product product);
    void removeProduct(int id);
    double getTotalCost();
}
