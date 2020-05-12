package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import user.usermicroservice.DTO.KatalogDTO;
import user.usermicroservice.DTO.UserAuthDTO;
import user.usermicroservice.DTO.UserDTO;
import user.usermicroservice.DTO.UserRatingDTO;
import user.usermicroservice.Models.User;
import user.usermicroservice.RoleName;
import user.usermicroservice.Servisi.UserServis;
import user.usermicroservice.exception.ApiRequestException;
import user.usermicroservice.exception.ApiUnauthorizedException;
import user.usermicroservice.util.JwtUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServis userServis;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwt;
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id){
        return userServis.findUserById(id);
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
        if(userServis.postojiEmail(user.getEmail())) throw new ApiRequestException("User sa "+ user.getEmail()+ " već postoji!");
        if(user.getSifra().equals("")) throw new ApiRequestException("Sifra mora biti unesena!");
        //hash sifre
        user.setSifra(passwordEncoder.encode(user.getSifra()));
        //spasavanje u bazu
        userServis.addNewUser(user);
        //kreiranje kataloga
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
        return user.getId();
    }

    @PutMapping(value="/update-rating")
    public void updateUser(@RequestBody UserRatingDTO userRatingInfo, @RequestHeader Map<String,String> headers) {
        isUserPriviledged(userRatingInfo.getId(), headers, "Nemate privilegiju da updateujete ovaj rating!");
        userServis.updateUser(userRatingInfo);
    }

    //pomocne metode
    @GetMapping("/userName/{name}")
    public Long getIdByUserName(@PathVariable String name){
        return userServis.findUserByUserName(name).getId();
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

    private void isUserPriviledged(Long id, Map<String, String> headers, String errorMsg){
        String token = headers.get("authorization").substring(7);
        String username = jwt.extractUsername(token);
        User logovani_user = userServis.singleUser(username);
        Long id_logovanog = logovani_user.getId();
        String role = logovani_user.getRole().getRoleName().toString();
        if(!role.equals(RoleName.ROLE_ADMIN.toString()) && id != id_logovanog) throw new ApiUnauthorizedException(errorMsg);
    }
}
