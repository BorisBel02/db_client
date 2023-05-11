package org.nsu.db.Repo;

import org.nsu.db.Entity.EngineerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepo extends CrudRepository<EngineerEntity, Integer> {
}
