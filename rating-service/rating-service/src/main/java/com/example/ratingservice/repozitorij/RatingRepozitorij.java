package com.example.ratingservice.repozitorij;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ratingservice.modeli.Rating;

@Repository
public interface RatingRepozitorij extends CrudRepository<Rating,Long> {

}
