package org.nsu.db.Repo;

import org.nsu.db.Entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepo extends JpaRepository<TestEntity, Integer> {
    List<TestEntity> findAllByTestName(String name);
}
