package catalogue.microsservice.cataloguemicroservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="katalog")
public class Katalog {
    @GeneratedValue
    @Id
    private Integer id;
    private String naziv;

    //foreign keys
    private Integer id_korisnik;

    //getters
    public Integer getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }

    //konstruktor
    public Katalog(String naziv, Integer id_korisnik){
        this.naziv = naziv;
        this.id_korisnik = id_korisnik;
    }
    protected Katalog(){}

}
