package com.example.ratingservice.modeli;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="rating")
@Proxy(lazy = false)
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="korisnik_id")
	private Korisnik korisnik;

	@ManyToOne
	@JoinColumn(name="strip_id")
	private Strip strip;
	
	@Min(value =1)
	@Max(value =5)
	@NotNull
	private int ocjena;
	@NotNull
	private String komentar;
	
	public Rating(Korisnik korisnik, Strip strip, int ocjena, String komentar) {
		this.korisnik = korisnik;
		this.strip = strip;
		this.ocjena = ocjena;
		this.komentar = komentar;
	}
	
	public Rating() {}

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
