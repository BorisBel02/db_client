package org.nsu.db.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.List;

@Getter
@Setter
public class TestModel {
    String testName;
    Integer labId;
    String productName;

    List<LabSpecialistModel> specialistModels;
    List<Integer> equipment;
}
