package shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NHANVIEN")
public class Staff {
	@Id
	@Column(name = "MANV")
	@GeneratedValue
	private Integer id;
	@Column(name = "TEN")
	private String firstName;
	@Column(name = "HO")
	private String lastName;
	@Column(name = "GIOITINH")
	private Boolean gender;
	@Column(name = "DIACHI", nullable = true)
	private String address;
	@Column(name = "SDT")
	private String phoneNumber;
	@Column(name = "EMAIL", nullable = true)
	private String email;
	@Column(name = "TRANGTHAI")
	@GeneratedValue
	private Boolean status;
	@OneToOne
	@JoinColumn(name = "MATK")
	private Account account;
	
	public Staff() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
