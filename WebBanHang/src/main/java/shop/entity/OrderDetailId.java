package shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Embeddable
public class OrderDetailId implements Serializable{
	@Column(name="MADH")
	private Integer orderId;
	
	@Column(name="MASP")
	private Integer productId;
    
	public OrderDetailId() {
		
	}
	public OrderDetailId(Integer orderId, Integer productId) {
		this.orderId=orderId;
		this.productId=productId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}
