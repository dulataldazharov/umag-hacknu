package kz.umag.hacknu.umaghacknu.service;

import kz.umag.hacknu.umaghacknu.model.FirstUpdate;
import kz.umag.hacknu.umaghacknu.dto.Report;
import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.model.SaleSupply;
import kz.umag.hacknu.umaghacknu.model.Supply;
import kz.umag.hacknu.umaghacknu.repo.FirstUpdateRepository;
import kz.umag.hacknu.umaghacknu.repo.SaleRepository;
import kz.umag.hacknu.umaghacknu.repo.SaleSupplyRepository;
import kz.umag.hacknu.umaghacknu.repo.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            rebuild(barcode, update.time);
        }
        SaleSupply lastSaleSupply = saleSupplyRepository.getLast(barcode, toTime);
        if (Objects.isNull(lastSaleSupply) || lastSaleSupply.saleTime.compareTo(fromTime) < 0L) {
            return new Report(barcode, 0, 0L, 0L);
        }
        Report result = new Report(barcode, lastSaleSupply.prefixQuantity, lastSaleSupply.prefixRevenue, lastSaleSupply.prefixMargin);
        SaleSupply firstSaleSupply = saleSupplyRepository.getFirstBefore(barcode, fromTime);
        if (firstSaleSupply != null) {
            result.quantity -= firstSaleSupply.prefixQuantity;
            result.revenue -= firstSaleSupply.prefixRevenue;
            result.netProfit -= firstSaleSupply.prefixMargin;
        }
        return result;
    }

    private void rebuild(Long barcode, Date fromTime) {
        saleSupplyRepository.deleteAllFromTime(barcode, fromTime);
        List<Sale> sales = saleRepository.findAllFromTime(barcode, fromTime);
        if (sales.isEmpty()) {
            return;
        }
        Sale firstSale = saleRepository.getFirstBefore(barcode, fromTime);
        SaleSupply firstSaleSupply = null;
        List<Supply> supplies = null;
        if (firstSale == null) {
            firstSale = new Sale();
            firstSale.quantity = 0;
            firstSale.price = 0;
            firstSale.barcode = barcode;
            firstSale.saleTime = new Date(0);
            firstSaleSupply = new SaleSupply();
            firstSaleSupply.saleTime = new Date(0);
            firstSaleSupply.supplySeq = 0;
            firstSaleSupply.barcode = barcode;
            firstSaleSupply.prefixMargin = 0L;
            firstSaleSupply.prefixRevenue = 0L;
            firstSaleSupply.prefixQuantity = 0;
            supplies = supplyRepository.findAllByBarcodeOrderBySupplyTime(barcode);
        } else {
            firstSaleSupply = saleSupplyRepository.findBySaleId(firstSale.id);
            Supply firstSupply = supplyRepository.findById(firstSaleSupply.supplyId).get();
            supplies = supplyRepository.findAllFromTime(barcode, firstSupply.supplyTime);
        }
        int seq = firstSaleSupply.supplySeq;
        int i = 0;
        int q = firstSale.quantity;
        while (q > 0) {
            if (i == supplies.size() || supplies.get(i).supplyTime.compareTo(firstSale.saleTime) > 0) {
                break;
            }

            int available = supplies.get(i).quantity - seq;
            if (available <= q) {
                q -= available;
                i++;
                seq = 0;
            } else {
                seq += q;
                q = 0;
            }
        }
        List<SaleSupply> saleSupplies = new ArrayList<>();

        for (Sale sale : sales) {
            // TODO: consider no match
            SaleSupply saleSupply = new SaleSupply();
            saleSupply.barcode = barcode;
            saleSupply.saleId = sale.id;
            saleSupply.saleTime = sale.saleTime;
            saleSupply.supplyId = supplies.get(i).id;
            saleSupply.supplySeq = seq;
            saleSupply.prefixQuantity = firstSaleSupply.prefixQuantity + sale.quantity;
            saleSupply.prefixRevenue = firstSaleSupply.prefixRevenue + (long) sale.price * sale.quantity;

            q = sale.quantity;
            Long netProfit = 0L;
            while (q > 0) {
                if (i == supplies.size() || supplies.get(i).supplyTime.compareTo(sale.saleTime) > 0) {
                    netProfit += (long) q * sale.price;
                    break;
                }

                int available = supplies.get(i).quantity - seq;
                if (available <= q) {
                    netProfit += (long) available * (sale.price - supplies.get(i).price);
                    q -= available;
                    i++;
                    seq = 0;
                } else {
                    netProfit += (long) q * (sale.price - supplies.get(i).price);
                    seq += q;
                    q = 0;
                }
            }
            saleSupply.prefixMargin = firstSaleSupply.prefixMargin + netProfit;
            saleSupplies.add(saleSupply);
            firstSaleSupply = saleSupply;
        }

        saleSupplyRepository.saveAll(saleSupplies);
    }
}








