package comicbook.microsservice.comicbookmicroservice.repository;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StripRepository extends JpaRepository<Strip, Long> {
    List<Strip> findAllByAutori_ImeContainsOrAutori_PrezimeContains(String ime, String prezime, Pageable pageable);
    List<Strip> findByIdIzdavac(Long id, Pageable pageable);
    List<Strip> findByIdZanr(Long id, Pageable pageable);
    List<Strip> findByNazivContaining(String naziv, Pageable pageable);
}
