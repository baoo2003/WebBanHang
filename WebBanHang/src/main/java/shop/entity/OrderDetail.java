package shop.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="CT_DONHANG")
public class OrderDetail {
	@EmbeddedId
	private OrderDetailId id;
	
	@MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MADH")
    private Order order;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MASP")
    private Product product;
	
	@Column(name="SOLUONG")
	private Integer quantity;
	
	@Column(name="DONGIA")
	private Float price;
	
	public OrderDetail() {
		
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
	public OrderDetailId getId() {
		return id;
	}
	public void setId(OrderDetailId id) {
		this.id = id;
	}
	
}
