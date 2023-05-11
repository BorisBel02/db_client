package org.nsu.db.Repo;

import org.nsu.db.Entity.MastersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MastersRepo extends CrudRepository<MastersEntity, Integer> {
}
