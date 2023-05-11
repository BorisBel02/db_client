package org.nsu.db.Repo;

import org.nsu.db.Entity.OccupationAttributeValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccupationAttributeValueRepo extends JpaRepository<OccupationAttributeValueEntity, Integer> {
    List<OccupationAttributeValueEntity> findAllByPersonId(int id);
}
