package catalogue.microsservice.cataloguemicroservice.model;

import javax.persistence.*;

@Entity
@Table(name="katalog")
public class Katalog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String naziv;

    //foreign keys
    private Long id_korisnik;

    //getters
    public Long getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }

    //konstruktor
    public Katalog(String naziv, Long id_korisnik){
        this.naziv = naziv;
        this.id_korisnik = id_korisnik;
    }
    protected Katalog(){}

}
