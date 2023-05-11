package org.nsu.db.Repo;

import org.nsu.db.Entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepo extends JpaRepository<EquipmentEntity, Integer> {
    public List<EquipmentEntity> findAllByLabId(Integer labId);
    public boolean existsByEquipmentIdAndLabId(Integer equipmentId, Integer labId);
}
