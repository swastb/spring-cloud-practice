package com.poc.hateoaspoc.model;

import org.springframework.hateoas.ResourceSupport;

public class Address extends ResourceSupport {

	private String houseNo;
	private String streetName;
	private int pinCode;

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "Address [houseNo=" + houseNo + ", streetName=" + streetName + ", pinCode=" + pinCode + "]";
	}

	public static class AddressBuilder {

		static Address address = new Address();

		public static Address createAddressWithHouseNo(String houseNo) {
			address.setHouseNo(houseNo);
			return address;
		}

		public static Address createAddressWithStreetName(String streetName) {
			address.setStreetName(streetName);
			return address;
		}

		public static Address createAddressWithPinCode(int pinCode) {
			address.setPinCode(pinCode);
			return address;
		}
	}

}
