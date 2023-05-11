package org.nsu.db.Repo;

import org.nsu.db.Entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductTypeEntity, Integer> {
    public Optional<ProductTypeEntity> findByProductTypeName(String name);
}
