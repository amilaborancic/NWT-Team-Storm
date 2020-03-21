package comicbook.microsservice.comicbookmicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zanr")
public class Zanr {
    @Id
    @GeneratedValue
    private Integer id;
    private String naziv;

    //veze
    @OneToMany
    @JoinColumn(name="id_zanr", referencedColumnName = "id")
    private List<Strip> stripovi;

    //getters and setters
    public Integer getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public List<Strip> getStripovi() {
        return stripovi;
    }

    //konstruktor
    protected Zanr(){}
    public Zanr(String naziv) {
        this.naziv = naziv;
    }
}
