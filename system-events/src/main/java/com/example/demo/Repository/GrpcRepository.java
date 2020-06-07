package com.example.demo.Repository;

import com.example.demo.Models.Event;
import org.springframework.data.repository.CrudRepository;

public interface GrpcRepository extends CrudRepository<Event, Long> {
}
