package BackendLuckyNumber.Backend.RequestModel;

import javax.persistence.Column;

public class InfoUserReqModal {
	private String idSeller;
	private String cost;
	private String totalPurchase;
	private String totalLost;
	private String peoplewin;
	private String peoplelost;
	private String pay;
	private String notpay;
	private String date;
	private String balance;
	private String id;
	private String nickname;
	private String statusTransfer;
	private String timeTransfer;
	private String done;
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
	public String getTotalLost() {
		return totalLost;
	}
	public void setTotalLost(String totalLost) {
		this.totalLost = totalLost;
	}
	public String getPeoplewin() {
		return peoplewin;
	}
	public void setPeoplewin(String peoplewin) {
		this.peoplewin = peoplewin;
	}
	public String getPeoplelost() {
		return peoplelost;
	}
	public void setPeoplelost(String peoplelost) {
		this.peoplelost = peoplelost;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getNotpay() {
		return notpay;
	}
	public void setNotpay(String notpay) {
		this.notpay = notpay;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getStatusTransfer() {
		return statusTransfer;
	}
	public void setStatusTransfer(String statusTransfer) {
		this.statusTransfer = statusTransfer;
	}
	public String getTimeTransfer() {
		return timeTransfer;
	}
	public void setTimeTransfer(String timeTransfer) {
		this.timeTransfer = timeTransfer;
	}
	public String getDone() {
		return done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	
	

}
