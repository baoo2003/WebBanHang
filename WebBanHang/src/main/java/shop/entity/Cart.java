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
@Table(name = "GIOHANG")
public class Cart {
	@EmbeddedId
	private CartId cartId;
	
	@Column(name="SOLUONG")
	private Integer quantity;
	
	@MapsId("customerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKH")
    private Customer customer;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAMH")
    private Product product;
	
	public Cart() {
		this.cartId = new CartId(); // Ensure the ID is never null
        this.quantity = 0; // Default quantity
	}
	
	public Cart(CartId cartId, Integer quantity) {
		this.cartId = cartId; // Ensure the ID is never null
        this.quantity = quantity; // Default quantity
	}
	
	public CartId getCartId() {
		return cartId;
	}

	public void setCartId(CartId cartId) {
		this.cartId = cartId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
