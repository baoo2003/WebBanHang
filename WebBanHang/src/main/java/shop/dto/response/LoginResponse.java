package shop.dto.response;

public class LoginResponse {
	private Integer userId;
	private String roleId;
	
	public LoginResponse() {}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
