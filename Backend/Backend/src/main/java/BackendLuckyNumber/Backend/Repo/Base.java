package BackendLuckyNumber.Backend.Repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import BackendLuckyNumber.Backend.Modal.UserModal;
import BackendLuckyNumber.Backend.RequestModel.LoginReqModel;
@Repository
public class Base {

//	@Autowired
//	JdbcTemplate JdbcTemplate;
//
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.JdbcTemplate = jdbcTemplate;
//	}
//
//	public List<UserModal> getAllEmployeesRowMapper() {
//		return JdbcTemplate.query("select * from user  ", FIND_MEMBER);
//		// JdbcTemplate.query(null, null, null)
//	}
//
////	public List<UserModal> validateLogin(LoginReqModel userLogin)
////	{
////			List<UserModal> userModal = new ArrayList<>();
////			List<Object> para = new ArrayList<>();
////			String sql = " SELLECT * FROM user WHERE iduser = ? AND password = ? ";
////			para.add(userLogin.getIduser());
////			para.add(userLogin.getPassword());
////			userModal = JdbcTemplate.query(sql, para.toArray(), FIND_MEMBER);
////		//	userModal = JdbcTemplate.query(sql,para.toArray(), FIND_MEMBER); 
////			return userModal;
////	}
//
//	public List<UserModal> validateLogin(LoginReqModel userLogin) {
//		List<UserModal> userModal = new ArrayList<>();
//		List<Object> para = new ArrayList<>();
//		String sql = " SELLECT * FROM user WHERE iduser = ? AND password = ? ";
//		para.add(userLogin.getIduser());
//		para.add(userLogin.getPassword());
//		userModal = JdbcTemplate.query(sql, new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement preparedStatement) throws SQLException {
//				preparedStatement.setString(1, "123");
//				preparedStatement.setString(2, "123");
//			}
//		}, FIND_MEMBER);
//		// userModal = JdbcTemplate.query(sql,para.toArray(), FIND_MEMBER);
//		return userModal;
//	}
//
//	private final RowMapper<UserModal> FIND_MEMBER = new RowMapper<UserModal>() {
//		public UserModal mapRow(ResultSet rs, int index) throws SQLException {
////	            newdata newdata = new newdata();
//
//			UserModal data = new UserModal();
//			data.setId(rs.getString("id"));
//			data.setIduser(rs.getString("iduser"));
//			data.setPassword(rs.getString("password"));
//			data.setTimelogin(rs.getString("timelogin"));
//			data.setTimelogout(rs.getString("timelogout"));
//			data.setToken(rs.getString("token"));
//			data.setStatus(rs.getString("status"));
//
////	            data.setPhone(phone);
////	          
////	            data.setPassword(rs.getString("password"));
//			return data;
//		}
//	};
}
