package org.nsu.db.Repo;

import org.nsu.db.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByName(String Name);
    boolean existsByProductId(Integer id);

    @Query(value = "select \"Name\" " +
            "from \"Product\" pr " +
            "where pr.\"ProductTypeID\" = ( " +
            "select \"ProductTypeID\" " +
            "from \"ProductType\" " +
            "where \"ProductTypeName\" = ?1)", nativeQuery = true)
    List<String> findByProductTypeName(String name);

    @Query(value = """
            select "Name"
            from "Product" pr
            where pr."ProductTypeID" = (
            \tselect "ProductTypeID"
            \tfrom "ProductType"
            \twhere "ProductTypeName" = ?1)\s
                and pr."WorkshopID" = ?2""", nativeQuery = true)
    List<String> findByProductTypeNameAndWorkshop(String name, Integer id);

    @Query(value = """
            select "Name"
            from "Product"
            where "ProductID" in(
            \tselect wa."ProductID"\s
            \tfrom "WorkflowArchive" wa
            \twhere ("Date" >= ?1 and "Date" <= ?2)
            \t)""", nativeQuery = true)
    List<String> findByTime(Date dateMin, Date dateMax);

    @Query(value = """
            select "Name"
            from "Product"
            where "ProductID" in(
            \tselect wa."ProductID"\s
            \tfrom "WorkflowArchive" wa
            \twhere ("Date" >= ?1 and "Date" <= ?2)
            \t)
            \tand "WorkshopID" = ?3""", nativeQuery = true)
    List<String> findByTimeAndWorkshop(Date dateMin, Date dateMax, Integer id);

    @Query(value = """
            select "Name"
            from "Product"
            where "ProductID" in(
            \tselect wa."ProductID"\s
            \tfrom "WorkflowArchive" wa
            \tinner join "Brigade" br
            \t\ton wa."BrigadeID" = br."BrigadeID"
            \twhere ("Date" >= ?1 and "Date" <= ?2)
            \t\tand br."SectorID" = ?3
            \t)""", nativeQuery = true)
    List<String> findByTimeAndSector(Date dateMin, Date dateMax, Integer id);

    @Query(value = """
            select "StepName"
            from "ProductCycle"
            where "ProductID" = (select "ProductID"
            \t\t    \tfrom "Product"
            \t\t\twhere "Name" = ?1)
            order by("Step")""", nativeQuery = true)
    List<String> findProductStepsByProductName(String name);
}
