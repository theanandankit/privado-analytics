package com.analytics.analytics.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.analytics.analytics.entity.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerRepository {


    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Buyer saveBuyer(Buyer buyer) {
        dynamoDBMapper.save(buyer);
        return buyer;
    }

    public Buyer getBuyerById(String buyerId) {
        return dynamoDBMapper.load(Buyer.class, buyerId);
    }

    public String deleteBuyerById(String buyerId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Buyer.class, buyerId));
        return "Buyer Id : "+ buyerId +" Deleted!";
    }

    public String updateBuyer(String buyerId, Buyer buyer) {
        dynamoDBMapper.save(buyer,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("buyerId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(buyerId)
                                )));
        return buyerId;
    }
}
