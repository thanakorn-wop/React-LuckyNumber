package BackendLuckyNumber.Backend.Repo;

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

	@Query(" SELECT u from list_number u Where u.id = ?1 and u.transfer = 'N' ORDER BY id_list desc ")
	List<List_number_Modal> findItem(String id );
	
	@Modifying
	@Query(" UPDATE list_number set status_payment = ?1 Where id = ?2 and id_list = ?3 ")
	void postUpdateStatusPaymentRepo(String statuspayment, String id , String idList);
	
	@Modifying
	@Query(" DELETE from list_number where id = ?1 and id_list = ?2 ")
	void postDeleteDataRepo(String id,String idlist);
	
	@Modifying
	@Query(" UPDATE list_number set transfer ='Y'  Where id = ?1 and luckytime = ?2 ")
	void postSendLottaryRepo(String id , String luckytime);
	
	@Query(" SELECT u from transfer_lottary u Where u.iduser = ?1 and u.nickname = ?2 and date = ?3 ")
	List<TransferLottaryModal> getDataTransferLottaryRepo(String iduser,String nickname , String date );
	
	@Query(" SELECT u from list_number u Where u.id = ?1 and u.transfer = 'N' and luckytime = ?2 ")
	List<List_number_Modal> getItemListNumber(String id,String luckytime );
	
//	@Query(" Insert into transfer_lottary (iduser,nickname,date,time_transfer) VALUES(?1,?2,?3,CURRENT_TIMESTAMP) ")
//	void InsertDataTransfer(String iduser,String nickname,String date);
	
//	@Modifying
//	@Query(" UPDATE list_number set transfer ='Y'  Where id = ?1 and luckytime = ?2 ")
//	void postTransferDataLottaryRepo(String id, String luckytime);
	
}




