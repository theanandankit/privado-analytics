package com.analytics.analytics.services;

import com.analytics.analytics.dao.ProductRepository;
import com.analytics.analytics.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
            return productRepository.saveProduct(product);
    }

    public Product getProductById(String productId) {
        return productRepository.getProductById(productId);
    }

    public String updateProduct(String productId, Product product) {
        return productRepository.updateProduct(productId, product);
    }

    public String updateProductBuyerEmail(String productId, String email) {
        Product product = productRepository.getProductById(productId);
        product.setBuyerEmail(email);
        return productRepository.updateProduct(productId, product);
    }
}
