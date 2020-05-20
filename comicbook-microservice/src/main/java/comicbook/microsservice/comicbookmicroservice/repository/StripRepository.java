package comicbook.microsservice.comicbookmicroservice.repository;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StripRepository extends JpaRepository<Strip, Long> {
    List<Strip> findByIdIzdavac(Long id, Pageable pageable);
    List<Strip> findByIdZanr(Long id, Pageable pageable);
    List<Strip> findByNazivContains(String naziv, Pageable pageable);
    List<Strip> findAllByAutori_ImeContains(String ime, Pageable pageable);
    List<Strip> findAllByAutori_PrezimeContains(String ime, Pageable pageable);
    List<Strip> findAllByAutori_ImeContainsAndAutori_PrezimeContains(String ime, String prezime, Pageable pageable);
    List<Strip> findAllByIdIn(List<Long> idStripova);
    Long countStripByIdZanr(Long id);
    Long countStripByIdIzdavac(Long id);
    Long countStripByNazivContains(String naziv);
    Long countStripByAutori_ImeContainsAndAutori_PrezimeContains(String ime, String prezime);
    Long countStripByAutori_ImeContains(String ime);
    Long countStripByAutori_PrezimeContains(String prezime);

}
