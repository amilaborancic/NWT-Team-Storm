package catalogue.microsservice.cataloguemicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="korisnik")
public class Korisnik {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    //relationships
    @OneToMany
    @JoinColumn(name="id_korisnik", referencedColumnName = "id")
    private List<Katalog> katalozi;

    //getters and setters
    public Long getId() {
        return id;
    }
    public List<Katalog> getKatalozi() {
        return katalozi;
    }
    public void setKatalozi(List<Katalog> katalozi) {
        this.katalozi = katalozi;
    }

    public Korisnik(){}
    public Korisnik(Long id){
        this.id = id;
    }
}
