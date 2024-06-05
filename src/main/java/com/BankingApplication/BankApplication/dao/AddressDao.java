package com.BankingApplication.BankApplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BankingApplication.BankApplication.entity.Address;
import com.BankingApplication.BankApplication.repository.AddressRepository;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepository addressRepository;
	
	//save address
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
	
	//update address by id
	public String updateAddress(Address address) {
		Optional<Address> optional= addressRepository.findById(address.getAddressId());
		if (optional.isPresent()) {
			saveAddress(address);
			return "address is updated";
		} else {
            return "address not found";
		}
	}
	
	//get address by id
	public Optional<Address> getAdressById(int addressId) {
		return addressRepository.findById(null);
	}
	
	//get all the addresses
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}
	
	//delete address by id
	public String deleteAddressById(int addressId) {
		Optional<Address> optional= getAdressById(addressId);
		if (optional.isPresent()) {
			Address address=optional.get();
			addressRepository.delete(address);
			return "address is deleted";
		} else {
			return "address is not found";
		}
	}
	
}
