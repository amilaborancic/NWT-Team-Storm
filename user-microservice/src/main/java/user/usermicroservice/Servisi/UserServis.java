package user.usermicroservice.Servisi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserServis {

    @Autowired
    UserRepository userRepository;

    public Optional<User> findUserById( Long id){
        return userRepository.findById(id);
    }


    public void addNewUser(User user){
        userRepository.save(user);
    }

    public Long findUserByName(String name){
        return userRepository.findByUserName(name).getId();
    }

    public boolean postojiEmail(String email){
        return userRepository.existsByEmail(email);
    }




}
