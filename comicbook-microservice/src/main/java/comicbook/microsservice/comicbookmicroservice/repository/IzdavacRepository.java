package comicbook.microsservice.comicbookmicroservice.repository;

import comicbook.microsservice.comicbookmicroservice.model.Izdavac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IzdavacRepository extends JpaRepository<Izdavac, Integer> {
}
