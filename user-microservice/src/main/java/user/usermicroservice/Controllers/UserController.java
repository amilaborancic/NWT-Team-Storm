package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import user.usermicroservice.DTO.UserAuthDTO;
import user.usermicroservice.DTO.UserDTO;
import user.usermicroservice.DTO.UserRatingDTO;
import user.usermicroservice.Models.Role;
import user.usermicroservice.Models.User;
import user.usermicroservice.RabbitMQ.Producer;
import user.usermicroservice.Repository.RoleRepository;
import user.usermicroservice.RoleName;
import user.usermicroservice.Servisi.UserServis;
import user.usermicroservice.exception.ApiRequestException;
import user.usermicroservice.exception.ApiUnauthorizedException;
import user.usermicroservice.util.JwtUtil;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Producer producer;
    @Autowired
    UserServis userServis;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtil jwt;
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id, @RequestHeader Map<String, String> headers){
        isUserPriviledged(id, headers, "Nemate privilegiju za ovu akciju!");
        return userServis.findUserById(id);
    }

    @GetMapping("/name/{username}")
    public User getByUsername(@PathVariable String username, @RequestHeader Map<String, String> headers){
        isUserPriviledged(username, headers, "Nemate privilegiju za ovu akciju!");
        return userServis.singleUser(username);
    }

    @PostMapping(value = "/sign-in")
    public Long signIn(@RequestBody UserDTO userDTO){
        String userName = userDTO.getUserName();
        String sifra = userDTO.getSifra();
        //hash sifre
        sifra = passwordEncoder.encode(sifra);
        if(!userServis.postojiUserName(userName)) throw new ApiRequestException("Username nije ispravan!");
        if(!sifra.equals(userServis.findUserByUserName(userName).getSifra())) throw new ApiRequestException("Unesite ispravnu šifru!");
        return userServis.findUserByUserName(userName).getId();
    }

    @PostMapping(value ="/sign-up")
    public Long signUp(@RequestBody User user){
        if(user.getIme().equals("")) throw new ApiRequestException("Ime je obavezno!");
        if(user.getUserName().equals("")) throw new ApiRequestException("Username je obavezan!");
        if(user.getEmail().equals("")) throw new ApiRequestException("Email je obavezan!");
        if(userServis.postojiEmail(user.getEmail())) throw new ApiRequestException("User sa emailom "+ user.getEmail()+ " već postoji!");
        if(userServis.postojiUserName(user.getUserName())) throw new ApiRequestException("User sa usernameom " + user.getUserName() + " već postoji!");
        if(user.getSifra().equals("")) throw new ApiRequestException("Sifra mora biti unesena!");
        //rola
        List<Role> role =  roleRepository.findAll();
        role.forEach(rola -> {
            if(rola.getRoleName().toString().equals(RoleName.ROLE_USER.toString())) user.setRole(rola);
        });
        //hash sifre
        user.setSifra(passwordEncoder.encode(user.getSifra()));
        //spasavanje u bazu
        userServis.addNewUser(user);
        //rabbitmq
        User singleUser=userServis.singleUser(user.getUserName());
        producer.send(user.getId().toString());
        return user.getId();
    }

    @PutMapping(value="/update-rating")
    public void updateUser(@RequestBody UserRatingDTO userRatingInfo, @RequestHeader Map<String,String> headers) {
        isUserPriviledged(userRatingInfo.getId(), headers, "Nemate privilegiju da updateujete ovaj rating!");
        userServis.updateUser(userRatingInfo);
    }

    @GetMapping("/single/{name}")
    public UserAuthDTO getByUsername(@PathVariable String name){
        User pronadjeniUser = userServis.singleUser(name);
        if(pronadjeniUser != null){
            UserAuthDTO user = new UserAuthDTO(pronadjeniUser.getId(), pronadjeniUser.getUserName(), pronadjeniUser.getSifra(), pronadjeniUser.getRole().getRoleName().toString());
            return user;
        }
        return null;
    }

    @GetMapping("/single/id/{id}")
    public UserAuthDTO getById(@PathVariable Long id){
        System.out.println(id);
        User user = userServis.singlebyId(id);
        return new UserAuthDTO(user.getId(), user.getUserName(), user.getSifra(), user.getRole().getRoleName().toString());
    }

    //samo za testiranje kroz postman
    @GetMapping("/svi")
    public List<User> svi(){ return userServis.svi(); }

    @GetMapping("/username/{id}")
    public String getUsername(@PathVariable Long id) {
        return userServis.findUserById(id).get().getUserName();
    }

    @GetMapping(value="/count")
    public Long brojKorisnikaUBazi(){return userServis.brojKorisnikaUBazi();}

    @GetMapping(value="/naziv-role/{username}")
    public String getNazivRole(@PathVariable String username){return userServis.getNazivRole(username);}

    private String extractUsernameFromHeaders(Map<String, String> headers){
        String token = headers.get("authorization").substring(7);
        return jwt.extractUsername(token);
    }

    private void isUserPriviledged(Long id, Map<String, String> headers, String errorMsg){
        String username = extractUsernameFromHeaders(headers);
        User logovani_user = userServis.singleUser(username);
        Long id_logovanog = logovani_user.getId();
        String role = logovani_user.getRole().getRoleName().toString();
        if(!role.equals(RoleName.ROLE_ADMIN.toString()) && !id.equals(id_logovanog)) throw new ApiUnauthorizedException(errorMsg);
    }
    private void isUserPriviledged(String username, Map<String, String> headers, String errorMsg){
        String usernameFromToken = extractUsernameFromHeaders(headers);
        User logovani_user = userServis.singleUser(username);
        String role = logovani_user.getRole().getRoleName().toString();
        if(!role.equals(RoleName.ROLE_ADMIN.toString()) && !username.equals(usernameFromToken)) throw new ApiUnauthorizedException(errorMsg);
    }

    //for other services
    @GetMapping(value="/role")
    public User userRole(@RequestHeader Map<String, String> headers){
        String username = extractUsernameFromHeaders(headers);
        return userServis.singleUser(username);
    }
}
