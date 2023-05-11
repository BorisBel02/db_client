package org.nsu.db.Repo;

import org.nsu.db.Entity.ProductInProgressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInProgressRepo extends CrudRepository<ProductInProgressEntity, Integer> {
}
