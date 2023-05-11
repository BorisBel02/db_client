package org.nsu.db.Repo;

import org.nsu.db.Entity.WorkerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepo extends CrudRepository<WorkerEntity, Integer> {

}
