package kz.umag.hacknu.umaghacknu.dto;

import java.io.Serializable;

public class Report implements Serializable {

    public Report(Long barcode, Integer quantity, Long revenue, Long netProfit) {
        this.barcode = barcode;
        this.quantity = quantity;
        this.revenue = revenue;
        this.netProfit = netProfit;
    }

    public Long barcode;

    public Integer quantity;

    public Long revenue;

    public Long netProfit;
}
