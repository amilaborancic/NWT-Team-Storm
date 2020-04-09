package comicbook.microsservice.comicbookmicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StripRatingInfo {
	
    private Long id;
    private Integer ukupnoKomentara;
    private Double ukupniRating;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 public void setUkupniRating(Double ukupniRating) {
	    	this.ukupniRating=ukupniRating;
	    }
	    
	 public void setUkupnoKomentara(Integer ukupnoKomentara) {
	    	this.ukupnoKomentara=ukupnoKomentara;
	 }
	 public Double getUkupniRating() {
	        return ukupniRating;
	 }
	 public Integer getUkupnoKomentara() {
	        return ukupnoKomentara;
	 }
	 

}
