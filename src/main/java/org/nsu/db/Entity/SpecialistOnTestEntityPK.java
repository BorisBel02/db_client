package org.nsu.db.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpecialistOnTestEntityPK implements Serializable {
    private int specialistId;
    private int testId;

    public SpecialistOnTestEntityPK(int specialistId, int testId){
        this.specialistId = specialistId;
        this.testId = testId;
    }
}
