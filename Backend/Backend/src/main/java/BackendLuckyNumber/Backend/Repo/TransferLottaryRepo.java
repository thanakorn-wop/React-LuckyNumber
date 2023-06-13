package BackendLuckyNumber.Backend.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.MonthModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
import BackendLuckyNumber.Backend.Modal.UserModal;

public interface TransferLottaryRepo extends JpaRepository<TransferLottaryModal,String> {
	
	@Query(value =  " SELECT * from transfer_lottary   ",nativeQuery = true)
	List<TransferLottaryModal> getAllUserTransfer();

}
