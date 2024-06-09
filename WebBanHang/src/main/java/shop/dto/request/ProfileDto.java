package shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import shop.entity.Customer;

public class ProfileDto {
	
	private String username;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String firstName;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String lastName;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private Boolean gender;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String address;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String phoneNumber;
	
	@NotBlank(message="Must not be left blank!")
	@NotEmpty(message="Must not be empty!")
	private String email;
	
	public ProfileDto() {}
	
	public ProfileDto(Customer c) {
		this.username=c.getAccount().getUsername();
		this.firstName=c.getFirstName();
		this.lastName=c.getLastName();
		this.gender=c.getGender();
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

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
