package comicbook.microsservice.comicbookmicroservice.model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name="strip")
@Entity
public class Strip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Naziv stripa je obavezan!")
    private String naziv;
    @Column(length = 3000) 
    private String opis;
    @NotBlank(message = "Strip mora imati naslovnicu!")
    private String slika;
    @Column(name="ukupni_rating")
    private Double ukupniRating;
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
    public Long getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public String getOpis() {
        return opis;
    }
    public Double getUkupniRating() {
        return ukupniRating;
    }
    public Integer getUkupnoKomentara() {
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
    public void setAutori(List<Autor> autori) { this.autori = autori; }
    public Long getIdZanr() { return idZanr; }
    public Long getIdIzdavac() { return idIzdavac; }

    //konstruktor
    public Strip() { }
    public Strip(String naziv, String opis, String slika, Double ukupniRating, Integer ukupnoKomentara, Integer izdanje, Long idIzdavac, Long idZanr, List<Autor> autori){
        this.naziv = naziv;
        this.opis = opis;
        this.slika = slika;
        this.ukupniRating = ukupniRating;
        this.ukupnoKomentara = ukupnoKomentara;
        this.izdanje = izdanje;
        this.idIzdavac = idIzdavac;
        this.idZanr = idZanr;
        this.autori = autori;
    }
    public void setUkupniRating(Double ukupniRating) {
    	this.ukupniRating=ukupniRating;
    }
    
    public void setUkupnoKomentara(Integer ukupnoKomentara) {
    	this.ukupnoKomentara=ukupnoKomentara;
    }
}
