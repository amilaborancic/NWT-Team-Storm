package comicbook.microsservice.comicbookmicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.amqp.core.Message;

import java.io.Serializable;

public class RatingDTO implements Serializable{
    String username;
    Integer ocjena;
    String komentar;
    Double ukupniRating;
    Integer ukupnoKomentara;

    public RatingDTO(){

    }
    public RatingDTO(String username, Integer ocjena, String komentar, Double ukupniRating, Integer ukupnoKomentara){
        this.username=username;
        this.ocjena=ocjena;
        this.komentar=komentar;
        this.ukupniRating=ukupniRating;
        this.ukupnoKomentara=ukupnoKomentara;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
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

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setUkupnoKomentara(Integer ukupnoKomentara) {
        this.ukupnoKomentara = ukupnoKomentara;
    }

}
