package prointern.ProinternApplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import prointern.ProinternApplication.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByToken(String token);
    
    @Query(value="select * from users where email=?1",nativeQuery=true)
	User findByEmail(String email);

}
