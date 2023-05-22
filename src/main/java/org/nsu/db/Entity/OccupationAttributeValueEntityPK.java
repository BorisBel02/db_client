package org.nsu.db.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OccupationAttributeValueEntityPK implements Serializable {
    private int occupationAttributeId;
    private int personId;

    public OccupationAttributeValueEntityPK(int occupationAttributeId, int personId){
        this.occupationAttributeId = occupationAttributeId;
        this.personId = personId;
    }
}
