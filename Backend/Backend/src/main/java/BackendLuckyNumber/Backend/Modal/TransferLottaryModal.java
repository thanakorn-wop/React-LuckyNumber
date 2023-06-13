package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "transfer_lottary")
public class TransferLottaryModal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transfer")
	private String id_transfer;
	@Column(name = "iduser")
	private String iduser;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "date")
	private String date;
	@Column(name = "time_transfer")
	private String timeTransfer;
	@Column(name = "total_purchase")
	private String totalPurchase;
	@Column(name = "total_lost")
	private String totalLost;
	@Column(name = "balance")
	private String balance;
	
	
	
	
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
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public String getId_transfer() {
		return id_transfer;
	}
	public void setId_transfer(String id_transfer) {
		this.id_transfer = id_transfer;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTimeTransfer() {
		return timeTransfer;
	}
	public void setTimeTransfer(String timeTransfer) {
		this.timeTransfer = timeTransfer;
	}
	
	
	
	
}
