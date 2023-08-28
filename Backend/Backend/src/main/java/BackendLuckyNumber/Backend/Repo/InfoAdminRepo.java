package BackendLuckyNumber.Backend.Repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.InfoAdminModal;
import BackendLuckyNumber.Backend.Modal.InfoUserModal;

@Transactional
public interface InfoAdminRepo extends JpaRepository<InfoAdminModal,String>  {

	@Query(value = " SELECT * FROM info_admin  WHERE iduser = ?1  and date = ?2 and nickname = ?3 ORDER by date DESC LIMIT 1",nativeQuery = true)
	InfoAdminModal findInfoAdmin(String iduser,String date,String nickname);
	
	
	@Query(value = " SELECT * FROM info_admin  WHERE iduser = ?1  and nickname = ?2 ORDER by date DESC LIMIT 1",nativeQuery = true)
	InfoAdminModal findLastDateInfoAdmin(String iduser,String nickname);
	
	@Modifying
	@Query(value ="INSERT INTO info_user (balance,total_purchase,total_lost,people_win,people_lost,pay,notpay,date) VALUES(?,?,?,?,?,?,?)",nativeQuery = true)
	int insertAdmin(String balance,String totalPurchase,String totalLost,String peopleWin, String peopleLost,String pay,String notpay,String date);

	@Modifying
	@Query(value ="UPDATE info_user set balance = ?1,total_purchase = ?2,total_lost = ?3,people_win = ?4,people_lost = ?5,pay = ?6,notpay = ?7) where date = ?8 and id =?9",nativeQuery = true)
	int updateInfoAdmin(String balance,String totalPurchase,String totalLost,String peopleWin, String peopleLost,String pay,String notpay,String date,String id);
}
