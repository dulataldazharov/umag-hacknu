package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Integer> {
    @Query(value = "SELECT * FROM SUPPLY s " +
            "WHERE (?1 IS NULL OR s.barcode = ?1) AND " +
            "   ?2 <= s.supply_time AND ?3 >= s.supply_time",
            nativeQuery = true)
    List<Supply> get(Long barcode, Date fromTime, Date toTime);
}
