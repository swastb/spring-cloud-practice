package com.poc.hateoaspoc.service;

import java.util.List;

import com.poc.hateoaspoc.model.Address;
import com.poc.hateoaspoc.model.Customer;

public interface CustomerInfoService {

	public Customer getCustomer(String customerID);
	public List<Customer> getAllCustomer();	
	public List<Address> getCustomerAddress(String customerAddress);
	
}
