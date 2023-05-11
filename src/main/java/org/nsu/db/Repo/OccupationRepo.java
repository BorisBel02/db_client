package org.nsu.db.Repo;

import org.nsu.db.Entity.OccupationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OccupationRepo extends JpaRepository<OccupationEntity, Integer> {
    Optional<OccupationEntity> findByOccupationName(String occupationName);
}
