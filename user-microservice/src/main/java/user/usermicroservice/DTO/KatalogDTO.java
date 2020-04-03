package user.usermicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KatalogDTO {
    String naziv;
    Long idKorisnik;

    public String getNaziv() {
        return naziv;
    }
    public Long getIdKorisnik() {
        return idKorisnik;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public KatalogDTO(){}

    public KatalogDTO(@JsonProperty("naziv") String naziv, @JsonProperty("idKorisnik") Long idKorisnik) {
        this.naziv = naziv;
        this.idKorisnik = idKorisnik;
    }
}
