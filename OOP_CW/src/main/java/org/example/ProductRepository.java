package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class ProductRepository implements IRepository {
    ArrayList<Product> products;
    private XmlRepository repository;

    public ProductRepository() {
        this.repository = new XmlRepository();
        this.products = repository.loadProducts();
    }

    public void getProducts() {
        repository.loadProducts();
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
        repository.saveProducts(products);
    }

    @Override
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
}
