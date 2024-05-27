package shop.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAIKHOAN")
public class Account {
	@Id
	@Column(name = "MATK")
	private String username;
	@Column(name = "MATKHAU")
	private String password;
	@ManyToOne
	@JoinColumn(name = "MAVT")
	private Role role;
	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	private Customer customer;
	
	public Account() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
