package org.jsp.MerchantProductProj.service;


import java.util.List;
import java.util.Optional;

import org.jsp.MerchantProductProj.dao.MerchantDao;
import org.jsp.MerchantProductProj.dao.ProductDao;
import org.jsp.MerchantProductProj.dto.Merchant;
import org.jsp.MerchantProductProj.dto.Product;
import org.jsp.MerchantProductProj.dto.ResponseStructure;
import org.jsp.MerchantProductProj.exception.MerchantNotFoundException;
import org.jsp.MerchantProductProj.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> addProduct(Product product, int merchant_id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> dbMerchant = merchantDao.findById(merchant_id);
		if (dbMerchant.isPresent()) {
			Merchant merchant = dbMerchant.get();
			product.setMerchant(merchant);
			merchant.getProducts().add(product);
			structure.setMessage("Product Added");
			structure.setData(productDao.addProduct(product));
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new MerchantNotFoundException("Product is Not Added...! Invalid Merchant Id");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(product.getId());
		if (recProduct.isPresent()) {
			Product dbProduct = recProduct.get();
			dbProduct.setBrand(product.getBrand());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setCost(product.getCost());
			dbProduct.setImage_url(product.getImage_url());
			dbProduct.setName(product.getName());
			structure.setData(productDao.addProduct(dbProduct));
			structure.setMessage("Update Product Successfull");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new ProductNotFoundException("Updated UnSuccessfull..! Invalid Product Id");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int merchant_id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products=productDao.findByMerchantId(merchant_id);
		if(products.size()>0) {
			structure.setMessage("Product Found");
			structure.setData(productDao.findByMerchantId(merchant_id));
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Merchant Id");
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products=productDao.findByBrand(brand);
		if(products.size()>0) {
			structure.setMessage("Product Found");
			structure.setData(productDao.findByBrand(brand));
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Brand");
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products=productDao.findByCategory(category);
		if(products.size()>0) {
			structure.setMessage("Product Found");
			structure.setData(productDao.findByCategory(category));
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Category");
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts(){
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		List<Product> products=productDao.findAllProducts();
		if(products.size()>0) {
			structure.setData(productDao.findAllProducts());
			structure.setMessage("Products Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Products");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Product> products=productDao.findById(id);
		if(products.isPresent()) {
			productDao.deleteProduct(products.get());
			structure.setMessage("product Found");
			structure.setData("product Deleted");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		throw new ProductNotFoundException("Product not found");
	}
	public ResponseEntity<ResponseStructure<Product>> findById(int id){
		ResponseStructure<Product> structure=new ResponseStructure<>();
		Optional<Product> products=productDao.findById(id);
		if(products.isPresent()) {
			structure.setMessage("Product Found");
			structure.setData(products.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid product Id");
	}
	
	
}