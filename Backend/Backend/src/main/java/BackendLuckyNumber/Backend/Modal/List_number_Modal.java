package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "list_number")
public class List_number_Modal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_list")
	private String idlist;
	@Column(name = "number")
	private String number;
	@Column(name = "price")
	private String price;
	@Column(name = "all_price")
	private String all_price;
	@Column(name = "option_purchase")
	private String optinpurchase;
	@Column(name = "status")
	private String status;
	@Column(name = "date_buy")
	private String datebuy;
	@Column(name = "time")
	private String time;
	@Column(name = "status_payment")
	private String statuspayment;
	@Column(name = "luckytime")
	private String luckytime;
	@Column(name = "id")
	private String id;
	@Column(name = "transfer")
	private String transfer;
	@Column(name = "status_insert")
	private String statusInsert;
	
	
	
	
	
	
	
	
	
	

	public String getStatusInsert() {
		return statusInsert;
	}
	public void setStatusInsert(String statusInsert) {
		this.statusInsert = statusInsert;
	}
	public String getTransfer() {
		return transfer;
	}
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	
	public String getAll_price() {
		return all_price;
	}
	public void setAll_price(String all_price) {
		this.all_price = all_price;
	}
	public String getIdlist() {
		return idlist;
	}
	public void setIdlist(String idlist) {
		this.idlist = idlist;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getOptinpurchase() {
		return optinpurchase;
	}
	public void setOptinpurchase(String optinpurchase) {
		this.optinpurchase = optinpurchase;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDatebuy() {
		return datebuy;
	}
	public void setDatebuy(String datebuy) {
		this.datebuy = datebuy;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatuspayment() {
		return statuspayment;
	}
	public void setStatuspayment(String statuspayment) {
		this.statuspayment = statuspayment;
	}
	
	
	
	public String getLuckytime() {
		return luckytime;
	}
	public void setLuckytime(String luckytime) {
		this.luckytime = luckytime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	
}
