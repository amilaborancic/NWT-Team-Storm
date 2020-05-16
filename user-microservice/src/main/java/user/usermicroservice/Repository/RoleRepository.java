package user.usermicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.usermicroservice.Models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
