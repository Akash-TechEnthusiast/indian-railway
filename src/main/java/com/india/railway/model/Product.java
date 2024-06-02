package com.india.railway.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "products")
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;

    // Getters and Setters
}
