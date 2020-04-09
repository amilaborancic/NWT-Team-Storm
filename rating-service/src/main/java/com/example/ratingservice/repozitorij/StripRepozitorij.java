package com.example.ratingservice.repozitorij;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Strip;

@Repository
public interface StripRepozitorij extends JpaRepository<Strip,Long> {
	
	Strip findByid(Long id);
	Strip getOne(Long id);
	List<Strip> findAll();
	void deleteById(Long id);
}
