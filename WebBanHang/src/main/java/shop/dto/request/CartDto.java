package shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import shop.entity.Customer;

public class CartDto {
	private String MAKH;
	
	private String MASP;
	
	private String SOLUONG;
	
	public CartDto() {
		
	}

	public String getMAKH() {
		return MAKH;
	}

	public void setMAKH(String mAKH) {
		MAKH = mAKH;
	}

	public String getMASP() {
		return MASP;
	}

	public void setMASP(String mASP) {
		MASP = mASP;
	}

	public String getSOLUONG() {
		return SOLUONG;
	}

	public void setSOLUONG(String sOLUONG) {
		SOLUONG = sOLUONG;
	}
	
}