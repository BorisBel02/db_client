package org.nsu.db.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Lab", schema = "public", catalog = "manufacture")
public class LabEntity {
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
        LabEntity labEntity = (LabEntity) o;
        return labId == labEntity.labId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(labId);
    }
}
