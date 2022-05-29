package com.analytics.analytics.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import com.analytics.analytics.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {


    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Product saveProduct(Product product) {
        dynamoDBMapper.save(product);
        return product;
    }

    public Product getProductById(String productId) {
        return dynamoDBMapper.load(Product.class, productId);
    }

    public String deleteProductById(String productId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Product.class, productId));
        return "Product Id : "+ productId +" Deleted!";
    }

    public String updateProduct(String productId, Product product) {
        dynamoDBMapper.save(product,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("productId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(productId)
                                )));
        return productId;
    }
}
