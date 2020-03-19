package user.usermicroservice.Models;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    private UUID id;


    private String ime;
    private  String prezime;
    private  String email;
    private String sifra;
    private int broj_losih_reviewa;
    private int ukupno_reviewa;

    public User(UUID id, String ime, String prezime, String email, String sifra, int broj_losih_reviewa, int ukupno_reviewa) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.sifra = sifra;
        this.broj_losih_reviewa = broj_losih_reviewa;
        this.ukupno_reviewa = ukupno_reviewa;
    }

    public UUID getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

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
}
