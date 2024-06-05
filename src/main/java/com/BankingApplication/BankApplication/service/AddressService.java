package com.BankingApplication.BankApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankingApplication.BankApplication.dao.AddressDao;
import com.BankingApplication.BankApplication.entity.Address;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	
	//save
	public Address saveAddress(Address address) {
		return addressDao.saveAddress(address);
	}
	
	//update
	public String updateAddress(Address address) {
		return addressDao.updateAddress(address);
	}
	
	//get address by id
	public Address getAddressById(int addressId) {
		Optional<Address> optional= addressDao.getAdressById(addressId);
		return optional.get();
	}
	
	//get all the addresses
	public List<Address> getAllAddresses() {
		return addressDao.getAllAddresses();
	}
	
	//delete address
	public String deleteAddressById(int addressId) {
		return addressDao.deleteAddressById(addressId);
	}
}
