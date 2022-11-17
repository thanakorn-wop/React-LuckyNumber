package BackendLuckyNumber.Backend.Repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.UserModal;


@Transactional
public interface InfoUserRepo extends JpaRepository<InfoUserModal,String>   {

	
	

	@Query(value = " SELECT p FROM info_user p  WHERE p.id = ?1  ")
	InfoUserModal findByIdSeller(String id);

}
