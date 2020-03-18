package com.example.ratingservice.modeli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="korisnik")
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ime;
	private String prezime;

	private int broj_losih_reviewa;
	private int ukupno_reviewa;
	
	public Korisnik(String ime, String prezime,int broj_losih_reviewa, int ukupno_reviewa) {

		this.ime = ime;
		this.prezime = prezime;
		this.broj_losih_reviewa = broj_losih_reviewa;
		this.ukupno_reviewa = ukupno_reviewa;
	}
	protected Korisnik() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public int getBroj_losih_reviewa() {
		return broj_losih_reviewa;
	}

	public void setBroj_losih_reviewa(int broj_losih_reviewa) {
		this.broj_losih_reviewa = broj_losih_reviewa;
	}

	public int getUkupno_reviewa() {
		return ukupno_reviewa;
	}

	public void setUkupno_reviewa(int ukupno_reviewa) {
		this.ukupno_reviewa = ukupno_reviewa;
	}
	
	
}
