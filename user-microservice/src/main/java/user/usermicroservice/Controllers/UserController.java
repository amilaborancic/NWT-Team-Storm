package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;
import user.usermicroservice.Servisi.UserServis;
import user.usermicroservice.exception.ApiRequestException;

import java.util.List;
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
    
    //novo
    @RequestMapping("/username/{id}")
    public String getUsername(@PathVariable Long id) {
    	return userServis.findUserById(id).get().getUserName();
    }
    

    @RequestMapping(method = RequestMethod.POST, value ="/sign-up")
    public void signUp(@RequestBody User user){
        if(user.getIme().equals("")) throw new ApiRequestException("Ime je obavezno!");
        if(user.getUserName().equals("")) throw new ApiRequestException("Username je obavezan!");
        if(user.getEmail().equals("")) throw new ApiRequestException("Email mora biti valjan!");
        if(userServis.postojiEmail(user.getEmail())) throw new ApiRequestException("User sa "+ user.getEmail()+ " već postoji!");
        if(user.getSifra().equals("")) throw new ApiRequestException("Sifra mora biti unesena!");
        userServis.addNewUser(user);
    }

    @RequestMapping("/userName/{name}")
    public Long getIdByUserName(@PathVariable String name){
        return userServis.findUserByName(name);
    }

    @RequestMapping("/svi/useri")
    @GetMapping
    public List<User> svi(){ return userServis.svi(); }


}
