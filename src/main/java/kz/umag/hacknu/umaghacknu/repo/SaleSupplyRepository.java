package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.SaleSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SaleSupplyRepository extends JpaRepository<SaleSupply, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM sale_supply s WHERE s.barcode = ?1 AND s.sale_time <= ?2 ORDER BY s.sale_time DESC LIMIT 1")
    SaleSupply getLast(Long barcode, Date toTime);
    @Query(nativeQuery = true, value = "SELECT * FROM sale_supply s WHERE s.barcode = ?1 AND s.sale_time < ?2 ORDER BY s.sale_time DESC LIMIT 1")
    SaleSupply getFirstBefore(Long barcode, Date fromTime);
    @Query(nativeQuery = true, value = "DELETE FROM sale_supply WHERE barcode = ?1 AND sale_time >= ?2")
    void deleteAllFromTime(Long barcode, Date fromTime);

    SaleSupply findBySaleId(Long saleId);
}
