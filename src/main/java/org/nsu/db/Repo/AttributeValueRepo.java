package org.nsu.db.Repo;

import org.nsu.db.Entity.AttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeValueRepo extends JpaRepository<AttributeValueEntity, Integer> {
    public List<AttributeValueEntity> findAllByProductId(Integer productId);
}
