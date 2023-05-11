package org.nsu.db.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "SpecialistOnTest", schema = "public", catalog = "manufacture")
@jakarta.persistence.IdClass(org.nsu.db.Entity.SpecialistOnTestEntityPK.class)
public class SpecialistOnTestEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "SpecialistID")
    private int specialistId;

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "TestID")
    private int testId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialistOnTestEntity that = (SpecialistOnTestEntity) o;
        return specialistId == that.specialistId && testId == that.testId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialistId, testId);
    }
}
