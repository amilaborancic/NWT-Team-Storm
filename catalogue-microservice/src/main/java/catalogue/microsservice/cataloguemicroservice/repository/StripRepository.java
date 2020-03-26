package catalogue.microsservice.cataloguemicroservice.repository;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StripRepository extends JpaRepository<Strip, Long> {
    List<Strip> findByKatalozi_Id(Long id, Pageable pageable);
}