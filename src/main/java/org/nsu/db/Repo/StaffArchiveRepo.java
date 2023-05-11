package org.nsu.db.Repo;

import org.nsu.db.Entity.StaffArchiveEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffArchiveRepo extends CrudRepository<StaffArchiveEntity, Integer> {
}
