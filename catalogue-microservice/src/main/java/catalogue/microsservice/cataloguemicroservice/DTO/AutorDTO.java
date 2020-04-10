package catalogue.microsservice.cataloguemicroservice.DTO;

public class AutorDTO {
    String ime;
    String prezime;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public AutorDTO(String ime, String prezime){
        this.ime = ime;
        this.prezime = prezime;
    }
    public AutorDTO(){}
}
