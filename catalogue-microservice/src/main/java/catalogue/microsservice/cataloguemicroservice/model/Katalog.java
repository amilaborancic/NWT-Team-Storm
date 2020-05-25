package catalogue.microsservice.cataloguemicroservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
@Table(name="katalog")
public class Katalog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    private String naziv;

    //foreign keys
    @Column(name="id_korisnik")
    private Long idKorisnik;

    //veze
    @ManyToMany
    @JsonIgnore
    private List<Strip> stripovi;

    //getters
    public Long getId() { return id; }
    public String getNaziv() {
        return naziv;
    }
    public Long getIdKorisnik() { return idKorisnik; }
    public List<Strip> getStripovi() { return stripovi; }
    public void setStripovi(List<Strip> stripovi) { this.stripovi = stripovi; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public void setIdKorisnik(Long idKorisnik) { this.idKorisnik = idKorisnik; }

    //konstruktori
    public Katalog(@JsonProperty("naziv") String naziv, @JsonProperty("idKorisnik") Long idKorisnik){
        this.naziv = naziv;
        this.idKorisnik = idKorisnik;
        this.stripovi = new ArrayList<>();
    }
    public Katalog(){
    }

}
