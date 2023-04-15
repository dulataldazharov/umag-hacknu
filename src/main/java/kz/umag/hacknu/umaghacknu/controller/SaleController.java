package kz.umag.hacknu.umaghacknu.controller;

import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.model.Supply;
import kz.umag.hacknu.umaghacknu.service.SaleService;
import kz.umag.hacknu.umaghacknu.service.SupplyService;
import kz.umag.hacknu.umaghacknu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SaleController {
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    public SaleService saleService;

    @GetMapping(path="api/sales")
    public @ResponseBody List<Sale> test(
            @RequestParam(name = "barcode", required = false) String barcodeStr,
            @RequestParam(name = "fromTime", required = false) String fromTimeStr,
            @RequestParam(name = "toTime", required = false) String toTimeStr) throws ParseException {
        Long barcode = barcodeStr == null ? null : Long.parseLong(barcodeStr);
        Date fromTime = fromTimeStr == null ? new Date(0L) : formatter.parse(fromTimeStr);
        Date toTime = toTimeStr == null ? new Date() : formatter.parse(toTimeStr);
        return saleService.get(barcode, fromTime, toTime);
    }
}
