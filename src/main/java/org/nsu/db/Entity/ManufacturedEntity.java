package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "Manufactured", schema = "public", catalog = "manufacture")
public class ManufacturedEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "SerialNum")
    private int serialNum;

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    @Basic
    @Column(name = "ProductID")
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "Date")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturedEntity that = (ManufacturedEntity) o;
        return serialNum == that.serialNum && productId == that.productId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNum, productId, date);
    }
}
