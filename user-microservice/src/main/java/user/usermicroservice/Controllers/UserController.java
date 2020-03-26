package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/{id}")
    public Optional <User> getUser(@PathVariable Long id){
        return userRepository.findById(id);
    }
    @RequestMapping("/ahmo")
    public String sayHi(){
        return "HIIII";
    }

    @RequestMapping(method = RequestMethod.POST, value ="/sign-up")
    public void signUp(@RequestBody User user){
        userRepository.save(user);
    }

}
