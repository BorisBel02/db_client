package org.nsu.db.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkshopLabRelationEntityPK implements Serializable {
    private int workshopId;
    private int labId;

    public WorkshopLabRelationEntityPK(int workshopId, int labId){
        this.workshopId = workshopId;
        this.labId = labId;
    }
}
