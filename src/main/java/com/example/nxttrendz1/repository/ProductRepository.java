package com.example.nxttrendz1.repository;

import java.util.ArrayList;
import com.example.nxttrendz1.model.Product;

public interface ProductRepository {

    ArrayList<Product> getProducts();

    Product getProductById(int ProductId);

    Product addProduct(Product product);

    Product updateProduct(int productId, Product product);

    void deleteProduct(int productId);

    Product getReviewProduct(int reviewId);
}