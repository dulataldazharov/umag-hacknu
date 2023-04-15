package kz.umag.hacknu.umaghacknu.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "supply")
public class Supply {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer id;
    @Column(name = "barcode")
    public String barcode;

    @Column(name = "price")
    public Integer price;

    @Column(name = "quantity")
    public Integer quantity;

    @Column(name = "supply_time")
    public Date supplyTime;
}
