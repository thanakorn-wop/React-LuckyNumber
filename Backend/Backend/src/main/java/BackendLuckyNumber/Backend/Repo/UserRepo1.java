//package BackendLuckyNumber.Backend.Repo;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import BackendLuckyNumber.Backend.Modal.TransferLottaryModal;
//import BackendLuckyNumber.Backend.Modal.UserModal;
//
//@Transactional 
//public interface UserRepo1 extends JpaRepository<UserModal,String> {
//	
//	@Query(value =  " SELECT u.id,u.iduser, u.role,u.status, u.nickname from user u where u.id = '1'  ")
//	UserModal getUser();
//
//}
