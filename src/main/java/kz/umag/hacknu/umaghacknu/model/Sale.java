package kz.umag.hacknu.umaghacknu.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer id;

    @Column(name = "barcode")
    public Integer barcode;

    @Column(name = "price")
    public Integer price;

    @Column(name = "quantity")
    public Integer quantity;

    @Column(name = "supply_time")
    public Date saleTime;

    @Column(name = "supply_id")
    public Integer supplyId;

    @Column(name = "supply_seq")
    public Integer supplySeq;

    @Column(name = "margin")
    public Integer margin;

    @Column(name = "prefix_margin")
    public Integer prefixMargin;
}
