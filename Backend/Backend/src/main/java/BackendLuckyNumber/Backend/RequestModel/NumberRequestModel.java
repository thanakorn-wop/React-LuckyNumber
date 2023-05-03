package BackendLuckyNumber.Backend.RequestModel;

import lombok.Data;


public class NumberRequestModel {
	private String idList;
	private String price;
	private String date;
	private String number;
	private String option;
	private String luckytime;
	private String allPrice;
	private String sequence;
	
	
	
	
	 
	 
	

	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getLuckytime() {
		return luckytime;
	}
	public void setLuckytime(String luckytime) {
		this.luckytime = luckytime;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAllPrice() {
		return allPrice;
	}
	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}

	
	
	

}
