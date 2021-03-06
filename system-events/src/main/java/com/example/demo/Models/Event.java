package com.example.demo.Models;

import javax.persistence.*;
@Entity
@Table(name="event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String timestamp;
    private String nazivServisa;
    private Integer tipAkcije;
    private String nazivResursa;
    private Long idKorisnik;

    public Integer  getId() {
        return id;
    }

    public void setId(Integer  id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNazivServisa() {
        return nazivServisa;
    }

    public void setNazivServisa(String nazivServisa) {
        this.nazivServisa = nazivServisa;
    }

    public Integer getTipAkcije() {
        return tipAkcije;
    }

    public void setTipAkcije(Integer tipAkcije) {
        this.tipAkcije = tipAkcije;
    }

    public String getNazivResursa() {
        return nazivResursa;
    }

    public void setNazivResursa(String nazivResursa) {
        this.nazivResursa = nazivResursa;
    }

    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Event(String timestamp, Long idKorisnik, String nazivServisa, Integer tipAkcije, String nazivResursa) {
        this.timestamp = timestamp;
        this.nazivServisa = nazivServisa;
        this.tipAkcije = tipAkcije;
        this.nazivResursa = nazivResursa;
        this.idKorisnik = idKorisnik;
    }

    public Event() {}


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", idKorisnik=" + idKorisnik +
                ", nazivServisa='" + nazivServisa + '\'' +
                ", tipAkcije=" + tipAkcije +
                ", nazivResursa='" + nazivResursa + '\'' +
                '}';
    }
}