package user.usermicroservice.Repository;

import org.springframework.data.repository.CrudRepository;
import user.usermicroservice.Models.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

}
