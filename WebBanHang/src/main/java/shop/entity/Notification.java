package shop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "THONGBAO")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATB")
	private Integer id;
	
	@Column(name = "TG_TAO")
	@DateTimeFormat(pattern = "HH:mm dd-MM-yyyy")
	private Date createTime;
	
	@Column(name = "NOIDUNG")
	private String message;
	
	@Column(name = "DUONGDAN")
	private String url;
	
	@Column(name = "TRANGTHAI")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "MAKH")
	private Customer customer;

	
	public Notification() {
		this.createTime = new Date();
		this.status = false;
	}
	
	public Notification(Integer id, String message, String url, Date createTime, Boolean status) {
		this.id = id;
		this.message = message;
		this.url = url;
		this.createTime = createTime;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
