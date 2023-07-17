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

	
	@Query(value = " SELECT p  FROM lottary p WHERE p.date = ?1 ")
	LottaryModal findByDate(String date);
	
	@Query(value = " SELECT * FROM lottary where date = ?1  ORDER BY date DESC LIMIT 1",nativeQuery = true)
	LottaryModal findDate(String date);
	
	@Query(value = " SELECT * FROM lottary   ORDER BY date DESC LIMIT 1",nativeQuery = true)
	LottaryModal findLastDate();
	
	@Query(value = " SELECT * FROM lottary  Where date = ?1",nativeQuery = true)
	LottaryModal findSelectDate(String date);
//	@Query(value = " INSERT into lottary (three_top,three_dow,two_top,two_down,date) VALUES(?1,?2,?3,?4,?5)  ")
//	LottaryModal SaveDate(String three_top,String_three_dow,String two_top, String_ two_down,D);
	@Query(value = " SELECT * from lottary  WHERE date <= ?1 order by date desc  LIMIT 2 ",nativeQuery = true)
	List<LottaryModal> findLastDate(String date);
	
	@Modifying
	@Query(value =  "UPDATE list_number SET status = 'Lucky' WHERE date_buy <= ?1 AND date_buy >=?2 AND (number = ?3 and option_purchase = 'Top' or number = ?4 and option_purchase = 'Below' or number =?5 and option_purchase ='Top' ) ")
	void updateStatusLucky(String date1,String date2,String ThreeTop, String TwoDown, String TwoTop);
	@Modifying
	@Query(value =  "UPDATE list_number SET status = 'Lucky' WHERE date_buy <= ?1 AND (number = ?2 and option_purchase = 'Top' or number = ?3 and option_purchase = 'Below' or number =?4 and option_purchase ='Top' ) ")
	void updateStatusLuckyOneonOne(String date1,String ThreeTop, String TwoDown, String TwoTop);
	
	
}
