package shop.dto.response;

public class SaleOverDto {
	private Integer month;
	
	private Integer year;
	
	private Double total;

	public SaleOverDto() {}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
