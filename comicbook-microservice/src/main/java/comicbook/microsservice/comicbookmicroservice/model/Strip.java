package comicbook.microsservice.comicbookmicroservice.model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table(name="strip")
@Entity
public class Strip {
    @Id
    @GeneratedValue
    private UUID id;
    private String naziv;
    private String opis;
    private String slika;
    private int ukupni_rating;
    private int ukupno_komentara;
    @Nullable
    private int izdanje;

    //relationships
    @ManyToMany
    private List<Autor> autori;

    //foreign keys
    private UUID id_zanr;
    private UUID id_izdavac;

    //getters and setters

    public UUID getId_zanr() {
        return id_zanr;
    }

    public UUID getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public int getUkupni_rating() {
        return ukupni_rating;
    }

    public int getUkupno_komentara() {
        return ukupno_komentara;
    }

    public int getIzdanje() {
        return izdanje;
    }
    public String getSlika() {
        return slika;
    }

    public List<Autor> getAutori() {
        return autori;
    }
    public UUID getId_izdavac() {
        return id_izdavac;
    }

}
