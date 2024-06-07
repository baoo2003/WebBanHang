package shop.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SANPHAM")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "MASP")
	private Integer id;
	
	@Column(name = "TENSP")
	private String name;
	
	@Column(name = "SKU", nullable = true)
	private String sku;
	
	@ManyToOne
	@JoinColumn(name="MATH", nullable = true)
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="MADM")
	private Category category;
	
	@Column(name = "MOTA")
	private String describe;
	
	@Column(name = "XUATXU")
	private String origin;

	@Column(name = "TRANGTHAI")
	private Boolean status;
	
	@Column(name = "HINHANH")
	private String image;
	
	@Column(name = "DONVITINH")
	private String unit;
	
	@Column(name = "SOLUONG")
	private Integer quantity;
	
	@Column(name = "DONGIA")
	private Float price;
	
	@Column(name = "GIAMGIA")
	private Integer discount;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Collection<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Collection<Cart> carts;
	
	public Product() {
		//super();
		this.status = true;
	}
	
	public Product(Integer id, String name, String sku, Brand brand, 
			Category category, String describe, String origin, String image,
			Boolean status, String unit, Integer quantity, Float price, Integer discount) {
		this.id = id;
		this.name = name;
		this.sku=sku;
		this.brand=brand;
		this.category=category;		
		this.describe=describe;
		this.origin=origin;
		this.image=image;
		this.status = status;
		this.unit = unit;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}
	
	public Product(
		String name,
		Brand brand, 
		Category category,
		String describe,
		String origin,
		String image,
		String unit,
		Integer quantity,
		Float price,
		Integer discount
	) {
		this.name = name;
		this.brand = brand;
		this.category = category;		
		this.describe = describe;
		this.origin = origin;
		this.image = image;
		this.unit = unit;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Boolean isStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Collection<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Collection<Cart> carts) {
		this.carts = carts;
	}
}
