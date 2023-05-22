package org.nsu.db.Repo;

import org.nsu.db.Entity.StaffEntity;
import org.nsu.db.dto.NameAndOccupation;
import org.nsu.db.dto.IdAndFullName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<StaffEntity, Integer> {
    List<StaffEntity> findAllStaffEntitiesByFirstNameAndSecondNameAndLastName(
            String firstName,
            String secondName,
            String lastName);

    @Query(value = """
            select "FirstName", "SecondName", "LastName", "OccupationName"\s
            from "Staff" s
            inner join "Occupation" o
            \ton s."OccupationID" = o."OccupationID"
            where "OccupationName" in ?1""", nativeQuery = true)
    List<Object> findAllByOccupation(List<String> occupations);

    @Query(value = """
            select "SectorID", "FirstName", "SecondName", "LastName"
            from "Sector" sec
            inner join "Staff" s
            \ton s."PersonID" = sec."SectorSupervisor"
            """, nativeQuery = true)
    List<Object> findAllSectorsWithSupervisors();

    @Query(value = """
            select "SectorID", "FirstName", "SecondName", "LastName"
            from "Sector" sec
            inner join "Staff" s
            \ton s."PersonID" = sec."SectorSupervisor"
            where sec."WorkshopID" = ?1""", nativeQuery = true)
    List<Object> findAllSectorsWithSupervisorsByWorkshop(Integer id);

    @Query(value = """
            select b."BrigadeID", "FirstName", "SecondName", "LastName"
            from "Brigade" b
            inner join "Worker" w
            \ton b."BrigadeID" = w."BrigadeID"
            inner join "Staff" s
            \ton s."PersonID" = w."WorkerID"
            where b."SectorID" = ?1 order by(b."BrigadeID")""", nativeQuery = true)
    List<Object> findBrigadeStaffBySectorId(Integer id);

    @Query(value = """
            select b."BrigadeID", "FirstName", "SecondName", "LastName"
            from "Brigade" b
            inner join "Worker" w
            \ton b."BrigadeID" = w."BrigadeID"
            inner join "Staff" s
            \ton s."PersonID" = w."WorkerID"
            where b."SectorID" in (select "SectorID"
            \t\t\t\t\t   from "Sector"
            \t\t\t\t\t   where "WorkshopID" = ?1)
            order by(b."BrigadeID")""", nativeQuery = true)
    List<Object> findBrigadeStaffByWorkshopId(Integer id);

    @Query(value = """
            select "SectorID","FirstName", "SecondName", "LastName"
            from "Staff" s
            inner join "Masters" ms
            \ton ms."MasterID" = s."PersonID"
            inner join "Sector" sec
            \ton sec."SectorSupervisor" = ms."SupervisorID"
            where sec."WorkshopID" = ?1""", nativeQuery = true)
    List<Object> findMastersByWorkshop(Integer id);

    @Query(value = """
            select "SectorID","FirstName", "SecondName", "LastName"
            from "Staff" s
            inner join "Masters" ms
            \ton ms."MasterID" = s."PersonID"
            inner join "Sector" sec
            \ton sec."SectorSupervisor" = ms."SupervisorID"
            where sec."SectorID" = ?1""", nativeQuery = true)
    List<Object> findMastersBySector(Integer id);

    @Query(value = """
            select "Name"
            from "ProductInProgress" pip
            inner join "Product" pr
            \ton pip."ProductID" = pr."ProductID"
            \t\twhere pip."ProductID" in (select "ProductID"
            \t\t\t\t\t\t  from "ProductCycle"
            \t\t\t\t\t\t  where "SectorID" = ?1)""", nativeQuery = true)
    List<String> findAllProductsInProgressBySector(Integer id);

    @Query(value = """
            select "Name"
            from "ProductInProgress" pip
            inner join "Product" pr
            \ton pip."ProductID" = pr."ProductID"
            \t\twhere pip."ProductID" in (select "ProductID"
            \t\t\t\t\t\t  from "ProductCycle"
            \t\t\t\t\t\t  where "WorkshopID" = ?1)""", nativeQuery = true)
    List<String> findAllProductsInProgressByWorkshop(Integer id);

    @Query(value = """
            select "Name"
            from "ProductInProgress" pip
            inner join "Product" pr
            \ton pip."ProductID" = pr."ProductID"
            """, nativeQuery = true)
    List<String> findAllProductsInProgress();

    @Query(value = """
        select b."BrigadeID", s."FirstName", s."SecondName", s."LastName"
        from "ProductCycle" pc
        inner join "Brigade" b
	        on b."SectorID" = pc."SectorID"
        inner join "Product" pr
	        on pr."ProductID" = pc."ProductID"
        inner join "Worker" w
	        on w."BrigadeID" = b."BrigadeID"
        inner join "Staff" s
	        on s."PersonID" = w."WorkerID"
        where "Name" = ?1
        """, nativeQuery = true)
    List<Object> findBrigadeStaffInProductCreation(String name);


}
