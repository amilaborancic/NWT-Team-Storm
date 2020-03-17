package comicbook.microsservice.comicbookmicroservice.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Izdavac {
    @Id
    @GeneratedValue
    private UUID id;
    private String naziv;

    @OneToMany
    @JoinColumn(name="id_izdavac", referencedColumnName = "id")
    private List<Strip> stripovi;

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
