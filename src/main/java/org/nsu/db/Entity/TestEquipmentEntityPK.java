package org.nsu.db.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestEquipmentEntityPK implements Serializable {
    private int testId;
    private int labId;
    private int equipmentId;

    public TestEquipmentEntityPK(int testId, int labId, int equipmentId){
        this.testId = testId;
        this.labId = labId;
        this.equipmentId = equipmentId;
    }
}
