package user.usermicroservice.Controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import user.usermicroservice.DTO.UserDTO;
import user.usermicroservice.DTO.UserRatingDTO;
import user.usermicroservice.Models.Role;
import user.usermicroservice.Models.User;
import user.usermicroservice.RoleName;
import user.usermicroservice.Servisi.UserServis;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserServis userServis;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getUser() throws Exception{
        String url = "/user/{id}";
        //sve okej
        mockMvc.perform(get(url, 1)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        //id usera ne postoji
        mockMvc.perform(get(url, 123))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("User sa id-jem 123 ne postoji!"));
    }

    @Test
    public void signIn() throws Exception {
        String url = "/user/sign-in";
        UserDTO user = new UserDTO("Amila", "sifraamila");
        UserDTO pogresanUsername = new UserDTO("alksnd", "sifraamila");
        UserDTO pogresnaSifra = new UserDTO("Mahira", "asmkfa");
        //sve okej
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
        //pogresan username
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(pogresanUsername)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Username nije ispravan!"));

        //pogresna sifra
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(pogresnaSifra)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Unesite ispravnu šifru!"));
    }

    //komunicira sa katalog servisom
    @Test
    public void signUp() throws Exception {

        Role role=new Role(RoleName.USER);
        User user = new User(role,"Neko", "Nekic", "Nekoc","neko@gmail.com",
                "neka",2, 5);
        User faliIme = new User(role,"", "klm", "Test","test@gmail.com",
                "neka",2, 5);
        User faliUsername = new User(role,"dqwpakml", "Nasdmaslc", "","aksks@gmail.com",
                "neka",2, 5);
        User emailPostoji = new User(role,"asdlm", "Neaslmdkic", "Ahmed","neko@gmail.com",
                "neka",2, 5);
        User faliSifra = new User(role,"asamdsl", "sldml", "alsmlx","aslmdlx@gmail.com",
                "",2, 5);
        User faliEmail = new User(role,"scld", "lsdmc", "laksmc","",
                "neka",2, 5);
        //sve okej
        mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))).andExpect(status().isOk());
        //fali ime
        mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(faliIme)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Ime je obavezno!"));
        //fali username
        mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(faliUsername)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Username je obavezan!"));
        //username vec postoji
        mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(emailPostoji)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("User sa neko@gmail.com već postoji!"));
        //fali sifra
        mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(faliSifra)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Sifra mora biti unesena!"));
        //fali email
        mockMvc.perform(post("/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(faliEmail)))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Email je obavezan!"));
    }

    @Test
    public void updateUser() throws Exception {
        UserRatingDTO urinfo = new UserRatingDTO(Long.valueOf(1), 0, 0);
        mockMvc.perform(
                put("/user/update-rating").contentType(MediaType.APPLICATION_JSON).content(asJsonString(urinfo)))
                .andExpect(status().isOk());
    }

    @Test
    public void getIdByUserName() throws Exception{
        String url = "/user/userName/{name}";
        //sve okej
        mockMvc.perform(get(url, "Amila")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .equals(1);
        //ne postoji user s tim usernameom
        mockMvc.perform(get(url, "ajkjn")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("Korisnik sa username-om ajkjn ne postoji!"));
    }


}
