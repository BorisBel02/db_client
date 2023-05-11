package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "Masters", schema = "public", catalog = "manufacture")
public class MastersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "MasterID")
    private int masterId;

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    @Basic
    @Column(name = "SupervisorID")
    private int supervisorId;

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MastersEntity that = (MastersEntity) o;
        return masterId == that.masterId && supervisorId == that.supervisorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterId, supervisorId);
    }
}
