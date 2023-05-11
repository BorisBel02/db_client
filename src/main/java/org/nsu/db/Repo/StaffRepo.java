package org.nsu.db.Repo;

import org.nsu.db.Entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<StaffEntity, Integer> {
    List<StaffEntity> findAllStaffEntitiesByFirstNameAndSecondNameAndLastName(
            String firstName,
            String secondName,
            String lastName);

}
