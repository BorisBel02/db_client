package org.nsu.db.Repo;

import org.nsu.db.Entity.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepo extends JpaRepository<SectorEntity, Integer> {
    Optional<SectorEntity> findBySectorSupervisor(Integer sectorSupervisor);
}
