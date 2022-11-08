package BackendLuckyNumber.Backend.Repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;

@Repository
public class  LoginRepo    {
	@Autowired
	JdbcTemplate JdbcTemplate;

		public List<UserModal> validateLogin(LoginReqModel userLogin)
	{
			List<UserModal> userModal = null;
			List<Object> para = new ArrayList<>();
			String sql = " SELLECT * FROM user WHERE iduser = ? AND password = ? ";
			para.add(userLogin.getIduser());
			para.add(userLogin.getPassword());
			userModal = JdbcTemplate.query(sql, FIND_MEMBER); 
			
	
			return userModal; 
	}
		
		public List<UserModal> updateStatusLogin(UserModal userLogin)
		{
			List<UserModal> userModal = null;
			List<Object> para = new ArrayList<>();
			try {
				
				String sql = " UPDATE user SET timelogin = ?, status = ? WHERE iduser = ? AND password = ? ";
				para.add(userLogin.getTimelogin());
				para.add(userLogin.getStatus());
				para.add(userLogin.getIduser());
				para.add(userLogin.getPassword());
				JdbcTemplate.update(sql, para);
				
			}catch(Exception e)
			{
				System.out.println("update status Login  = "+e);
			}
				
				
		
			return userModal; 
		}
		
		
	    private final RowMapper<UserModal> FIND_MEMBER = new RowMapper<UserModal>() {
	        public UserModal mapRow(ResultSet rs, int index) throws SQLException {
//	            newdata newdata = new newdata();

	        	UserModal data = new UserModal();
	        	data.setId(rs.getString("id"));
	        	data.setIduser(rs.getString("iduser"));
	        	data.setPassword(rs.getString("password"));
	        	data.setTimelogin(rs.getString("timelogin"));
	        	data.setTimelogout(rs.getString("timelogout"));
	        	data.setToken(rs.getString("token"));
	        	data.setStatus(rs.getString("status"));
	        	
//	            data.setPhone(phone);
//	          
//	            data.setPassword(rs.getString("password"));
	            return data;
	        }
	    };
//	
//	@Transactional
//	public UserModal save(UserModal loginreqmodal)
//	{
//	em.persist(loginreqmodal);
//	
//	return loginreqmodal;
//	}
	
}
