package BackendLuckyNumber.Backend.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.UserModal;

@Transactional 
public interface   UserRepo  extends JpaRepository<UserModal,String>     {

//	@Query("SELECT iduser FROM user WHERE iduser = ?1")
//	UserModal findidUser(String name);
	//List<UserModal> findByIdUser(String name);
	
	
	@Query(value = " SELECT p FROM user p  WHERE p.iduser = ?1  ")
	List<UserModal> findidUser(String iduser);
	
	@Query(value = " SELECT p FROM user p  WHERE p.id = ?1  ")
	UserModal findUserById(String id);
	
//	@Query(value = " SELECT p FROM user p  WHERE p.token = ?1  ")
//	UserModal findidUserByToken(String token);
//	
//	@Query(value = " SELECT p FROM user p  WHERE p.token = ?1  ")
//	UserModal findTokenUser(String token);
	
	@Query(value =  " SELECT * from user   ",nativeQuery = true)
	List<UserModal> getUser();
	
	@Modifying
	@Query(value = " UPDATE user p SET p.status = ?1, p.timelogin = ?2 WHERE p.iduser = ?3 and p.password = ?4  ")
	void updateStatusLoginUser(String status,String date,String user,String pass);
	
	@Modifying
	@Query(value = " UPDATE user p SET p.status = ?1, p.timelogout = ?2 WHERE p.iduser = ?3 ")
	void updateStatusLogoutUser(String status,String date,String iduser);
	
	
	@Modifying
	@Query(value = " UPDATE user p SET p.status =?1 Where  p.iduser = ?2 and p.nickname = ?3 ")
	void updateStatusLogin(String status,String iduser,String nickname);
	
}
