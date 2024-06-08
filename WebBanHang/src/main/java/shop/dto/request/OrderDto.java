package shop.dto.request;

import javax.validation.constraints.NotNull;

public class OrderDto {
	@NotNull(message = "This field is required")
	private Integer MADH;

	@NotNull(message = "This field is required")
	private Integer MAKH;

	@NotNull(message = "This field is required")
	private String HOTEN;

	@NotNull(message = "This field is required")
	private String SDT;

	private String LUUY_GIAO;

	private String LIDO_HUY;

	@NotNull(message = "This field is required")
	private String TG_HENGIAO;

	
	private Integer MANV;

	@NotNull(message = "This field is required")		
	private String TG_DAT;

	@NotNull(message = "This field is required")
	private Integer MATT_DH;

	public OrderDto() {
	}

	public OrderDto(Integer MADH, Integer MAKH, String HOTEN, String SDT, String LUUY_GIAO, String LIDO_HUY,
			String TG_HENGIAO, Integer MANV, String TG_DAT, Integer MATT_DH) {
		this.MADH = MADH;
		this.MAKH = MAKH;
		this.HOTEN = HOTEN;
		this.SDT = SDT;
		this.LIDO_HUY = LIDO_HUY;
		this.LUUY_GIAO = LUUY_GIAO;
		this.TG_HENGIAO = TG_HENGIAO;
		this.setMANV(MANV);
		this.TG_DAT = TG_DAT;
		this.MATT_DH = MATT_DH;
	}

	public Integer getMADH() {
		return MADH;
	}

	public void setMADH(Integer MADH) {
		this.MADH = MADH;
	}

	public Integer getMAKH() {
		return MAKH;
	}

	public void setMAKH(Integer MAKH) {
		this.MAKH = MAKH;
	}

	public String getTG_HENGIAO() {
		return TG_HENGIAO;
	}

	public void setTG_HENGIAO(String tG_HENGIAO) {
		TG_HENGIAO = tG_HENGIAO;
	}

	public String getLIDO_HUY() {
		return LIDO_HUY;
	}

	public void setLIDO_HUY(String lIDO_HUY) {
		LIDO_HUY = lIDO_HUY;
	}

	public String getLUUY_GIAO() {
		return LUUY_GIAO;
	}

	public void setLUUY_GIAO(String lUUY_GIAO) {
		LUUY_GIAO = lUUY_GIAO;
	}

	public String getTG_DAT() {
		return TG_DAT;
	}

	public void setTG_DAT(String tG_DAT) {
		TG_DAT = tG_DAT;
	}

	public Integer getMATT_DH() {
		return MATT_DH;
	}

	public void setMATT_DH(Integer mATT_DH) {
		MATT_DH = mATT_DH;
	}

	public String getHOTEN() {
		return HOTEN;
	}

	public void setHOTEN(String hOTEN) {
		HOTEN = hOTEN;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public Integer getMANV() {
		return MANV;
	}

	public void setMANV(Integer mANV) {
		MANV = mANV;
	}
}
