package BackendLuckyNumber.Backend.RequestModel;

public class LuckyNumberReq {
	
	private String date;
	private String threetop;
	private String threedown;
	private String twotop;
	private String twodown;
	private String biglucky;
	private String time;
	private Boolean statusLottary;
	
	
	
	
	

	public Boolean getStatusLottary() {
		return statusLottary;
	}
	public void setStatusLottary(Boolean statusLottary) {
		this.statusLottary = statusLottary;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBiglucky() {
		return biglucky;
	}
	public void setBiglucky(String biglucky) {
		this.biglucky = biglucky;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getThreetop() {
		return threetop;
	}
	public void setThreetop(String threetop) {
		this.threetop = threetop;
	}
	public String getThreedown() {
		return threedown;
	}
	public void setThreedown(String threedown) {
		this.threedown = threedown;
	}
	public String getTwotop() {
		return twotop;
	}
	public void setTwotop(String twotop) {
		this.twotop = twotop;
	}
	public String getTwodown() {
		return twodown;
	}
	public void setTwodown(String twodown) {
		this.twodown = twodown;
	}
	
	

}
