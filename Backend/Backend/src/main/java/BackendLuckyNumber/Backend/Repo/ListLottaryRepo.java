package BackendLuckyNumber.Backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import BackendLuckyNumber.Backend.Modal.InfoUserModal;

public interface ListLottaryRepo extends JpaRepository<InfoUserModal,String>  {

}
