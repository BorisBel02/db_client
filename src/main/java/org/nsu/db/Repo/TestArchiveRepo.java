package org.nsu.db.Repo;

import org.nsu.db.Entity.TestArchiveEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestArchiveRepo extends CrudRepository<TestArchiveEntity, Integer> {
}
