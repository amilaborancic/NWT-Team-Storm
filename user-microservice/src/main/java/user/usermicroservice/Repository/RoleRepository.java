package user.usermicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import user.usermicroservice.Models.Role;
import user.usermicroservice.Models.User;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
