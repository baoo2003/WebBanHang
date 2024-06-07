package shop.dto.request;

public class FilterProductDto {
	private String keyword;
	private Integer categoryId;
	private Integer priceGroupId;
	
	public FilterProductDto() {
		keyword = "";
		priceGroupId = 0;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPriceGroupId() {
		return priceGroupId;
	}

	public void setPriceGroupId(Integer priceGroupId) {
		this.priceGroupId = priceGroupId;
	}
}
