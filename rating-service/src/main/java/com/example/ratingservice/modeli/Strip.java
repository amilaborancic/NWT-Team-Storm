package com.example.ratingservice.modeli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="strip")
@Proxy(lazy = false)
public class Strip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer ukupnoKomentara;
	private Double ukupniRating;
	
	public Strip(Integer ukupnoKomentara, Double ukupniRating) {
		this.ukupnoKomentara = ukupnoKomentara;
		this.ukupniRating = ukupniRating;
	}
	
	public Strip() {}
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
