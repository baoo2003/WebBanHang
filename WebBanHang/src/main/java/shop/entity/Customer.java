package shop.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KHACHHANG")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "MAKH")
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
	private Boolean status;
	@OneToOne
	@JoinColumn(name = "MATK")
	private Account account;
	
	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY)
	private Collection<Order> orders;
	
	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY)
	private Collection<Cart> carts;
	
	public Customer() {
		this.status = true;
	}

	public int getId() {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean isStatus() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public Collection<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Collection<Cart> carts) {
		this.carts = carts;
	}
}
