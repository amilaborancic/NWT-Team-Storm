package catalogue.microsservice.cataloguemicroservice.repository;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KatalogRepository extends JpaRepository<Katalog, Long> {
    List<Katalog> findByIdKorisnik(Long id, Pageable pageable); //svi katalozi za jednog usera
}
