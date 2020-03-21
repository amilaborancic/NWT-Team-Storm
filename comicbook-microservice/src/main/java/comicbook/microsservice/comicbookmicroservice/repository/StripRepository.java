package comicbook.microsservice.comicbookmicroservice.repository;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StripRepository extends JpaRepository<Strip, Long> {
    List<Strip> findByAutori_ImeContaining(String ime);
    List<Strip> findByAutori_PrezimeContaining(String prezime);
    List<Strip> findByIdIzdavac(Long id);
    int countByIdIzdavac(Long id); //count query po id-ju izdavaca
    int countByAutori_Ime(String ime);
}
