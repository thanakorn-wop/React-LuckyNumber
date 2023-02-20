package BackendLuckyNumber.Backend.Repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.List_number_Modal;

@Transactional 
public interface List_numberRepo extends JpaRepository<List_number_Modal,String> {
	
//	@Modifying
//	@Query(" INSERT into list_number (number,price,option_purchase,date_buy,time,status_payment,id) VALUES(?1,?2,?3,?4,?5,?6,?7) ")
//	void postInsertNumber(String number,String price,String option, String date, String time,String status,String id);

	@Query(" SELECT u from list_number u Where u.id = ?1 ")
	List<List_number_Modal> findItem(String id);
	
	@Modifying
	@Query(" UPDATE list_number set status_payment = ?1 Where id = ?2 and id_list = ?3 ")
	void postUpdateStatusPaymentRepo(String statuspayment, String id , String idList);
}




