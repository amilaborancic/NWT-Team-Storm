package com.example.ratingservice.modeli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="strip")
public class Strip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;
	private int ukupno_komentara;
	private float ukupni_rating;
	private String izdanje;
	
	public Strip(String naziv, int ukupno_komentara, float ukupni_rating,String izdanje) {
		this.naziv = naziv;
		this.ukupno_komentara = ukupno_komentara;
		this.ukupni_rating = ukupni_rating;
		this.izdanje = izdanje;
	}
	
	protected Strip() {}
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
	public float getUkupni_rating() {
		return ukupni_rating;
	}
	public void setUkupni_rating(float ukupni_rating) {
		this.ukupni_rating = ukupni_rating;
	}
	public String getIzdanje() {
		return izdanje;
	}
	public void setIzdanje(String izdanje) {
		this.izdanje = izdanje;
	}	
	
}
