package org.nsu.db.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "WorkshopLabRelation", schema = "public", catalog = "manufacture")
@jakarta.persistence.IdClass(org.nsu.db.Entity.WorkshopLabRelationEntityPK.class)
public class WorkshopLabRelationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "WorkshopID")
    private int workshopId;

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "LabID")
    private int labId;

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkshopLabRelationEntity that = (WorkshopLabRelationEntity) o;
        return workshopId == that.workshopId && labId == that.labId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(workshopId, labId);
    }
}
