package BackendLuckyNumber.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import BackendLuckyNumber.Backend.Modal.MonthModal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;

public interface TransferLottaryRepo extends JpaRepository<TransferLottaryModal,String> {

}
