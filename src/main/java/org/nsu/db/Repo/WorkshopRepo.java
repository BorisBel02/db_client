package org.nsu.db.Repo;

import org.nsu.db.Entity.WorkshopEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepo extends CrudRepository<WorkshopEntity, Integer> {
    public Boolean existsByWorkshopId(Integer id);
}
