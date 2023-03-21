package BackendLuckyNumber.Backend.Repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.UserModal;

@Transactional 
public interface   LoginRepo  extends JpaRepository<UserModal,String>     {

//	@Query("SELECT iduser FROM user WHERE iduser = ?1")
//	UserModal findidUser(String name);
	//List<UserModal> findByIdUser(String name);
	
	
	@Query(value = " SELECT p FROM user p  WHERE p.iduser = ?1  ")
	UserModal findidUser(String iduser);
	
	@Query(value = " SELECT p FROM user p  WHERE p.token = ?1  ")
	UserModal findTokenUser(String token);
	 
	@Modifying
	@Query(value = " UPDATE user p SET p.status = ?1, p.timelogin = ?2, p.token = ?3 WHERE p.iduser = ?4 and p.password = ?5  ")
	void updateStatusLoginUser(String status,String date,String token,String user,String pass);
	
	@Modifying
	@Query(value = " UPDATE user p SET p.status = ?1, p.timelogout = ?2 WHERE p.token = ?3 ")
	void updateStatusLogoutUser(String status,String date,String token);
	
}
