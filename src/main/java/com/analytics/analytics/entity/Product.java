package com.analytics.analytics.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "product_usage_metrics")
public class Product {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private Long id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private Long orderId;

    @DynamoDBAttribute
    private String price;

    @DynamoDBAttribute
    private String manufacturedOn;

    @DynamoDBAttribute
    private String buyerEmail;
}