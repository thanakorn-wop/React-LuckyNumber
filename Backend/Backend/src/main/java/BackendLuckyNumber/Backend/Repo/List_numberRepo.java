package BackendLuckyNumber.Backend.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.List_number_Modal;
import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;

@Transactional 
public interface List_numberRepo extends JpaRepository<List_number_Modal,String> {
	
//	@Modifying
//	@Query(" INSERT into list_number (number,price,option_purchase,date_buy,time,status_payment,id) VALUES(?1,?2,?3,?4,?5,?6,?7) ")
//	void postInsertNumber(String number,String price,String option, String date, String time,String status,String id);

	@Query(value = " SELECT * from list_number  Where id = ?1 and transfer = 'N'  ORDER BY id_list desc ",nativeQuery = true)
	List<List_number_Modal> findItem(String id );
	
	@Modifying
	@Query(" UPDATE list_number set status_payment = ?1 Where id = ?2 and id_list = ?3 and luckytime = ?4 ")
	int postUpdateStatusPaymentRepo(String statuspayment, String id , String idList, String luckyDate);
	
	@Query(" SELECT u from list_number u Where u.id = ?1 and u.transfer = 'N' and u.luckytime = ?2 ORDER BY id_list desc ")
	List<List_number_Modal> findItembyDate(String id,String date);
	
	@Query(" SELECT u from list_number u Where u.id = ?1 and u.transfer = 'N'and status_validate = 'N' and u.luckytime = ?2 ORDER BY id_list desc ")
	List<List_number_Modal> findItembyStatusTransfer(String id,String date);
	
//	@Query(" SELECT u from list_number u Where u.id_list = ?1 and u.transfer = 'N' and u.luckytime = ?2 ")
//	List_number_Modal findEachItem(String idList,String date);
	
	@Query(" SELECT u from list_number u Where u.id = ?1 and u.luckytime = ?2  ORDER BY id_list desc ")
	List<List_number_Modal> getlistitem(String id,String luckytime );
	
	@Modifying
	@Query(" DELETE from list_number where id = ?1 and id_list = ?2 ")
	int postDeleteDataRepo(String id,String idlist);
	
	@Query(value =" SELECT * from list_number  Where id = ?1 and id_list = ?2 ",nativeQuery = true)
	List_number_Modal getitem(String id,String idList );
//	
	@Modifying
	@Query(" UPDATE list_number set transfer ='Y'  Where id = ?1 and luckytime = ?2 ")
	void postSendLottaryRepo(String id , String luckytime);
	
	@Modifying
	@Query(" UPDATE list_number set reward = ?1  Where id_list = ?2 and luckytime = ?3 ")
	void updateReward(String reward,String idList , String luckytime);
	
	@Modifying
	@Query(value = " update list_number set status = 'lucky' where id_list in (SELECT id_list FROM list_number   where id = ?1 and id_list in (?2) and luckytime = ?3) ",nativeQuery = true)
	void changeStatusLucky(String id ,ArrayList<String> idList,String luckydate);
	
	@Modifying
	@Query(value = " update list_number set status_validate = 'Y' where id_list in (SELECT id_list FROM list_number   where id = ?1 and id_list in (?2) and luckytime = ?3) ",nativeQuery = true)
	void changeStatusValidate(String id ,ArrayList<String> idValidate,String luckydate);
	
	@Query(" SELECT u from transfer_lottary u Where u.iduser = ?1 and u.nickname = ?2 and date = ?3 ")
	List<TransferLottaryModal> getDataTransferLottaryRepo(String iduser,String nickname , String date );
	
	@Query(" SELECT u from list_number u Where u.id = ?1 and u.transfer = 'N' and luckytime = ?2 ")
	List<List_number_Modal> getItemListNumber(String id,String luckytime );
	
	
	
	@Modifying
	@Query(" UPDATE list_number set status_insert =?1 Where number = ?2 and price = ?3 and all_price = ?4 and option_purchase = ?5 and date_buy = ?6 "
			+ " and  status_payment = ?7 and id = ?8 and status = ?9 and luckytime = ?10 and transfer = ?11" )
	void updateStatusInsert(String messagesuccess,String number,String price,String allprice,String option, String date
			,String statuspayment, String id, String status, String luckydate,String transferstatus);
	
//	@Query(" Insert into transfer_lottary (iduser,nickname,date,time_transfer) VALUES(?1,?2,?3,CURRENT_TIMESTAMP) ")
//	void InsertDataTransfer(String iduser,String nickname,String date);
	
//	@Modifying
//	@Query(" UPDATE list_number set transfer ='Y'  Where id = ?1 and luckytime = ?2 ")
//	void postTransferDataLottaryRepo(String id, String luckytime);
	
}




