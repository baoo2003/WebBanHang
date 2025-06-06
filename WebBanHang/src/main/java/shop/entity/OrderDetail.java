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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("orderId")
    @JoinColumn(name = "MADH")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "MASP")
    private Product product;
	
	@Column(name="SOLUONG")
	private Integer quantity;
	
	@Column(name="GIA")
	private Float price;
	
	public OrderDetail() {
		
	}
	
	public OrderDetail(Order order, Product product, Integer quantity, Float price) {
        this.id = new OrderDetailId(order.getId(), product.getId());
        this.setOrder(order);
        this.setProduct(product);
        this.quantity = quantity;
        this.price=price;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
