package org.nsu.db.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductCycleEntityPK implements Serializable {
    private int productId;
    private int sectorId;
    private int step;

    public ProductCycleEntityPK(int productId, int sectorId, int step){
        this.productId = productId;
        this.sectorId = sectorId;
        this.step = step;
    }
}
