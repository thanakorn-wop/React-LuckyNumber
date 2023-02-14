package BackendLuckyNumber.Backend.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.LottaryModal;

@Transactional 
public interface LottaryRepo extends JpaRepository<LottaryModal,String>  {

	
	@Query(value = " SELECT p FROM lottary p WHERE p.date = ?1 ")
	LottaryModal findByDate(String date);
//	@Query(value = " INSERT into lottary (three_top,three_dow,two_top,two_down,date) VALUES(?1,?2,?3,?4,?5)  ")
//	LottaryModal SaveDate(String three_top,String_three_dow,String two_top, String_ two_down,D);
	
	
}
