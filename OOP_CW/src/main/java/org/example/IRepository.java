package org.example;

public interface IRepository {
    void getProducts();
    void addProduct(Product product);
    void removeProduct(int id);
}
