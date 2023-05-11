package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "Workshop", schema = "public", catalog = "manufacture")
public class WorkshopEntity {
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

    @Basic
    @Column(name = "WorkshopSupervisor")
    private int workshopSupervisor;

    public int getWorkshopSupervisor() {
        return workshopSupervisor;
    }

    public void setWorkshopSupervisor(int workshopSupervisor) {
        this.workshopSupervisor = workshopSupervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkshopEntity that = (WorkshopEntity) o;
        return workshopId == that.workshopId && workshopSupervisor == that.workshopSupervisor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(workshopId, workshopSupervisor);
    }
}
