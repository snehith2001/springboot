package org.jsp.MerchantProductProj.exception;

import org.jsp.MerchantProductProj.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MerchantProductExceptionHandler extends ResponseEntityExceptionHandler {
		
	@ExceptionHandler(MerchantNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> MNFE(MerchantNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Merchant not Found");
		structure.setData(exception.getMessage());
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> PNFE(ProductNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Product not Found");
		structure.setData(exception.getMessage());
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}