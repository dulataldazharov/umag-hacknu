package kz.umag.hacknu.umaghacknu.controller;

import kz.umag.hacknu.umaghacknu.dto.SaleCreateDto;
import kz.umag.hacknu.umaghacknu.dto.SaleCreateResponse;
import kz.umag.hacknu.umaghacknu.dto.SupplyCreateDto;
import kz.umag.hacknu.umaghacknu.model.Sale;
import kz.umag.hacknu.umaghacknu.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SaleController {
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    public SaleService saleService;

    @GetMapping(path = "api/sales")
    public @ResponseBody List<Sale> test(
            @RequestParam(name = "barcode", required = false) String barcodeStr,
            @RequestParam(name = "fromTime", required = false) String fromTimeStr,
            @RequestParam(name = "toTime", required = false) String toTimeStr) throws ParseException {
        Long barcode = barcodeStr == null ? null : Long.parseLong(barcodeStr);
        Date fromTime = fromTimeStr == null ? new Date(0L) : formatter.parse(fromTimeStr);
        Date toTime = toTimeStr == null ? new Date() : formatter.parse(toTimeStr);
        return saleService.get(barcode, fromTime, toTime);
    }

    @PostMapping(path = "api/sales")
    public @ResponseBody SaleCreateResponse create(
            @RequestBody SaleCreateDto saleCreateDto
    ) throws ParseException {
        Sale sale = new Sale();
        sale.setBarcode(saleCreateDto.barcode);
        sale.setPrice(saleCreateDto.price);
        sale.setQuantity(saleCreateDto.quantity);
        sale.setSaleTime(formatter.parse(saleCreateDto.saleTime));
        sale = saleService.save(sale);

        SaleCreateResponse response = new SaleCreateResponse();
        response.id = sale.id;
        return response;
    }

    @PutMapping(path = "api/sales/{id}")
    public @ResponseBody SaleCreateDto update(
            @PathVariable Long id,
            @RequestBody SaleCreateDto saleCreateDto
    ) throws ParseException {
        Sale sale = new Sale();
        sale.setId(id);
        sale.setBarcode(saleCreateDto.barcode);
        sale.setPrice(saleCreateDto.price);
        sale.setQuantity(saleCreateDto.quantity);
        sale.setSaleTime(formatter.parse(saleCreateDto.saleTime));
        sale = saleService.save(sale);

        return saleCreateDto;
    }

    @DeleteMapping(path = "api/sales/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
