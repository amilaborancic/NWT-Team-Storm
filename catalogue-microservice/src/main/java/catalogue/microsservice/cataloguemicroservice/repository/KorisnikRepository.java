package catalogue.microsservice.cataloguemicroservice.repository;

import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
}
