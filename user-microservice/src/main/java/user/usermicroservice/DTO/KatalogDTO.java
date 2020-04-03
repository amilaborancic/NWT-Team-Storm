package user.usermicroservice.DTO;

public class KatalogDTO {
    String naziv;
    Long idKorisnik;

    public KatalogDTO(String naziv, Long idKorisnik) {
        this.naziv = naziv;
        this.idKorisnik = idKorisnik;
    }

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

}
