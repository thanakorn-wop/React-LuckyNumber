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
	@Column(name = "option_purcase")
	private String optionpurchase;
	@Column(name = "status")
	private String status;
	@Column(name = "date_buy")
	private String datebuy;
	@Column(name = "time")
	private String time;
	@Column(name = "status_payment")
	private String statuspayment;
	@Column(name = "lucky_time")
	private String luckytime;
	@Column(name = "id")
	private String id;
	
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
	public String getOptionpurchase() {
		return optionpurchase;
	}
	public void setOptionpurchase(String optionpurchase) {
		this.optionpurchase = optionpurchase;
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
