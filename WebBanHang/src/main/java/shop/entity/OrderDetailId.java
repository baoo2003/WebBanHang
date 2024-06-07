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
	
	@MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MADH")
    private Order order;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MASP")
    private Product product;
    
	public OrderDetailId() {
		
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
