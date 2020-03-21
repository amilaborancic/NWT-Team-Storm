package catalogue.microsservice.cataloguemicroservice.repository;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StripRepository extends JpaRepository<Strip, Long> {
}
