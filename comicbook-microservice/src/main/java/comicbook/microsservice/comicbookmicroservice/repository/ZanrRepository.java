package comicbook.microsservice.comicbookmicroservice.repository;

import comicbook.microsservice.comicbookmicroservice.model.Zanr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZanrRepository extends JpaRepository<Zanr, Long> {


}
