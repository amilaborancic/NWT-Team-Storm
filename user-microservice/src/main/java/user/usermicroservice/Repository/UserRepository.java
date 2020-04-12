package user.usermicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import user.usermicroservice.Models.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
   public Optional<User> findById(Long id);
   public User findByUserName(String userName);
   public boolean existsByEmail(String email);
   public boolean existsByUserName(String userName);
}
