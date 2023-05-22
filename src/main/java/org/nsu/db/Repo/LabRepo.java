package org.nsu.db.Repo;

import org.nsu.db.Entity.LabEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepo extends CrudRepository<LabEntity, Integer> {
    /*@Query(value = """
        select "LabID"
        from "Test"
        where "TestID" = (select "TestID"
        				  from "TestInProgress"
        				  where "SerialNum = ?1")
""", nativeQuery = true)
    List<Integer> findBySerialNum(Integer num);*/
}
