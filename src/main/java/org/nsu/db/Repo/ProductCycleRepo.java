package org.nsu.db.Repo;

import org.nsu.db.Entity.ProductCycleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCycleRepo extends CrudRepository<ProductCycleEntity, Integer> {
}
