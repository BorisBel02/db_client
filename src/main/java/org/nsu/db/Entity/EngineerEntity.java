package org.nsu.db.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "Engineer", schema = "public", catalog = "manufacture")
public class EngineerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "EngineerID")
    private int engineerId;

    public int getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(int engineerId) {
        this.engineerId = engineerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineerEntity that = (EngineerEntity) o;
        return engineerId == that.engineerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineerId);
    }
}
