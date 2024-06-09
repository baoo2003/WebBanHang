package shop.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DONHANG")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MADH")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="MAKH")
	private Customer customer;
	
	@Column(name="HOTEN")
	private String fullname;
	
	@Column(name="SDT")
	private String phoneNumber;
	
	@Column(name="DIACHI")
	private String address;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="TG_HENGIAO")
	private Date deliveryTime;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="TG_DAT")
	private Date orderTime;
	
	@ManyToOne
	@JoinColumn(name="MANV")
	private Staff staff;
	
	@Column(name="TRANGTHAI")
	private String status;
	
	@Column(name="LYDO_HUY")
	private String cancelReason;
	
	@Column(name="LUUY_GIAO")
	private String deliveryNote;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Bill bill;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public String getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}
}
