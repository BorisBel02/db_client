package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "WorkflowArchive", schema = "public", catalog = "manufacture")
public class WorkflowArchiveEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "RecordID")
    private int recordId;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
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
    private int brigadeId;

    public int getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(int brigadeId) {
        this.brigadeId = brigadeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowArchiveEntity that = (WorkflowArchiveEntity) o;
        return recordId == that.recordId && productId == that.productId && brigadeId == that.brigadeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, productId, brigadeId);
    }
}
