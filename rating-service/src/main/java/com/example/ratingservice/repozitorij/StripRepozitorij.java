package com.example.ratingservice.repozitorij;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ratingservice.modeli.Strip;

@Repository
public interface StripRepozitorij extends CrudRepository<Strip,Long> {

}
