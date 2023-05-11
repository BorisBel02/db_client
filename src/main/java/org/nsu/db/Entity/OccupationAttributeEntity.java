package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "OccupationAttribute", schema = "public", catalog = "manufacture")
public class OccupationAttributeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "OccupationAttributeID")
    private int occupationAttributeId;

    public int getOccupationAttributeId() {
        return occupationAttributeId;
    }

    public void setOccupationAttributeId(int occupationAttributeId) {
        this.occupationAttributeId = occupationAttributeId;
    }

    @Basic
    @Column(name = "OccupationID")
    private int occupationId;

    public int getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }

    @Basic
    @Column(name = "OccupationAttributeName")
    private String occupationAttributeName;

    public String getOccupationAttributeName() {
        return occupationAttributeName;
    }

    public void setOccupationAttributeName(String occupationAttributeName) {
        this.occupationAttributeName = occupationAttributeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OccupationAttributeEntity that = (OccupationAttributeEntity) o;
        return occupationAttributeId == that.occupationAttributeId && occupationId == that.occupationId && Objects.equals(occupationAttributeName, that.occupationAttributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupationAttributeId, occupationId, occupationAttributeName);
    }
}
