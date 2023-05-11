package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "Sector", schema = "public", catalog = "manufacture")
public class SectorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "SectorID")
    private int sectorId;

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    @Basic
    @Column(name = "WorkshopID")
    private int workshopId;

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

    @Basic
    @Column(name = "SectorSupervisor")
    private int sectorSupervisor;

    public int getSectorSupervisor() {
        return sectorSupervisor;
    }

    public void setSectorSupervisor(int sectorSupervisor) {
        this.sectorSupervisor = sectorSupervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectorEntity that = (SectorEntity) o;
        return sectorId == that.sectorId && workshopId == that.workshopId && sectorSupervisor == that.sectorSupervisor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectorId, workshopId, sectorSupervisor);
    }
}
