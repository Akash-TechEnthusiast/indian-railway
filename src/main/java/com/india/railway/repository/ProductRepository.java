package com.india.railway.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.india.railway.model.Product;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByName(String name);
}
