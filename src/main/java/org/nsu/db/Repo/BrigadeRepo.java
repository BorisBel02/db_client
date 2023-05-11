package org.nsu.db.Repo;

import org.nsu.db.Entity.BrigadeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrigadeRepo extends CrudRepository<BrigadeEntity, Integer> {
    @Override
    boolean existsById(Integer integer);
}
