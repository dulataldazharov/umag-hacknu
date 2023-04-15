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

    public List<Supply> get(Long barcode, Date fromTime, Date toTime) {
        return supplyRepository.get(barcode, fromTime, toTime);
    }

    public Supply save(Supply supply) {
        return supplyRepository.save(supply);
    }

    public void delete(Long id) {
        supplyRepository.deleteById(id);
    }
}
