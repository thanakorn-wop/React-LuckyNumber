package BackendLuckyNumber.Backend.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;
import BackendLuckyNumber.Backend.Modal.MonthModal;
import BackendLuckyNumber.Backend.Modal.UserModal;


@Transactional
public interface MonthRepo extends JpaRepository<MonthModal,String>   {

	
	

	@Query(value = " SELECT p FROM month p WHERE p.id = ?1 and p.years = ?2 ")
	MonthModal findMonthOfUser(String id,String years);
	
	@Query(value = " UPDATE month  set jan = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth1(String money,String id,String years);
	@Query(value = " UPDATE month  set feb = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth2(String money,String id,String years);
	@Query(value = " UPDATE month  set mar = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth3(String money,String id,String years);
	@Query(value = " UPDATE month  set apr = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth4(String money,String id,String years);
	@Query(value = " UPDATE month  set may = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth5(String money,String id,String years);
	@Query(value = " UPDATE month  set jun = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth6(String money,String id,String years);
	@Query(value = " UPDATE month  set jul = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth7(String money,String id,String years);
	@Query(value = " UPDATE month  set aug = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth8(String money,String id,String years);
	@Query(value = " UPDATE month  set sep = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth9(String money,String id,String years);
	@Query(value = " UPDATE month  set oct = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth10(String money,String id,String years);
	@Query(value = " UPDATE month  set nov = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth11(String money,String id,String years);
	@Query(value = " UPDATE month  set dec = ?1 WHERE p.id = ?2 and p.years = ?3 ",nativeQuery = true)
	void updateMonth12(String money,String id,String years);
	

}
