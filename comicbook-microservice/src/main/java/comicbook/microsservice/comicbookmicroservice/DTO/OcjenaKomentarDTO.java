package comicbook.microsservice.comicbookmicroservice.DTO;

public class OcjenaKomentarDTO {
    Integer ocjena;
    String komentar;

    public OcjenaKomentarDTO(Integer ocjena, String komentar) {
        this.ocjena = ocjena;
        this.komentar= komentar;
    }

    public Integer getOcjena(){
        return this.ocjena;
    }
    public String getKomentar(){
        return this.komentar;
    }

}
