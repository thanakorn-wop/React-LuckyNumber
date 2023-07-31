package BackendLuckyNumber.Backend.Modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "price_lottary")
public class PriceLottaryModal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_price")
	private String idPrice;
	@Column(name = "three_bath")
	private String threeBath;
	@Column(name = "two_bath")
	private String twoBath;
	public String getIdPrice() {
		return idPrice;
	}
	public void setIdPrice(String idPrice) {
		this.idPrice = idPrice;
	}
	public String getThreeBath() {
		return threeBath;
	}
	public void setThreeBath(String threeBath) {
		this.threeBath = threeBath;
	}
	public String getTwoBath() {
		return twoBath;
	}
	public void setTwoBath(String twoBath) {
		this.twoBath = twoBath;
	}
	
	
	

}
