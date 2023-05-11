package org.nsu.db.Repo;

import org.nsu.db.Entity.ManufacturedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturedRepo extends CrudRepository<ManufacturedEntity, Integer> {
}
