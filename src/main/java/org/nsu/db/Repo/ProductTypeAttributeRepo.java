package org.nsu.db.Repo;

import org.nsu.db.Entity.ProductTypeAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeAttributeRepo extends JpaRepository<ProductTypeAttributeEntity, Integer> {
    public boolean existsByProductTypeIdAndAttributeName(Integer id, String name);
    public Optional<ProductTypeAttributeEntity> findByProductTypeIdAndAttributeName(Integer id, String name);
}
