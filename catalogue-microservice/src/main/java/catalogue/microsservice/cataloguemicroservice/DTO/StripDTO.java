package catalogue.microsservice.cataloguemicroservice.DTO;

import java.util.List;

public class StripDTO {
    String naziv;
    String opis;
    String slika;
    Double ukupniRating;
    Integer ukupnoKomentara;
    Integer izdanje;
    Long idIzdavac;
    Long idZanr;
    List<AutorDTO> autori;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Double getUkupniRating() {
        return ukupniRating;
    }

    public void setUkupniRating(Double ukupniRating) {
        this.ukupniRating = ukupniRating;
    }

    public Integer getUkupnoKomentara() {
        return ukupnoKomentara;
    }

    public void setUkupnoKomentara(Integer ukupnoKomentara) {
        this.ukupnoKomentara = ukupnoKomentara;
    }

    public Integer getIzdanje() {
        return izdanje;
    }

    public void setIzdanje(Integer izdanje) {
        this.izdanje = izdanje;
    }

    public Long getIdIzdavac() {
        return idIzdavac;
    }

    public void setIdIzdavac(Long idIzdavac) {
        this.idIzdavac = idIzdavac;
    }

    public Long getIdZanr() {
        return idZanr;
    }

    public void setIdZanr(Long idZanr) {
        this.idZanr = idZanr;
    }

    public List<AutorDTO> getAutori() {
        return autori;
    }

    public void setAutori(List<AutorDTO> autori) {
        this.autori = autori;
    }

    public StripDTO(String naziv, String opis, String slika, Double ukupniRating, Integer ukupnoKomentara, Integer izdanje, Long idIzdavac, Long idZanr, List<AutorDTO> autori){
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
    public StripDTO(){}
}
