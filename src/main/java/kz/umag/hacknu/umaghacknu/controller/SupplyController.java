package kz.umag.hacknu.umaghacknu.controller;

import kz.umag.hacknu.umaghacknu.dto.SupplyCreateDto;
import kz.umag.hacknu.umaghacknu.dto.SupplyCreateResponse;
import kz.umag.hacknu.umaghacknu.model.Supply;
import kz.umag.hacknu.umaghacknu.service.SupplyService;
import kz.umag.hacknu.umaghacknu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SupplyController {
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    public TestService testService;

    @Autowired
    public SupplyService supplyService;

    @GetMapping(path="api/supplies")
    public @ResponseBody List<Supply> get(
            @RequestParam(name = "barcode", required = false) String barcodeStr,
            @RequestParam(name = "fromTime", required = false) String fromTimeStr,
            @RequestParam(name = "toTime", required = false) String toTimeStr) throws ParseException {
        Long barcode = barcodeStr == null ? null : Long.parseLong(barcodeStr);
        Date fromTime = fromTimeStr == null ? new Date(0L) : formatter.parse(fromTimeStr);
        Date toTime = toTimeStr == null ? new Date() : formatter.parse(toTimeStr);
        return supplyService.get(barcode, fromTime, toTime);
    }

    @PostMapping(path = "api/supplies")
    public @ResponseBody SupplyCreateResponse create(
            @RequestBody SupplyCreateDto supplyCreateDto
    ) throws ParseException {
        Supply supply = new Supply();
        supply.setBarcode(supplyCreateDto.barcode);
        supply.setPrice(supplyCreateDto.price);
        supply.setQuantity(supplyCreateDto.quantity);
        supply.setSupplyTime(formatter.parse(supplyCreateDto.supplyTime));
        supply = supplyService.save(supply);

        SupplyCreateResponse response = new SupplyCreateResponse();
        response.id = supply.id;
        return response;
    }

    @PutMapping(path = "api/supplies/{id}")
    public @ResponseBody SupplyCreateDto update(
            @PathVariable Long id,
            @RequestBody SupplyCreateDto supplyCreateDto
    ) throws ParseException {
        Supply supply = new Supply();
        supply.setId(id);
        supply.setBarcode(supplyCreateDto.barcode);
        supply.setPrice(supplyCreateDto.price);
        supply.setQuantity(supplyCreateDto.quantity);
        supply.setSupplyTime(formatter.parse(supplyCreateDto.supplyTime));
        supply = supplyService.save(supply);

        return supplyCreateDto;
    }
}
