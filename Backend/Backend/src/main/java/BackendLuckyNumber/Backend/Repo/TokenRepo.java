package BackendLuckyNumber.Backend.Repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import BackendLuckyNumber.Backend.Modal.PriceLottaryModal;
import BackendLuckyNumber.Backend.Modal.TokenModal;

@Transactional
public interface  TokenRepo  extends JpaRepository<TokenModal,Long>{

	@Query(value = " SELECT * FROM token where refresh_token = ?1  ",nativeQuery = true)
	TokenModal getTokenByRefreshToken(String refreshToken);
	
	@Query(value = " SELECT * FROM token where token = ?1  ",nativeQuery = true)
	TokenModal getToken(String token);
	
	@Modifying
	@Query(value = " INSERT INTO token  (refresh_token,token,user)  VALUES(?1,?2,?3)  ",nativeQuery = true)
	Integer insertToken(String refreshToken,String token,String idUser);
	
	
	@Modifying
	@Query(value = " DELETE from token   where id_token = ?1  ",nativeQuery = true)
	Integer deleteToken(String id_token);
}
