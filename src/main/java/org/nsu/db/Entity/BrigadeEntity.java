package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "Brigade", schema = "public", catalog = "manufacture")
public class BrigadeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "BrigadeID")
    private int brigadeId;

    public int getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(int brigadeId) {
        this.brigadeId = brigadeId;
    }

    @Basic
    @Column(name = "ForemanID")
    private int foremanId;

    public int getForemanId() {
        return foremanId;
    }

    public void setForemanId(int foremanId) {
        this.foremanId = foremanId;
    }

    @Basic
    @Column(name = "SectorID")
    private int sectorId;

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrigadeEntity that = (BrigadeEntity) o;
        return brigadeId == that.brigadeId && foremanId == that.foremanId && sectorId == that.sectorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brigadeId, foremanId, sectorId);
    }
}
