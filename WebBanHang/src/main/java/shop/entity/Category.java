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
@Table(name = "DANHMUC")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MADM")
	private Integer id;
	@Column(name="TENDM")
	private String name;
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<Product> products;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Integer id, String name) {
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
