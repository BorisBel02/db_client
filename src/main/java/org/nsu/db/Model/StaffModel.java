package org.nsu.db.Model;

import lombok.Getter;
import org.nsu.db.Entity.OccupationAttributeEntity;
import org.nsu.db.Entity.OccupationAttributeValueEntity;
import org.nsu.db.Entity.OccupationEntity;
import org.nsu.db.Entity.StaffEntity;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;


@Getter
public class StaffModel {
    String firstName;

    String secondName;
    String lastName;
    String occupationName;
    List<Pair<String, String>> occupationAttributes;
    Integer workshopId;

    public StaffModel(StaffEntity staffEntity, OccupationEntity occupationEntity,
                      List<Pair<OccupationAttributeEntity, OccupationAttributeValueEntity>> attributes){
        this.firstName = staffEntity.getFirstName();
        this.secondName = staffEntity.getSecondName();
        this.lastName = staffEntity.getLastName();

        this.occupationName = occupationEntity.getOccupationName();

        occupationAttributes = new ArrayList<>();
        for (var it : attributes) {
            occupationAttributes.add(Pair.of(it.getFirst().getOccupationAttributeName(), it.getSecond().getOccupationAttributeValue()));
        }
    }
}
