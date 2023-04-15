package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.Supply;
import kz.umag.hacknu.umaghacknu.repo.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplyService {

    @Autowired
    public SupplyRepository supplyRepository;

    @Autowired
    public FirstUpdateService firstUpdateService;

    public List<Supply> get(Long barcode, Date fromTime, Date toTime) {
        return supplyRepository.get(barcode, fromTime, toTime);
    }

    public Supply save(Supply supply) {
        firstUpdateService.updateTime(supply.barcode, supply.supplyTime);
        return supplyRepository.save(supply);
    }

    public void delete(Long id) {
        Supply supply = supplyRepository.findById(id).get();
        firstUpdateService.updateTime(supply.barcode, supply.supplyTime);
        supplyRepository.deleteById(id);
    }
}
