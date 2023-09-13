package BackendLuckyNumber.Backend.Modal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Data
@Entity(name = "lottary")
public class LottaryModal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lottary")
	private String idLottary;
	@Column(name = "date")
	private String date;
	@Column(name = "three_top")
	private String threeTop;
	@Column(name = "three_dow")
	private String threedow;
	@Column(name = "two_top")
	private String twotop;
	@Column(name = "two_down")
	private String twodown;
	@Column(name = "biglucky")
	private String biglucky;
	@Column(name = "status_lottary")
	private String statusLottary;
	@Column(name = "user_update")
	private String userUpdate;
	@Column(name = "time")
	private String time;
	
	

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}
	public String getStatusLottary() {
		return statusLottary;
	}
	public void setStatusLottary(String statusLottary) {
		this.statusLottary = statusLottary;
	}
	public String getBiglucky() {
		return biglucky;
	}
	public void setBiglucky(String biglucky) {
		this.biglucky = biglucky;
	}
	public String getTwodown() {
		return twodown;
	}
	public void setTwodown(String twodown) {
		this.twodown = twodown;
	}
	public String getIdLottary() {
		return idLottary;
	}
	public void setIdLottary(String idLottary) {
		this.idLottary = idLottary;
	}

	public String getThreeTop() {
		return threeTop;
	}
	public void setThreeTop(String threeTop) {
		this.threeTop = threeTop;
	}
	public String getThreedow() {
		return threedow;
	}
	public void setThreedow(String threedow) {
		this.threedow = threedow;
	}
	public String getTwotop() {
		return twotop;
	}
	public void setTwotop(String twotop) {
		this.twotop = twotop;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	


}
