package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.FirstUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstUpdateRepository extends JpaRepository<FirstUpdate, Long> {
    FirstUpdate findByBarcode(Long barcode);
}
