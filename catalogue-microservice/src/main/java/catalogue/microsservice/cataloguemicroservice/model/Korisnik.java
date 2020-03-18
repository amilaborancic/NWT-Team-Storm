package catalogue.microsservice.cataloguemicroservice.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="korisnik")
public class Korisnik {
    @GeneratedValue
    @Id
    private Integer id;

    //relationships
    @OneToMany
    @JoinColumn(name="id_korisnik", referencedColumnName = "id")
    private List<Katalog> katalozi;

    //getters and setters
    public Integer getId() {
        return id;
    }
    public List<Katalog> getKatalozi() {
        return katalozi;
    }
    public void setKatalozi(List<Katalog> katalozi) {
        this.katalozi = katalozi;
    }
}
