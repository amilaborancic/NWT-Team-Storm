package user.usermicroservice.Models;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Proxy(lazy = false)
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_role")
    private Role role;

    @NotBlank(message = "Ime je obavezno!")
    private String ime;
    private  String prezime;
    @NotBlank(message = "Username je obavezan!")
    @Size(min = 1, max = 15, message = "Username mora imati između 1 i 15 karaktera")
    private String userName;
    @Email(message = "Email mora biti validan")
    private  String email;
    @NotBlank(message = "Sifra je obavezna!")
    @Size(min=60, max=60)
    private String sifra;
    private int broj_losih_reviewa;
    private int ukupno_reviewa;

    protected User(){}

    public User( Role role,String ime, String prezime, String userName, String email, String sifra, int broj_losih_reviewa, int ukupno_reviewa) {

        this.role=role;
        this.ime = ime;
        this.prezime = prezime;
        this.userName = userName;
        this.email = email;
        this.sifra = sifra;
        this.broj_losih_reviewa = broj_losih_reviewa;
        this.ukupno_reviewa = ukupno_reviewa;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getUserName(){return userName;}

    public String getEmail() {
        return email;
    }

    public String getSifra() {
        return sifra;
    }

    public int getBroj_losih_reviewa() {
        return broj_losih_reviewa;
    }

    public int getUkupno_reviewa() {
        return ukupno_reviewa;
    }

    public void setBroj_losih_reviewa(int broj_losih_reviewa) {
        this.broj_losih_reviewa=broj_losih_reviewa;

    }

    public void setUkupno_reviewa(int ukupno_reviewa ) {
        this.ukupno_reviewa=ukupno_reviewa;
    }

    public Role getRole(){
        return role;
    }
    public void setRole(Role role){
        this.role=role;
    }


    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
