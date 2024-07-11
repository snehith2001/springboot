package org.jsp.MerchantProductProj.service;

import java.util.Optional;

import org.jsp.MerchantProductProj.dao.MerchantDao;
import org.jsp.MerchantProductProj.dto.Merchant;
import org.jsp.MerchantProductProj.dto.ResponseStructure;
import org.jsp.MerchantProductProj.exception.MerchantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		structure.setMessage("Merchant Save Successfull");
		structure.setData(merchantDao.saveMerchant(merchant));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant){
		Optional<Merchant> recMerchant=merchantDao.findById(merchant.getId());
		if(recMerchant.isPresent()) {
			Merchant dbMerchant=recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setGst(merchant.getGst());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setPassword(merchant.getPassword());
			ResponseStructure<Merchant> structure=new ResponseStructure<>();
			structure.setMessage("Merchant Updated Successfully");
			structure.setData(merchantDao.saveMerchant(dbMerchant));
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
		}
		throw new MerchantNotFoundException("Invalid Merchant Id");
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone,String password){
		Optional<Merchant> dbMerchant=merchantDao.verifyMerchant(phone, password);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if(dbMerchant.isPresent()) {
			structure.setMessage("Merchant found");
			structure.setData(dbMerchant.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Phone Number or Password");
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(String email,String password){
		Optional<Merchant> dbMerchant=merchantDao.verifyMerchant(email, password);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if(dbMerchant.isPresent()) {
			structure.setMessage("Merchant found");
			structure.setData(dbMerchant.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Email Id or Password");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteMerchant(int id){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Merchant> dbMerchant=merchantDao.findById(id);
		if(dbMerchant.isPresent()) {
			merchantDao.deleteMerchant(dbMerchant.get());
			structure.setMessage("Merchant Found");
			structure.setData("Merchant Deleted");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Merchant Not Deleted..! Invalid Id");
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> findById(int id){
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		Optional<Merchant> dbMerchant=merchantDao.findById(id);
		if(dbMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setData(dbMerchant.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}
		throw new MerchantNotFoundException("Invalid Merchant Id");
	}
}
