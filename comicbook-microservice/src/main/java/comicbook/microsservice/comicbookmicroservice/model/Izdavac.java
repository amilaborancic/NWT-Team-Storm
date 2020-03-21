package comicbook.microsservice.comicbookmicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Izdavac {
    @Id
    @GeneratedValue
    private Integer id;
    private String naziv;

    //veze
    @OneToMany
    @JoinColumn(name="id_izdavac", referencedColumnName = "id")
    private List<Strip> stripovi;

    //getteri i setteri
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
    protected Izdavac(){}
    public Izdavac(String naziv){
        this.naziv = naziv;
    }
}
