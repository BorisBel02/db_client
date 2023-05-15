package org.nsu.db.Repo;

import org.nsu.db.Entity.ProductCycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCycleRepo extends JpaRepository<ProductCycleEntity, Integer> {
    public boolean existsByProductId(Integer id);
    public void deleteAllByProductId(Integer id);
}
