package kz.umag.hacknu.umaghacknu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    @Column(name = "name")
    public String name;
}
