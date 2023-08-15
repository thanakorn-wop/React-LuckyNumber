package BackendLuckyNumber.Backend.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.UserModal;


@Transactional
public interface InfoUserRepo extends JpaRepository<InfoUserModal,String>   {

	
	
	@Query(value = " SELECT * FROM info_user  ",nativeQuery = true)
	List<InfoUserModal> getAllUser();
	
	@Query(value = " SELECT p FROM info_user p WHERE p.id = ?1 and p.date = ?2   ")
	InfoUserModal findInfoUser(String id,String date);
	@Query(value = " SELECT * FROM info_user  WHERE id = ?1  and nickname = ?2 ORDER by date DESC LIMIT 1",nativeQuery = true)
	InfoUserModal findLastInfoUserReportPage(String id,String nickname);
	
	@Query(value = " SELECT * FROM info_user  WHERE id = ?1  and date = ?2 and nickname = ?3 ORDER by date DESC LIMIT 1",nativeQuery = true)
	InfoUserModal finInfoUserReportPage(String id,String date,String nickname);
	
	@Query(value = " SELECT * FROM info_user  WHERE id = ?1  and date = ?2 and nickname = ?3 and status_transfer = 'N' ",nativeQuery = true)
	List<InfoUserModal> finInfoUserTransfer(String id,String date,String nickname);
	
	@Modifying
	@Query(value = " UPDATE  info_user set total_purchase = ?1, people_lost = ?2 where nickname = ?3  and status_transfer = 'N' and date = ?4 and id = ?5 ")
	void updateInfoUser(String total,String peoplelost, String nickname, String date,String id);
	
	@Modifying
	@Query(value = " UPDATE  info_user set people_lost = ?1,people_win =?2,total_lost = ?3, notpay = ?4,balance = ?5 where  date = ?6 and id = ?7  ")
	void updateInfoPeopleLostWinTotalLost(String peoplelost,String peopleWin,String totalLost,String notpay,String conBalance, String date,String id);
	
	@Modifying
	@Query(value = " UPDATE  info_user set status_transfer = 'Y', balance = ?1, time_transfer = ?5 where id =?2  and nickname = ?3    and date = ?4  ")
	void updateTotalPurchase(String sum,String iduser,String nickname, String date, String timeTransfer);
}
