package kz.umag.hacknu.umaghacknu.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

public class SupplyCreateDto implements Serializable {
    public Long barcode;
    public Integer price;
    public Integer quantity;
    public String supplyTime;
}
