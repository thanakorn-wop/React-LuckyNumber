package BackendLuckyNumber.Backend.Repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
import org.springframework.data.jpa.repository.Query;

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
	@Query(value = " UPDATE user p SET p.status = ?1, p.timelogin = ?2 WHERE p.token = ?3 ")
	void updateStatusLoginUser(String status,String date,String token);
	
	@Modifying
	@Query(value = " UPDATE user p SET p.status = ?1, p.timelogout = ?2 WHERE p.token = ?3 ")
	void updateStatusLogoutUser(String status,String date,String token);
	
}
