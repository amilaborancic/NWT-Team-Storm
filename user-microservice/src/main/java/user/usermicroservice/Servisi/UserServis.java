package user.usermicroservice.Servisi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;
import user.usermicroservice.exception.ApiRequestException;

import java.util.Optional;

@Service
public class UserServis {

    @Autowired
    UserRepository userRepository;

    public Optional<User> findUserById( Long id){

        Optional <User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new ApiRequestException("User sa id-jem " + id + " ne postoji!");

        return user;
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
