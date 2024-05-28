package shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartId implements Serializable{
	@Column(name = "MAKH")
	private Integer customerId;
	
	@Column(name = "MAMH")
	private Integer productId;
	
	public CartId() {}
	
	public CartId(Integer customerId, Integer productId) {
		this.customerId = customerId;
		this.productId = productId;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
}
