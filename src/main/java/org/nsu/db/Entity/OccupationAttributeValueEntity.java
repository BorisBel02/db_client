package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "OccupationAttributeValue", schema = "public", catalog = "manufacture")
@IdClass(org.nsu.db.Entity.OccupationAttributeValueEntityPK.class)
public class OccupationAttributeValueEntity {
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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "PersonID")
    private int personId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "OccupationAttributeValue")
    private String occupationAttributeValue;

    public String getOccupationAttributeValue() {
        return occupationAttributeValue;
    }

    public void setOccupationAttributeValue(String occupationAttributeValue) {
        this.occupationAttributeValue = occupationAttributeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OccupationAttributeValueEntity that = (OccupationAttributeValueEntity) o;
        return occupationAttributeId == that.occupationAttributeId && personId == that.personId && Objects.equals(occupationAttributeValue, that.occupationAttributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupationAttributeId, personId, occupationAttributeValue);
    }
}
