package com.poc.hateoaspoc.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.hateoaspoc.model.Address;
import com.poc.hateoaspoc.model.Customer;
import com.poc.hateoaspoc.service.CustomerInfoServiceImpl;

@RestController
@RequestMapping(value = "/customers")
public class CustomerServiceController {

	@Autowired
	CustomerInfoServiceImpl customerInfoServiceImpl;

	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping(value = "/", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public HttpEntity<Resources<Resource<Customer>>> findAll() {

		List<Customer> customerList = customerInfoServiceImpl.getAllCustomer();
		List<Resource<Customer>> resources = new ArrayList<Resource<Customer>>();
		for (Customer customer : customerList) {
			Resource<Customer> userResource = new Resource<Customer>(customer);
			resources.add(userResource);
			/*userResource.add(linkTo(methodOn(CustomerServiceController.class).findAll()).withSelfRel());*/
			userResource.add(
					linkTo(methodOn(CustomerServiceController.class).getCustomer(customer.getCustomerId()))
							.withRel("customer"));
		}
		return new ResponseEntity<Resources<Resource<Customer>>>(new Resources<Resource<Customer>>(resources),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public HttpEntity<Customer> getCustomer(@PathVariable(value = "customerId") String customerId) {

		Customer customer = new Customer(String.format(TEMPLATE, customerId), "");
		customer.add(linkTo(methodOn(CustomerServiceController.class).getCustomer(customerId)).withSelfRel());
		customer.add(linkTo(methodOn(CustomerServiceController.class).getAddressForCustomer(customerId))
				.withRel("allAddress"));
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/{customerId}/adddresses", method = RequestMethod.GET)
	public List<Address> getAddressForCustomer(@PathVariable String customerId) {
		return new CustomerInfoServiceImpl().getCustomerAddress(customerId);
	}
}
