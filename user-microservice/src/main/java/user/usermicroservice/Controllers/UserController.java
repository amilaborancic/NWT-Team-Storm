package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import user.usermicroservice.DTO.KatalogDTO;
import user.usermicroservice.DTO.UserDTO;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;
import user.usermicroservice.Servisi.UserServis;
import user.usermicroservice.exception.ApiRequestException;
import static org.junit.Assert.*;

import javax.validation.constraints.AssertFalse;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserServis userServis;

    @Autowired
    RestTemplate restTemplate;

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
    //novo
    @GetMapping(value="/user/count")
    public Long brojKorisnikaUBazi(){return userServis.brojKorisnikaUBazi();}

    @RequestMapping(method = RequestMethod.POST, value = "sign-in")
    public Long signIn(@RequestBody UserDTO userDTO){

        String userName = userDTO.getUserName();
        String sifra = userDTO.getSifra();

        if(! userServis.postojiUserName(userName)) throw new ApiRequestException("Username nije ispravan!");
        if(! userDTO.getSifra().equals(userServis.findUserByUserName(userName).getSifra())) throw new ApiRequestException("Unesite ispravnu šifru!");

        return userServis.findUserByUserName(userName).getId();
    }

    @RequestMapping(method = RequestMethod.POST, value ="/sign-up")
    public Long signUp(@RequestBody User user){
        if(user.getIme().equals("")) throw new ApiRequestException("Ime je obavezno!");
        if(user.getUserName().equals("")) throw new ApiRequestException("Username je obavezan!");
        if(user.getEmail().equals("")) throw new ApiRequestException("Email mora biti valjan!");
        if(userServis.postojiEmail(user.getEmail())) throw new ApiRequestException("User sa "+ user.getEmail()+ " već postoji!");
        if(user.getSifra().equals("")) throw new ApiRequestException("Sifra mora biti unesena!");

        userServis.addNewUser(user);
        KatalogDTO procitani = new KatalogDTO("Pročitani stripovi", user.getId());
        KatalogDTO zelimProcitati = new KatalogDTO("Želim pročitati", user.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //request body
        HttpEntity<KatalogDTO> entity1 = new HttpEntity<>(procitani, headers);
        ResponseEntity<Long> response1 = restTemplate.postForEntity("http://catalogue-service/katalog/novi", entity1, Long.class);

        HttpEntity<KatalogDTO> entity2 = new HttpEntity<>(zelimProcitati, headers);
        ResponseEntity<Long> response2 = restTemplate.postForEntity("http://catalogue-service/katalog/novi", entity2, Long.class);
        return response1.getBody();
    }


    @RequestMapping("/userName/{name}")
    public Long getIdByUserName(@PathVariable String name){
        return userServis.findUserByUserName(name).getId();
    }



    @RequestMapping("/svi/useri")
    @GetMapping
    public List<User> svi(){ return userServis.svi(); }


}
