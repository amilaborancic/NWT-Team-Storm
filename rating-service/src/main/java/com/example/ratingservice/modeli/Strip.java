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
	
	private String naziv;
	@PositiveOrZero
	private int ukupno_komentara;
	@PositiveOrZero 
	private double ukupni_rating;
	
	public Strip(String naziv, int ukupno_komentara, float ukupni_rating) {
		this.naziv = naziv;
		this.ukupno_komentara = ukupno_komentara;
		this.ukupni_rating = ukupni_rating;
	}
	
	public Strip() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getUkupno_komentara() {
		return ukupno_komentara;
	}
	public void setUkupno_komentara(int ukupno_komentara) {
		this.ukupno_komentara = ukupno_komentara;
	}
	public double getUkupni_rating() {
		return ukupni_rating;
	}
	public void setUkupni_rating(double d) {
		this.ukupni_rating = d;
	}
	
}
