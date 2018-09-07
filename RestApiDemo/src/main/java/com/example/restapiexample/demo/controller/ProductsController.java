package com.example.restapiexample.demo.controller;

import com.example.restapiexample.demo.model.Product;
import com.example.restapiexample.demo.repository.ProductRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.Optional;
@RestController
@RequestMapping(path = "/api/products/")
public class ProductsController {

    private ProductRepository productRepository;
    private Logger LOG = LoggerFactory.getLogger(ProductsController.class);
       @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable(name = "id") String id){
        return productRepository.findById(id).orElse(null);

    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave){
        return  productRepository.save(productToSave);
    }

    @RequestMapping(path = "{id}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id){
           Product foundProduct = productRepository.findById(id).orElse(null);
           if (foundProduct != null){
               foundProduct.setName(productToUpdate.getName());
               foundProduct.setDescription(productToUpdate.getDescription());
               foundProduct.setCategory(productToUpdate.getCategory());
               foundProduct.setType(productToUpdate.getType());
               return productRepository.save(foundProduct);
           }
           else{
                LOG.info("No Products found with given id");
                return productToUpdate;
           }
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") String id){
        Product foundProduct = productRepository.findById(id).orElse(null);
        if (foundProduct!= null) {
            productRepository.delete(foundProduct);
        }

    }
}

