package catalogue.microsservice.cataloguemicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="katalog")
public class Katalog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String naziv;

    //foreign keys
    private Long id_korisnik;

    //veze
    @ManyToMany
    @JsonIgnore
    private List<Strip> stripovi;

    //getters
    public Long getId() { return id; }
    public String getNaziv() {
        return naziv;
    }
    public Long getId_korisnik() { return id_korisnik; }
    public List<Strip> getStripovi() { return stripovi; }
    public void setStripovi(List<Strip> stripovi) { this.stripovi = stripovi; }

    //konstruktori
    public Katalog(String naziv, Long id_korisnik){
        this.naziv = naziv;
        this.id_korisnik = id_korisnik;
        this.stripovi = new ArrayList<>();
    }
    protected Katalog(){}

}
