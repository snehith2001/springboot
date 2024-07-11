package org.jsp.MerchantProductProj.controller;

import org.jsp.MerchantProductProj.dto.Merchant;
import org.jsp.MerchantProductProj.dto.ResponseStructure;
import org.jsp.MerchantProductProj.service.MerchantService;
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
public class MerchantController {
	@Autowired
	private MerchantService merchantService;
	
	@PostMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
		return merchantService.saveMerchant(merchant);
	}
	
	@GetMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@RequestParam int id) {
		return merchantService.findById(id);
	}
	
	@PutMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return merchantService.updateMerchant(merchant);
	}
	
	@PostMapping("/merchants/verify-phone")
	public ResponseEntity<ResponseStructure<Merchant>> veriftMerchant(@RequestParam long phone,@RequestParam String password) {
		return merchantService.verifyMerchant(phone, password);
	}
	
	@PostMapping("/merchants/verify-email")
	public ResponseEntity<ResponseStructure<Merchant>> veriftMerchant(@RequestParam String email,@RequestParam String password) {
		return merchantService.verifyMerchant(email, password);
	}
	
	@DeleteMapping("/merchants/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteMerchant(@PathVariable int id) {
		return merchantService.deleteMerchant(id);
	}
}