package com.india.railway.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.Product;
import com.india.railway.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ElasticProductController {

    private ProductService productService;

    @Autowired
    public void ProductController(ProductService productService) {
        this.productService = productService;
    }
    // https://localhost:9191/railway/products
    //{
    //    "name":"laptop charger",
    //    "description":"laptop 1.1.1",
    //    "price":20,
    //    "id":2
    //}
    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable String id) {
        return productService.findById(id);
    }
    // https://localhost:9191/railway/products/name/laptop
    @GetMapping("/name/{name}")
    public List<Product> findByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        productService.deleteById(id);
    }
}
