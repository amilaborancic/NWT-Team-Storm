package comicbook.microsservice.comicbookmicroservice.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="autor")
public class Autor {
    @Id
    @GeneratedValue
    private UUID id;
    private String ime;

    //relationships
    @ManyToMany(mappedBy = "autori")
    private List<Strip> stripovi;

    //getters and setters

    public UUID getId() {
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

    private String prezime;

}
