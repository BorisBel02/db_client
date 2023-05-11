package org.nsu.db.Repo;

import org.nsu.db.Entity.WorkshopLabRelationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopLabRelationRepo extends CrudRepository<WorkshopLabRelationEntity, Integer> {
}
