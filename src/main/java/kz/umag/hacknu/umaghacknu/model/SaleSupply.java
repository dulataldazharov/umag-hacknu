package kz.umag.hacknu.umaghacknu.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sale_supply")
public class SaleSupply {

    @Id
    @Column(name = "sale_id")
    public Long saleId;

    @Column(name = "barcode")
    public Long barcode;

    @Column(name = "supply_id")
    public Long supplyId;

    @Column(name = "supply_seq")
    public Integer supplySeq;

    @Column(name = "prefix_margin")
    public Long prefixMargin;

    @Column(name = "prefix_quantity")
    public Integer prefixQuantity;

    @Column(name = "prefix_revenue")
    public Long prefixRevenue;

    @Column(name = "sale_time")
    public Date saleTime;
}
