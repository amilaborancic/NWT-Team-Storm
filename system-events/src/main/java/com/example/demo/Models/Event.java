package com.example.demo.Models;

import com.google.protobuf.Timestamp;
import javax.persistence.*;

@Table(name="event")
@Entity
public class Event {
    @GeneratedValue
    @Id
    private Long id;
    private String timestamp;
    private String nazivServisa;
    private Integer tipAkcije;
    private String nazivResursa;
    private Long idKorisnik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Event(Timestamp timestamp, Long idKorisnik, String nazivServisa, Integer tipAkcije, String nazivResursa) {
        this.id = id;
        this.timestamp = timestamp.toString();
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
                ", userId=" + idKorisnik +
                ", serviceName='" + nazivServisa + '\'' +
                ", actionType=" + tipAkcije +
                ", resourceName='" + nazivResursa + '\'' +
                '}';
    }
}