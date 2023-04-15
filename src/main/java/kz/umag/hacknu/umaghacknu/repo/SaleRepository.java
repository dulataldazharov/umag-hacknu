package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
