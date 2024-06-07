package shop.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class UpdateProductDto {
	private Integer id;
	private String name;
	private Integer brandId;
	private Integer categoryId;
	private String description;
	private String origin;
	private String imagePath;
	private MultipartFile image;
	private String unit;
	private Integer quantity;
	private Float price;
	private Integer discount;
	
	public UpdateProductDto() {}
	
	public UpdateProductDto(
		Integer id,
		String name,
		Integer brandId,
		Integer categoryId,
		String description,
		String origin,
		String imagePath,
		String unit,
		Integer quantity,
		Float price,
		Integer discount
	) {
		this.id = id;
		this.name = name;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.description = description;
		this.origin = origin;
		this.imagePath = imagePath;
		this.unit = unit;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
