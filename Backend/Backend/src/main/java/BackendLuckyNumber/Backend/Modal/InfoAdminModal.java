package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "info_admin")
public class InfoAdminModal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_admin")
	private String idAdmin;
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
	@Column(name = "iduser")
	private String idUser;
	@Column(name = "nickname")
	private String nickName;
	
	
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
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
	
	
	
}
