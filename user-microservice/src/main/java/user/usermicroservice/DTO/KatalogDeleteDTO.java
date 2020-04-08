package user.usermicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KatalogDeleteDTO {

    Long idKorisnik;
    Long idKatalog;

    public Long getIdKorisnik() { return idKorisnik; }
    public Long getIdKatalog() { return idKatalog; }
    public void setIdKorisnik(Long idKorisnik) { this.idKorisnik = idKorisnik; }
    public void setIdKatalog(Long idKatalog) { this.idKatalog = idKatalog; }

    public KatalogDeleteDTO(@JsonProperty("idKorisnik") Long idKorisnik, @JsonProperty("idKatalog") Long idKatalog) {
        this.idKorisnik = idKorisnik;
        this.idKatalog = idKatalog;
    }
}
