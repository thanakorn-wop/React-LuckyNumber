package BackendLuckyNumber.Backend.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.LottaryModal;

@Transactional 
public interface LottaryRepo extends JpaRepository<LottaryModal,String>  {

	
	@Query(value = " SELECT p FROM lottary p WHERE p.date = ?1 ")
	LottaryModal findByDate(String id);
}
