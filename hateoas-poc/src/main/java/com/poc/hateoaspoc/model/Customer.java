package com.poc.hateoaspoc.model;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class Customer extends ResourceSupport {

	private final String name;
	private final String customerId;
	private List<Address> addresses;
	
	
	public Customer(String name,String customerId) {
		this.name = name;
		this.customerId = customerId;
	}

	/*@JsonCreator
	public Customer(@JsonProperty("name") String name,@JsonProperty("name") String customerId) {
		this.name = name;
		this.customerId = customerId;
	}*/

	public String getName() {
		return name;
	}

	public String getCustomerId() {
		return customerId;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", customerId=" + customerId + ", addresses=" + addresses + "]";
	}
	

}