package kz.umag.hacknu.umaghacknu.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "first_update")
public class FirstUpdate {

    public FirstUpdate() {

    }
    public FirstUpdate(Long barcode, Date time) {
        this.barcode = barcode;
        this.time = time;
    }

    @Id
    @Column(name = "barcode")
    public Long barcode;

    @Column(name = "time")
    public Date time;
}
