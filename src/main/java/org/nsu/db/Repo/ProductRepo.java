package org.nsu.db.Repo;

import org.nsu.db.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    public Optional<ProductEntity> findByName(String Name);
    public boolean existsByProductId(Integer id);
}