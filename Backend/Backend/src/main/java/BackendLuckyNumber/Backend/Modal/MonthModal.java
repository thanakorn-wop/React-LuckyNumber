package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "month")
public class MonthModal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_month")
	private String id_month;
	@Column(name = "jan")
	private String jan;
	@Column(name = "feb")
	private String feb;
	@Column(name = "mar")
	private String mar;
	@Column(name = "apr")
	private String apr;
	@Column(name = "may")
	private String may;
	@Column(name = "jun")
	private String jun;
	@Column(name = "jul")
	private String jul;
	@Column(name = "aug")
	private String aug;
	@Column(name = "sep")
	private String sep;
	@Column(name = "oct")
	private String oct;
	@Column(name = "nov")
	private String nov;
	@Column(name = "dec")
	private String dec;
	@Column(name = "id")
	private String id;
	@Column(name = "years")
	private String years;
	
	
	
	public String getId_month() {
		return id_month;
	}
	public void setId_month(String id_month) {
		this.id_month = id_month;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getJan() {
		return jan;
	}
	public void setJan(String jan) {
		this.jan = jan;
	}
	public String getFeb() {
		return feb;
	}
	public void setFeb(String feb) {
		this.feb = feb;
	}
	public String getMar() {
		return mar;
	}
	public void setMar(String mar) {
		this.mar = mar;
	}
	public String getApr() {
		return apr;
	}
	public void setApr(String apr) {
		this.apr = apr;
	}
	public String getMay() {
		return may;
	}
	public void setMay(String may) {
		this.may = may;
	}
	public String getJun() {
		return jun;
	}
	public void setJun(String jun) {
		this.jun = jun;
	}
	public String getJul() {
		return jul;
	}
	public void setJul(String jul) {
		this.jul = jul;
	}
	public String getAug() {
		return aug;
	}
	public void setAug(String aug) {
		this.aug = aug;
	}
	public String getSep() {
		return sep;
	}
	public void setSep(String sep) {
		this.sep = sep;
	}
	public String getOct() {
		return oct;
	}
	public void setOct(String oct) {
		this.oct = oct;
	}
	public String getNov() {
		return nov;
	}
	public void setNov(String nov) {
		this.nov = nov;
	}
	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}
	

	
	
}
