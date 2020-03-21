package comicbook.microsservice.comicbookmicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="autor")
public class Autor {
    @Id
    @GeneratedValue
    private Integer id;
    private String ime;
    private String prezime;

    //veze
    @ManyToMany(mappedBy = "autori")
    private List<Strip> stripovi;

    //getters and setters
    public Integer getId() {
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

    //konstruktor
    protected Autor(){}
    public Autor(String ime, String prezime){
        this.ime = ime;
        this.prezime = prezime;
    }

}
