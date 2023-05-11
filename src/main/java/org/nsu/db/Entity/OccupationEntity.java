package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "Occupation", schema = "public", catalog = "manufacture")
public class OccupationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "OccupationID")
    private int occupationId;

    public int getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }

    @Basic
    @Column(name = "OccupationName")
    private String occupationName;

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OccupationEntity that = (OccupationEntity) o;
        return occupationId == that.occupationId && Objects.equals(occupationName, that.occupationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupationId, occupationName);
    }
}
