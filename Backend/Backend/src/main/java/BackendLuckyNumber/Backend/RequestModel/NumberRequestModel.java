package BackendLuckyNumber.Backend.RequestModel;

import lombok.Data;


public class NumberRequestModel {
	private String id;
	private String price;
	private String date;
	private String number;
	private String idline;
	private String phoneNumber;
	private String option;
	private String luckytime;
	
	
	 
	

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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getIdline() {
		return idline;
	}
	public void setIdline(String idline) {
		this.idline = idline;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	

}
