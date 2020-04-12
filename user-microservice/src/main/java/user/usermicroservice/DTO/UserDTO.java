package user.usermicroservice.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotBlank (message = "Username je obavezan!")
    @Size(min = 1, max = 15, message = "Username mora imati između 1 i 15 karaktera")
    private String UserName;

    @NotBlank(message = "Sifra je obavezna!")
    private String sifra;

    protected UserDTO(){}

    public UserDTO(String userName, String sifra){
        this.UserName = userName;
        this.sifra = sifra;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
