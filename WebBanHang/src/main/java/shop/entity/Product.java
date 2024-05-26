package shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SANPHAM")
public class Product {
	@Id
	@Column(name = "MASP")
	private int id;
	@Column(name = "TENSP")
	private String name;
	@Column(name = "TRANGTHAI")
	private boolean status;
	
	public Product() {}
	
	public Product(int id, String name, boolean status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
