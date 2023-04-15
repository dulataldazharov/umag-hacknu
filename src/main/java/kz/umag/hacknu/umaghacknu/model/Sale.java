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
    public Long barcode;

    @Column(name = "price")
    public Integer price;

    @Column(name = "quantity")
    public Integer quantity;

    @Column(name = "sale_time")
    public Date saleTime;
}
