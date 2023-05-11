package org.nsu.db.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.List;

@Getter
@Setter
public class ProductModel {
    String productName;
    String productTypeName;
    Integer workshopId;

    List<Pair<String, String>> attributes;
}
