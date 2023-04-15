package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.FirstUpdate;
import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.repo.FirstUpdateRepository;
import kz.umag.hacknu.umaghacknu.repo.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    public SaleRepository saleRepository;

    @Autowired
    public FirstUpdateService firstUpdateService;

    public List<Sale> get(Long barcode, Date fromTime, Date toTime) {
        return saleRepository.get(barcode, fromTime, toTime);
    }

    public Sale save(Sale sale) {
        firstUpdateService.updateTime(sale.barcode, sale.saleTime);
        return saleRepository.save(sale);
    }

    public void delete(Long id) {
        Sale sale = saleRepository.findById(id).get();
        firstUpdateService.updateTime(sale.barcode, sale.saleTime);
        saleRepository.deleteById(id);
    }
}
