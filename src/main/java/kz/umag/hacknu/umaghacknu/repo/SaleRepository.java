package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT * FROM SALE s " +
            "WHERE (?1 IS NULL OR s.barcode = ?1) AND " +
            "   ?2 <= s.sale_time AND ?3 >= s.sale_time",
            nativeQuery = true)
    List<Sale> get(Long barcode, Date fromTime, Date toTime);
}
