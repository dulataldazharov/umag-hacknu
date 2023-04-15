package kz.umag.hacknu.umaghacknu.dto;

import java.io.Serializable;

public class SaleCreateDto implements Serializable {
    public Long barcode;
    public Integer price;
    public Integer quantity;
    public String saleTime;
}
