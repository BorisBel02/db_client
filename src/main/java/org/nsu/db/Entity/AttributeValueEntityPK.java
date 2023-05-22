package org.nsu.db.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttributeValueEntityPK implements Serializable {
    private int attributeId;
    private int productId;

    public AttributeValueEntityPK(int attributeId, int productId){
        this.attributeId = attributeId;
        this.productId = productId;
    }
}
