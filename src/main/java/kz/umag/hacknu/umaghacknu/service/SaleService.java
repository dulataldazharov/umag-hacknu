package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.FirstUpdate;
import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.repo.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static kz.umag.hacknu.umaghacknu.util.Utils.isOld;

@Service
public class SaleService {

    @Autowired
    public SaleRepository saleRepository;

    @Autowired
    public FirstUpdateService firstUpdateService;

    @Autowired
    public ReportService reportService;

    public List<Sale> get(Long barcode, Date fromTime, Date toTime) {
        return saleRepository.get(barcode, fromTime, toTime);
    }

    public Sale save(Sale sale) {
        firstUpdateService.updateTime(sale.barcode, sale.saleTime);
        Sale saved = saleRepository.save(sale);
        if (isOld(sale.saleTime)) {
            reportService.rebuild(new FirstUpdate(sale.barcode, sale.saleTime));
        }
        return saved;
    }

    public void delete(Long id) {
        Sale sale = saleRepository.findById(id).get();
        firstUpdateService.updateTime(sale.barcode, sale.saleTime);
        saleRepository.deleteById(id);
        if (isOld(sale.saleTime)) {
            reportService.rebuild(new FirstUpdate(sale.barcode, sale.saleTime));
        }
    }
}
