package org.nsu.db.Repo;

import org.nsu.db.Entity.LabEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepo extends CrudRepository<LabEntity, Integer> {
}
