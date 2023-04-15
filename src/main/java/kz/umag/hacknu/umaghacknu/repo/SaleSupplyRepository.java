package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.SaleSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SaleSupplyRepository extends JpaRepository<SaleSupply, Long> {

    @Query(nativeQuery = true, value = "SELECT s FROM SaleSupply s WHERE s.barcode = ?1 AND s.saleTime <= ?2 ORDER BY s.saleTime DESC LIMIT 1")
    SaleSupply getLast(Long barcode, Date toTime);
    @Query(nativeQuery = true, value = "SELECT s FROM SaleSupply s WHERE s.barcode = ?1 AND s.saleTime < ?2 ORDER BY s.saleTime DESC LIMIT 1")
    SaleSupply getFirstBefore(Long barcode, Date fromTime);
    @Query(nativeQuery = true, value = "DELETE FROM SaleSupply s WHERE s.barcode = ?1 AND s.saleTime >= ?2")
    void deleteAllFromTime(Long barcode, Date fromTime);

    SaleSupply findBySaleId(Long saleId);
}
