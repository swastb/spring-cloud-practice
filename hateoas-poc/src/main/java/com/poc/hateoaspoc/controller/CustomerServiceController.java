package com.poc.hateoaspoc.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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

	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public HttpEntity<Customer> getWelcomeMessage(@PathVariable(value = "customerId") String customerId) {

		Customer customer = new Customer(String.format(TEMPLATE, customerId), "");
		customer.add(linkTo(methodOn(CustomerServiceController.class).getWelcomeMessage(customerId)).withSelfRel());
		customer.add(linkTo(methodOn(CustomerServiceController.class).getAddressForCustomer(customerId)).withRel("allAddress"));
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/{customerId}/adddresses", method = RequestMethod.GET)
	public List<Address> getAddressForCustomer(@PathVariable String customerId) {
		return new CustomerInfoServiceImpl().getCustomerAddress(customerId);
	}
}
