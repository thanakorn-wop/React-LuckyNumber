package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "info_user")
public class InfoUserModal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_seller")
	private String idSeller;
	@Column(name = "cost")
	private String cost;
	@Column(name = "total_purchase")
	private String totalPurchase;
	@Column(name = "total_lost")
	private String totalLost;
	@Column(name = "people_win")
	private String peoplewin;
	@Column(name = "people_lost")
	private String peoplelost;
	@Column(name = "pay")
	private String pay;
	@Column(name = "notpay")
	private String notpay;
	@Column(name = "date")
	private String date;
	@Column(name = "balance")
	private String balance;
	@Column(name = "id")
	private String id;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "status_transfer")
	private String statusTransfer;
	
	

	


	public String getStatusTransfer() {
		return statusTransfer;
	}


	public void setStatusTransfer(String statusTransfer) {
		this.statusTransfer = statusTransfer;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
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


	public String getTotalLost() {
		return totalLost;
	}


	public void setTotalLost(String totalLost) {
		this.totalLost = totalLost;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
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





	

}
