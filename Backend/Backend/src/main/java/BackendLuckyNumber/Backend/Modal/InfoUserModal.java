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
	@Column(name = "total_lost_price")
	private String totalLostPrice;
	@Column(name = "time")
	private String time;
	@Column(name = "balance")
	private String balance;
	@Column(name = "id")
	private String id;

	public InfoUserModal() {

	}

	
	
	public InfoUserModal(String idSeller, String cost, String totalPurchase, String totalLostPrice, String time,
			String balance, String id) {
		super();
		this.idSeller = idSeller;
		this.cost = cost;
		this.totalPurchase = totalPurchase;
		this.totalLostPrice = totalLostPrice;
		this.time = time;
		this.balance = balance;
		this.id = id;
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

}
