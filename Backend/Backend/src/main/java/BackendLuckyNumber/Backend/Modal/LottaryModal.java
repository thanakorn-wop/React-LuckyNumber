package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity(name = "lottary")
public class LottaryModal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idLottary;
	private String time;
	private String threeTop;
	private String threedow;
	private String twotop;
	private String twodow;
	public String getIdLottary() {
		return idLottary;
	}
	public void setIdLottary(String idLottary) {
		this.idLottary = idLottary;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getTwodow() {
		return twodow;
	}
	public void setTwodow(String twodow) {
		this.twodow = twodow;
	}
	

}
