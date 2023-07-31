package BackendLuckyNumber.Backend.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.LottaryModal;
import BackendLuckyNumber.Backend.Modal.PriceLottaryModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;

@Transactional
public interface PriceLottaryRepo extends JpaRepository<PriceLottaryModal,String> {

	
	@Query(value =  " SELECT * from price_lottary   ",nativeQuery = true)
	PriceLottaryModal getPrice();

}
