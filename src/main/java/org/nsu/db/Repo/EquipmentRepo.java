package org.nsu.db.Repo;

import org.nsu.db.Entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepo extends JpaRepository<EquipmentEntity, Integer> {
    public List<EquipmentEntity> findAllByLabId(Integer labId);
    public boolean existsByEquipmentIdAndLabId(Integer equipmentId, Integer labId);

    @Query(value = """
        select "EquipmentName"
        from "Equipment"
        where "EquipmentID" in (
        	select "EquipmentID"
        	from "TestEquipment" sot
        	inner join "TestArchive" tst
        		on sot."TestID" = tst."TestID"
        	where tst."ProductID" in (
        		select "ProductID"
        		from "Product" pr
        		inner join "ProductType" pt
        			on pr."ProductTypeID" = pt."ProductTypeID"
        		where pt."ProductTypeName" = ?3
        		)
        		and (tst."Date" >= ?1 and tst."Date" <= ?2)
        		)
        """, nativeQuery = true)
    List<String> findByDateAndProductName(String dateMin, String dateMax, String name);
}
