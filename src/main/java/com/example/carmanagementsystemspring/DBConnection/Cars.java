package com.example.carmanagementsystemspring.DBConnection;

import jakarta.persistence.*;

@Entity
@Table(name = "Cars")
public class Cars {
    private Integer carid;
    private String carname;
    private Integer carmry;
    private Integer price;
    @Id
    @Column(name = "CARID")
    @SequenceGenerator(name = "seq1", sequenceName = "seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq1")
    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }
    @Column(name = "CARNAME")
    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }
    @Column(name = "CARMRY")
    public Integer getCarmry() {
        return carmry;
    }

    public void setCarmry(Integer carmry) {
        this.carmry = carmry;
    }
    @Column(name = "PRICE")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
