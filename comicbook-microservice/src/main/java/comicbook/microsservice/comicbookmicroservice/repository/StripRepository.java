package comicbook.microsservice.comicbookmicroservice.repository;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StripRepository extends JpaRepository<Strip, Integer> {
}
