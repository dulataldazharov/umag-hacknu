package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.model.Supply;
import kz.umag.hacknu.umaghacknu.repo.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    public SaleRepository saleRepository;

    public List<Sale> get(Long barcode, Date fromTime, Date toTime) {
        return saleRepository.get(barcode, fromTime, toTime);
    }

    public Sale create(Sale sale) {
        return saleRepository.save(sale);
    }
}
