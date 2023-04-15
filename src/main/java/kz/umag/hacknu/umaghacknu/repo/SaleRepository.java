package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.model.SaleSupply;
import kz.umag.hacknu.umaghacknu.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT * FROM sale s " +
            "WHERE (?1 IS NULL OR s.barcode = ?1) AND " +
            "   ?2 <= s.sale_time AND ?3 >= s.sale_time",
            nativeQuery = true)
    List<Sale> get(Long barcode, Date fromTime, Date toTime);

    @Query(nativeQuery = true, value = "SELECT * FROM sale s WHERE s.barcode = ?1 AND s.sale_time >= ?2 ORDER BY s.sale_time")
    List<Sale> findAllFromTime(Long barcode, Date fromTime);

    @Query(nativeQuery = true, value = "SELECT * FROM sale s WHERE s.barcode = ?1 AND s.sale_time < ?2 ORDER BY s.sale_time DESC LIMIT 1")
    Sale getFirstBefore(Long barcode, Date fromTime);


}
