package org.nsu.db.Repo;

import org.nsu.db.Entity.TestEquipmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEquipmentRepo extends CrudRepository<TestEquipmentEntity, Integer> {
}
