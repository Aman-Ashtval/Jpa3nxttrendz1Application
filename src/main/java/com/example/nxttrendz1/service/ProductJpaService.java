package com.example.nxttrendz1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.repository.ProductJpaRepository;
import com.example.nxttrendz1.repository.ReviewJpaRepository;
import com.example.nxttrendz1.repository.ProductRepository;

@Service
public class ProductJpaService implements ProductRepository {
    @Autowired
    private ProductJpaRepository productJpa;

    @Autowired
    private ReviewJpaRepository reviewJpa;

    @Override
    public ArrayList<Product> getProducts() {
        List<Product> productList = productJpa.findAll();
        ArrayList<Product> products = new ArrayList<>(productList);
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        try {
            Product product = productJpa.findById(productId).get();
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product addProduct(Product product) {
        productJpa.save(product);
        return product;
    }

    @Override
    public Product updateProduct(int productId, Product product) {
        try {
            Product newProduct = productJpa.findById(productId).get();

            if (product.getProductName() != null)
                newProduct.setProductName(product.getProductName());
            if (product.getPrice() != 0)
                newProduct.setPrice(product.getPrice());

            productJpa.save(newProduct);
            return newProduct;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = this.getProductById(productId);
        if (product != null) {
            productJpa.deleteById(productId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public Product getReviewProduct(int reviewId) {
        try {
            Review review = reviewJpa.findById(reviewId).get();
            return review.getProduct();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}