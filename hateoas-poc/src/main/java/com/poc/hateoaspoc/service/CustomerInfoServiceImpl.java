package com.poc.hateoaspoc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.poc.hateoaspoc.model.Address;
import com.poc.hateoaspoc.model.Address.AddressBuilder;
import com.poc.hateoaspoc.model.Customer;

public class CustomerInfoServiceImpl implements CustomerInfoService {

	static Map<String, Customer> customerMap = null;
	static Map<String, Address> addressMap = null;

	static {
		String[] nameList = { "Bob", "Martin", "Sam", "Neil", "Roy" };
		Address[] addressList = { AddressBuilder.createAddressWithHouseNo("1 Boxborough"),
				AddressBuilder.createAddressWithHouseNo("2 Lowell"),
				AddressBuilder.createAddressWithHouseNo("3 Morris Plain"),
				AddressBuilder.createAddressWithHouseNo("4 Staten Island"),
				AddressBuilder.createAddressWithHouseNo("5 Randolph") };

		customerMap = Collections.unmodifiableMap(IntStream.range(1, 5).boxed().collect(
				Collectors.toMap(o -> String.valueOf(o), o -> new Customer(nameList[o - 1], String.valueOf(o)))));
		
		addressMap = Collections.unmodifiableMap(IntStream.range(1, 5).boxed()
				.collect(Collectors.toMap(o -> String.valueOf(o), o -> addressList[o - 1])));
	}

	@Override
	public Customer getCustomer(String customerID) {
		return customerMap.get(customerID);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return new ArrayList<Customer>(customerMap.values());
	}

	@Override
	public List<Address> getCustomerAddress(String customerId) {
		List<Address> address = new ArrayList<Address>();
		address.add(addressMap.get(customerId));
		return address;
	}

	public static void main(String[] args) {
		System.out.println(customerMap);
		System.out.println(new CustomerInfoServiceImpl().getAllCustomer());
	}

}
