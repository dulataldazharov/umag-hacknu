package kz.umag.hacknu.umaghacknu.controller;

import kz.umag.hacknu.umaghacknu.dto.Report;
import kz.umag.hacknu.umaghacknu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReportController {

    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    public ReportService reportService;

    @GetMapping(path = "api/reports")
    public @ResponseBody Report get(
            @RequestParam(name = "barcode") String barcodeStr,
            @RequestParam(name = "fromTime") String fromTimeStr,
            @RequestParam(name = "toTime") String toTimeStr) throws ParseException {
        Long barcode = barcodeStr == null ? null : Long.parseLong(barcodeStr);
        Date fromTime = fromTimeStr == null ? new Date(0L) : formatter.parse(fromTimeStr);
        Date toTime = toTimeStr == null ? new Date() : formatter.parse(toTimeStr);
        return reportService.getReport(barcode, fromTime, toTime);
    }
}
