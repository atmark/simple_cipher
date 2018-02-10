package my.cipher.persistence.repo;

import my.cipher.persistence.model.CipherEntry;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CipherEntryRepository extends CrudRepository<CipherEntry, Long> {

    List<CipherEntry> findByText(String text);

    Optional<CipherEntry> findOne(long id);

    Optional<CipherEntry> findFirstByOrderByIdDesc();
}
