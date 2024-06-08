package shop.dto.response;

public class YearBreakDto {
	private Integer currentYear;
	
	private Double currentYearTotal;
	
	private Double previousYearToTal;

	private String percentageChange;
	
	public YearBreakDto() {}
	


	public Integer getCurrentYear() {
		return currentYear;
	}


	public void setCurrentYear(Integer currentYear) {
		this.currentYear = currentYear;
	}


	

	public Double getCurrentYearTotal() {
		return currentYearTotal;
	}


	public void setCurrentYearTotal(Double currentYearTotal) {
		this.currentYearTotal = currentYearTotal;
	}


	public Double getPreviousYearToTal() {
		return previousYearToTal;
	}


	public void setPreviousYearToTal(Double previousYearToTal) {
		this.previousYearToTal = previousYearToTal;
	}



	public String getPercentageChange() {
		return percentageChange;
	}



	public void setPercentageChange(String percentageChange) {
		this.percentageChange = percentageChange;
	}
	
	
}
