package BackendLuckyNumber.Backend.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.MonthModal;
import BackendLuckyNumber.Backend.Modal.UserModal;


@Transactional
public interface MonthRepo extends JpaRepository<MonthModal,String>   {

	
	

	@Query(value = " SELECT p FROM month p WHERE p.id = ?1 ")
	MonthModal findByIdSeller(String id);

}
