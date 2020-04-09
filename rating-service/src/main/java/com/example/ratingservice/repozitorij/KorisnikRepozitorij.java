package com.example.ratingservice.repozitorij;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;

@Repository
public interface KorisnikRepozitorij extends JpaRepository<User,Long> {
	
	List<User> findAll();
	Optional<User> findById(Long id);
	void deleteById(Long id);
}
