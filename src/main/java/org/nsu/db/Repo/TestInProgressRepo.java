package org.nsu.db.Repo;

import org.nsu.db.Entity.TestInProgressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestInProgressRepo extends CrudRepository<TestInProgressEntity, Integer> {
}
