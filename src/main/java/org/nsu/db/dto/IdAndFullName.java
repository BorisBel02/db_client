package org.nsu.db.dto;

import lombok.Data;

@Data
public class IdAndFullName {
    Integer SectorId;
    String firstName;
    String secondName;
    String lastName;
}
