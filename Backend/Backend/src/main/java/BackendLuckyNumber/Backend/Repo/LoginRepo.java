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
	//   public List<UserModal> findAllSortedByName();
//	
//		public List<UserModal> validateLogin(LoginReqModel userLogin)
//	{
//			List<UserModal> userModal = new ArrayList<>();
//			List<Object> para = new ArrayList<>();
//			String sql = " SELLECT * FROM user WHERE iduser = ? AND password = ? ";
//			para.add(userLogin.getIduser());
//			para.add(userLogin.getPassword());
//			userModal = JdbcTemplate.query(sql,para.toArray(), FIND_MEMBER); 
//			return userModal;
//	}
//		public List<UserModal> test()
//		{
//			List<UserModal> userModal = new ArrayList<>();
//			String sql = " SELLECT * FROM user  ";
//			userModal = JdbcTemplate.query(sql,FIND_MEMBER);
//			return  userModal;
//		}
//		
//		public List<UserModal> updateStatusLogin(UserModal userLogin)
//		{
//			List<UserModal> userModal = null;
//			List<Object> para = new ArrayList<>();
//			try {
//				
//				String sql = " UPDATE user SET timelogin = ?, status = ? WHERE iduser = ? AND password = ? ";
//				para.add(userLogin.getTimelogin());
//				para.add(userLogin.getStatus());
//				para.add(userLogin.getIduser());
//				para.add(userLogin.getPassword());
//				JdbcTemplate.update(sql, para);
//				
//			}catch(Exception e)
//			{
//				System.out.println("update status Login  = "+e);
//			}
//				
//				
//		
//			return userModal; 
//		}
//		
//		
//	    private final RowMapper<UserModal> FIND_MEMBER = new RowMapper<UserModal>() {
//	        public UserModal mapRow(ResultSet rs, int index) throws SQLException {
////	            newdata newdata = new newdata();
//
//	        	UserModal data = new UserModal();
//	        	data.setId(rs.getString("id"));
//	        	data.setIduser(rs.getString("iduser"));
//	        	data.setPassword(rs.getString("password"));
//	        	data.setTimelogin(rs.getString("timelogin"));
//	        	data.setTimelogout(rs.getString("timelogout"));
//	        	data.setToken(rs.getString("token"));
//	        	data.setStatus(rs.getString("status"));
//	        	
////	            data.setPhone(phone);
////	          
////	            data.setPassword(rs.getString("password"));
//	            return data;
//	        }
//	    };
//	
//	@Transactional
//	public UserModal save(UserModal loginreqmodal)
//	{
//	em.persist(loginreqmodal);
//	
//	return loginreqmodal;
//	}
	
}
