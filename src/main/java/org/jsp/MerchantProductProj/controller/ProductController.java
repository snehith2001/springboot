package org.jsp.MerchantProductProj.controller;

import java.util.List;

import org.jsp.MerchantProductProj.dto.Merchant;
import org.jsp.MerchantProductProj.dto.Product;
import org.jsp.MerchantProductProj.dto.ResponseStructure;
import org.jsp.MerchantProductProj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product,@PathVariable int id) {
		return productService.addProduct(product, id);
	}
	
	@PutMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	@GetMapping("/products/ByMerchantId/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(@PathVariable int merchant_id) {
		return productService.findByMerchantId(merchant_id);
	}
	
	@GetMapping("/products/ByBrand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable String brand) {
		return productService.findByBrand(brand);
	}
	
	@GetMapping("/products/ByCategory/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String category) {
		return productService.findByCategory(category);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		return productService.findAllProducts();
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id){
		return productService.deleteProduct(id);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable int id) {
		return productService.findById(id);
}
}