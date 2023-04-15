package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.FirstUpdate;
import kz.umag.hacknu.umaghacknu.dto.Report;
import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.model.SaleSupply;
import kz.umag.hacknu.umaghacknu.repo.FirstUpdateRepository;
import kz.umag.hacknu.umaghacknu.repo.SaleRepository;
import kz.umag.hacknu.umaghacknu.repo.SaleSupplyRepository;
import kz.umag.hacknu.umaghacknu.repo.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class ReportService {

    @Autowired
    public SupplyRepository supplyRepository;

    @Autowired
    public SaleRepository saleRepository;

    @Autowired
    public FirstUpdateRepository firstUpdateRepository;

    @Autowired
    public SaleSupplyRepository saleSupplyRepository;

    public Report getReport(Long barcode, Date fromTime, Date toTime) {
        FirstUpdate update = firstUpdateRepository.findByBarcode(barcode);
        if (!Objects.isNull(update)) {
            rebuild(barcode);
        }
        Sale lastSale = saleRepository.getLast(barcode, toTime);
        if (Objects.isNull(lastSale) || lastSale.saleTime.compareTo(fromTime) < 0L) {
            return new Report(barcode, 0, 0L, 0L);
        }
        SaleSupply lastSaleSupply = saleSupplyRepository.findById(lastSale.id).get();
        Report result = new Report(barcode, lastSaleSupply.prefixQuantity, lastSaleSupply.prefixRevenue, lastSaleSupply.prefixMargin);
        Sale firstSale = saleRepository.getFirstBefore(barcode, fromTime);
        if (firstSale != null) {
            SaleSupply firstSaleSupply = saleSupplyRepository.findById(firstSale.id).get();
            result.quantity -= firstSaleSupply.prefixQuantity;
            result.revenue -= firstSaleSupply.prefixRevenue;
            result.netProfit -= firstSaleSupply.prefixMargin;
        }
        return result;
    }

    private void rebuild(Long barcode) {
    }
}
