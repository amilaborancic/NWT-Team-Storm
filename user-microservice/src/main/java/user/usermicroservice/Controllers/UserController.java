package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;
import user.usermicroservice.Servisi.UserServis;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserServis userServis;


    @RequestMapping("/user/{id}")
    public Optional <User> getUser(@PathVariable Long id){
        return userServis.findUserById(id);
    }

    @RequestMapping("/ahmo")
    public String sayHi(){
        return "HIIII";
    }

    @RequestMapping(method = RequestMethod.POST, value ="/sign-up")
    public void signUp(@RequestBody User user){
        userServis.addNewUser(user);

    }

    @RequestMapping("/userName/{name}")
    public Long getIdByUserName(@PathVariable String name){
        return userServis.findUserByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sign-in")
        public Long signIn(@RequestBody String userName){
            return userServis.findUserByName(userName);

    }



}
