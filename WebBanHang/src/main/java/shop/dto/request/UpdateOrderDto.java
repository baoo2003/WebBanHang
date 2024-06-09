package shop.dto.request;

public class UpdateOrderDto {
	private Integer id;
	private String status;
	
	public UpdateOrderDto() {}
	
	public UpdateOrderDto(Integer id, String status) {
		this.id=id;
		this.status=status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
