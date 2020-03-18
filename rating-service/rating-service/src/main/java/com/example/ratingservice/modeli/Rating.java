package com.example.ratingservice.modeli;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Korisnik korisnik;
	
	@ManyToOne
	@JoinColumn
	private Strip strip;
	
	private int ocjena;
	private String komentar;
	
	public Rating(Korisnik korisnik, Strip strip, int ocjena, String komentar) {
		this.korisnik = korisnik;
		this.strip = strip;
		this.ocjena = ocjena;
		this.komentar = komentar;
	}
	
	protected Rating() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Strip getStrip() {
		return strip;
	}

	public void setStrip(Strip strip) {
		this.strip = strip;
	}

	public int getOcjena() {
		return ocjena;
	}

	public void setOcjena(int ocjena) {
		this.ocjena = ocjena;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	
		
}
