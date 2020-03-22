package comicbook.microsservice.comicbookmicroservice.repository;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StripRepository extends JpaRepository<Strip, Long> {
    List<Strip> findByAutori_ImeContaining(String ime);
    List<Strip> findByAutori_PrezimeContaining(String prezime);
    List<Strip> findByIdIzdavac(Long id, Pageable pageable);
    List<Strip> findByIdZanr(Long id, Pageable pageable);
    List<Strip> findByNazivContaining(String naziv, Pageable pageable);
    int countByIdIzdavac(Long id); //count query po id-ju izdavaca za paginaciju
    int countByIdZanr(Long id); //count query po id-ju zanra za paginaciju
    int countByNazivContaining(String naziv); //count query po slicnom nazivu za paginaciju
}
