package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "ProductInProgress", schema = "public", catalog = "manufacture")
public class ProductInProgressEntity {
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
    @Column(name = "BrigadeID")
    private Integer brigadeId;

    public Integer getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(Integer brigadeId) {
        this.brigadeId = brigadeId;
    }

    @Basic
    @Column(name = "Step")
    private int step;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInProgressEntity that = (ProductInProgressEntity) o;
        return serialNum == that.serialNum && productId == that.productId && step == that.step && Objects.equals(brigadeId, that.brigadeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNum, productId, brigadeId, step);
    }
}
