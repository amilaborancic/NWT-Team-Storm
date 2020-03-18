package com.example.ratingservice.repozitorij;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ratingservice.modeli.Korisnik;

@Repository
public interface KorisnikRepozitorij extends CrudRepository<Korisnik,Long> {

}
