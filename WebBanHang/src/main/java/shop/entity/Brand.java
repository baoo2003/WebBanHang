package shop.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="THUONGHIEU")
public class Brand {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MATH")
	private Integer id;
	@Column(name="TENTH")
	private String name;
	@OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
	private Collection<Product> products;
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Brand(Integer id, String name) {
		super();
		id= this.id;
		name = this.name;
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
	
}
