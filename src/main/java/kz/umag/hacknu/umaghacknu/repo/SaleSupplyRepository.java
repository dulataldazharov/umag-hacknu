package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.SaleSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleSupplyRepository extends JpaRepository<SaleSupply, Long> {
}
