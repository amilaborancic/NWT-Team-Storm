package comicbook.microsservice.comicbookmicroservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Izdavac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Naziv izdavaca je obavezan!")
    private String naziv;

    //veze
    @OneToMany
    @JoinColumn(name="id_izdavac", referencedColumnName = "id")
    private List<Strip> stripovi;

    //getteri i setteri
    public Long getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public List<Strip> getStripovi() {
        return stripovi;
    }

    //konstruktor
    protected Izdavac(){}
    public Izdavac(String naziv){
        this.naziv = naziv;
    }
}
