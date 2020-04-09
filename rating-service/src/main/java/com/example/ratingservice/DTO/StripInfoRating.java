package com.example.ratingservice.DTO;

public class StripInfoRating {

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
		this.ukupniRating = ukupniRating;
	}

	public StripInfoRating(Long id, Integer ukupnoKomentara, Double ukupniRating) {
		this.id = id;
		this.ukupnoKomentara = ukupnoKomentara;
		this.ukupniRating = ukupniRating;
	}

	public void setUkupnoKomentara(Integer ukupnoKomentara) {
		this.ukupnoKomentara = ukupnoKomentara;
	}

	public Double getUkupniRating() {
		return ukupniRating;
	}

	public Integer getUkupnoKomentara() {
		return ukupnoKomentara;
	}

}
