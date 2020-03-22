package comicbook.microsservice.comicbookmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Ime je obavezno!")
    private String ime;
    @NotBlank(message = "Prezime je obavezno!")
    private String prezime;

    //veze
    @ManyToMany(mappedBy = "autori")
    @JsonIgnore
    private List<Strip> stripovi;

    //getters and setters
    public Long getId() {
        return id;
    }
    public String getIme() {
        return ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public List<Strip> getStripovi() {
        return stripovi;
    }
    public void setStripovi(List<Strip> stripovi) { this.stripovi = stripovi; }

    //konstruktor
    protected Autor(){}
    public Autor(String ime, String prezime){
        this.ime = ime;
        this.prezime = prezime;
    }

}
