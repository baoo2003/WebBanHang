package shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import shop.entity.Customer;

public class OrderDto {
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String firstName;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String lastName;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String address;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String phoneNumber;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String email;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String shipping;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String payment;
	
	private String note;
	
	public OrderDto() {}
	
	public OrderDto(Customer c) {
		this.firstName=c.getFirstName();
		this.lastName=c.getLastName();
		this.address=c.getAddress();
		this.email=c.getEmail();
		this.phoneNumber=c.getPhoneNumber();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
