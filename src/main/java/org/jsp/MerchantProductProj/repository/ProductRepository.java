package org.jsp.MerchantProductProj.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.MerchantProductProj.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("select m.products from Merchant m where m.id=?1")
	List<Product> findByMerchantId(int merchant_id);
	
	@Query("select p from Product p where p.brand=?1")
	List<Product> findByBrand(String brand);
	
	@Query("select p from Product p where p.category=?1")
	List<Product> findByCategory(String category);
	
}
