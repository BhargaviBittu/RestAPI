package com.example.restapiexample.demo;

import com.example.restapiexample.demo.model.Product;
import com.example.restapiexample.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	private ProductRepository productRepository;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product testProduct = new Product();
		testProduct.setName("SimpleProduct");
		testProduct.setDescription("This is for testing purpose");
		testProduct.setCategory("SPECIAL");
		testProduct.setType("CUSTOM");

		productRepository.save(testProduct);

	}
}
