package org.nsu.db.Repo;

import org.nsu.db.Entity.LabSpecialistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabSpecialistRepo extends JpaRepository<LabSpecialistEntity, Integer> {
    public Optional<LabSpecialistEntity> findByFirstNameAndSecondNameAndLastNameAndLabId(
            String firstName, String secondName, String lastName, Integer labId);
}
