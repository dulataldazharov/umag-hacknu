package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
    @Query(value = "SELECT * FROM supply s " +
            "WHERE (?1 IS NULL OR s.barcode = ?1) AND " +
            "   ?2 <= s.supply_time AND ?3 >= s.supply_time",
            nativeQuery = true)
    List<Supply> get(Long barcode, Date fromTime, Date toTime);

    List<Supply> findAllByBarcodeOrderBySupplyTime(Long barcode);

    @Query(nativeQuery = true, value = "SELECT s FROM supply s WHERE s.barcode = ?1 AND s.supplyTime >= ?2 ORDER BY s.supplyTime")
    List<Supply> findAllFromTime(Long barcode, Date fromTime);
}
