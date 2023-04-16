package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.FirstUpdate;
import kz.umag.hacknu.umaghacknu.model.Supply;
import kz.umag.hacknu.umaghacknu.repo.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static kz.umag.hacknu.umaghacknu.util.Utils.isOld;

@Service
public class SupplyService {

    @Autowired
    public SupplyRepository supplyRepository;

    @Autowired
    public FirstUpdateService firstUpdateService;

    @Autowired
    public ReportService reportService;

    public List<Supply> get(Long barcode, Date fromTime, Date toTime) {
        return supplyRepository.get(barcode, fromTime, toTime);
    }

    public Supply save(Supply supply) {
        firstUpdateService.updateTime(supply.barcode, supply.supplyTime);
        Supply saved = supplyRepository.save(supply);
        if (isOld(supply.supplyTime)) {
            reportService.rebuild(new FirstUpdate(supply.barcode, supply.supplyTime));
        }
        return saved;
    }

    public void delete(Long id) {
        Supply supply = supplyRepository.findById(id).get();
        firstUpdateService.updateTime(supply.barcode, supply.supplyTime);
        supplyRepository.deleteById(id);
        if (isOld(supply.supplyTime)) {
            reportService.rebuild(new FirstUpdate(supply.barcode, supply.supplyTime));
        }
    }


}
