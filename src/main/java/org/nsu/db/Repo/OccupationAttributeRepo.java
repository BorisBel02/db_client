package org.nsu.db.Repo;

import org.nsu.db.Entity.OccupationAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OccupationAttributeRepo extends JpaRepository<OccupationAttributeEntity, Integer> {
    boolean existsByOccupationAttributeNameAndOccupationId(String name, Integer occupationId);
    Optional<OccupationAttributeEntity> findByOccupationAttributeNameAndOccupationId(String name, Integer occupationId);
}
