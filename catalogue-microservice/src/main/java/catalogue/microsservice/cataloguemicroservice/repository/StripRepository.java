package catalogue.microsservice.cataloguemicroservice.repository;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StripRepository extends JpaRepository<Strip, Long> {
    List<Strip> findByKatalozi_Id(Long id, Pageable pageable);
    Long countAllByKatalozi_Id(Long id);
    boolean existsByIdAndKatalozi_Id(Long id_strip, Long id_katalog);
    Long countStripByKatalozi_Id(Long id);
    Strip getOne(Long id);
}
