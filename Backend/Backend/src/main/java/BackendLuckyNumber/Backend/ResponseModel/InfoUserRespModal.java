package BackendLuckyNumber.Backend.ResponseModel;

import javax.persistence.Column;

import BackendLuckyNumber.Backend.Header;

public class InfoUserRespModal {
	private Header header;
	private String idSeller;
	private String cost;
	private String totalPurchase;
	private String totalLostPrice;
	private String time;
	private String balance;
	private String id;
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public String getIdSeller() {
		return idSeller;
	}
	public void setIdSeller(String idSeller) {
		this.idSeller = idSeller;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(String totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public String getTotalLostPrice() {
		return totalLostPrice;
	}
	public void setTotalLostPrice(String totalLostPrice) {
		this.totalLostPrice = totalLostPrice;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
	

}
