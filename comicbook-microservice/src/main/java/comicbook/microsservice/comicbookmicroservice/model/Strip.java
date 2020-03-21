package comicbook.microsservice.comicbookmicroservice.model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.List;

@Table(name="strip")
@Entity
public class Strip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private String opis;
    private String slika;
    @Column(name="ukupni_rating")
    private float ukupniRating;
    @Column(name="ukupno_komentara")
    private Integer ukupnoKomentara;
    @Nullable
    private Integer izdanje;

    //relationships
    @ManyToMany
    private List<Autor> autori;

    //foreign keys
    @Column(name="id_zanr")
    private Long idZanr;
    @Column(name="id_izdavac")
    private Long idIzdavac;

    //getters and setters
    public Long getId_zanr() {
        return idZanr;
    }
    public Long getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public String getOpis() {
        return opis;
    }
    public float getUkupni_rating() {
        return ukupniRating;
    }
    public Integer getUkupno_komentara() {
        return ukupnoKomentara;
    }
    public Integer getIzdanje() {
        return izdanje;
    }
    public String getSlika() {
        return slika;
    }
    public List<Autor> getAutori() {
        return autori;
    }
    public Long getId_izdavac() {
        return idIzdavac;
    }
    public void setAutori(List<Autor> autori) { this.autori = autori; }

    //konstruktor
    protected Strip() { }
    public Strip(String naziv, String opis, String slika, float ukupniRating, Integer ukupnoKomentara, Integer izdanje, Long idIzdavac, Long idZanr){
        this.naziv = naziv;
        this.opis = opis;
        this.slika = slika;
        this.ukupniRating = ukupniRating;
        this.ukupnoKomentara = ukupnoKomentara;
        this.izdanje = izdanje;
        this.idIzdavac = idIzdavac;
        this.idZanr = idZanr;
    }
}
