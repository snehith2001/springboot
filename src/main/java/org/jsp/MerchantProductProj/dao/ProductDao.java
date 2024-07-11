package org.jsp.MerchantProductProj.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.MerchantProductProj.dto.Merchant;
import org.jsp.MerchantProductProj.dto.Product;
import org.jsp.MerchantProductProj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Optional<Product> findById(int id){
		return productRepository.findById(id);
	}
	
	public List<Product> findByBrand(String brand){
		return productRepository.findByBrand(brand);
	}
	
	public List<Product> findByCategory(String category){
		return productRepository.findByCategory(category);
	}
	
	public List<Product> findByMerchantId(int merchant_id){
		return productRepository.findByMerchantId(merchant_id);
	}
	
	public List<Product> findAllProducts(){
		return productRepository.findAll();
	}
	
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}
	
}
