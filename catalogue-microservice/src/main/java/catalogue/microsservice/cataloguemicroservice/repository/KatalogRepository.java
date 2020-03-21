package catalogue.microsservice.cataloguemicroservice.repository;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KatalogRepository extends JpaRepository<Katalog, Long> {
}
