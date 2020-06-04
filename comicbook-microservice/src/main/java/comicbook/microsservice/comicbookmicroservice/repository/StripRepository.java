package comicbook.microsservice.comicbookmicroservice.repository;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StripRepository extends JpaRepository<Strip, Long> {
    List<Strip> findByIdIzdavacOrderByNaziv(Long id, Pageable pageable);
    List<Strip> findByIdZanrOrderByNaziv(Long id, Pageable pageable);
    List<Strip> findByNazivContainsOrderByNaziv(String naziv, Pageable pageable);
    List<Strip> findAllByAutori_ImeContainsOrderByNaziv(String ime, Pageable pageable);
    List<Strip> findAllByAutori_PrezimeContainsOrderByNaziv(String ime, Pageable pageable);
    List<Strip> findAllByAutori_ImeContainsAndAutori_PrezimeContainsOrderByNaziv(String ime, String prezime, Pageable pageable);
    List<Strip> findAllByIdIn(List<Long> idStripova);
    List<Strip> findAllByOrderByNaziv(Pageable pageable);
    Long countStripByIdZanr(Long id);
    Long countStripByIdIzdavac(Long id);
    Long countStripByNazivContains(String naziv);
    Long countStripByAutori_ImeContainsAndAutori_PrezimeContains(String ime, String prezime);
    Long countStripByAutori_ImeContains(String ime);
    Long countStripByAutori_PrezimeContains(String prezime);

}
