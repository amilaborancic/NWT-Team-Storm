package comicbook.microsservice.comicbookmicroservice.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "zanr")
public class Zanr {
    @Id
    @GeneratedValue
    private UUID id;
    private String naziv;

    // VEZE

    @OneToMany
    @JoinColumn(name="id_zanr", referencedColumnName = "id")
    private List<Strip> stripovi;

    //getters and setters

    public UUID getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public List<Strip> getStripovi() {
        return stripovi;
    }
}
