package org.nsu.db.Controller;

import org.nsu.db.Entity.*;
import org.nsu.db.Model.StaffModel;
import org.nsu.db.Repo.*;
import org.nsu.db.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffRepo staffRepo;
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffRepo staffRepo, StaffService staffService){
        this.staffRepo = staffRepo;
        this.staffService = staffService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getStaff(){
        List<StaffEntity> staffEntities = staffRepo.findAll();
        return new ResponseEntity<>(staffService.convertStaffEntityList(staffEntities), HttpStatus.OK);
    }

    @GetMapping("/get_employee/{firstName}_{secondName}_{lastName}")
    public ResponseEntity<?> get_employee_by_name(@PathVariable("firstName") String firstName,
                                                 @PathVariable(value = "secondName", required = false) String secondName,
                                                 @PathVariable("lastName") String lastName){
        List<StaffEntity> staffEntities = staffRepo.findAllStaffEntitiesByFirstNameAndSecondNameAndLastName(
                firstName,
                secondName,
                lastName
        );
        return new ResponseEntity<>(staffService.convertStaffEntityList(staffEntities), HttpStatus.OK);
    }
    @GetMapping("/get_by_occupation")
    public ResponseEntity<?> get_by_occupation(@RequestParam(name = "occupations") List<String> occupations){
        return new ResponseEntity<>(staffRepo.findAllByOccupation(occupations), HttpStatus.OK);
    }
    @GetMapping("/get_sectors_with_supervisors")
    public ResponseEntity<?> get_sectors_with_supervisors(){
        return new ResponseEntity<>(staffRepo.findAllSectorsWithSupervisors(), HttpStatus.OK);
    }
    @GetMapping("/get_sectors_with_supervisors_by_workshop_id")
    public ResponseEntity<?> get_sectors_with_supervisors_by_workshop_id(
            @RequestParam Integer workshopId){
        return new ResponseEntity<>(staffRepo.findAllSectorsWithSupervisorsByWorkshop(workshopId),
                HttpStatus.OK);
    }
    @GetMapping("get_brigades_by_workshop_id")
    public ResponseEntity<?> get_brigades_by_workshop_id(@RequestParam Integer workshopId){
        return new ResponseEntity<>(staffRepo.findBrigadeStaffByWorkshopId(workshopId), HttpStatus.OK);
    }
    @GetMapping("get_brigades_by_sector_id")
    public ResponseEntity<?> get_brigades_by_sector_id(@RequestParam Integer sectorId){
        return new ResponseEntity<>(staffRepo.findBrigadeStaffBySectorId(sectorId), HttpStatus.OK);
    }
    @GetMapping("brigades_by_product_in_progress")
    public ResponseEntity<?> brigades_by_product_in_progress(@RequestParam String name){
        return new ResponseEntity<>(staffRepo.findBrigadeStaffInProductCreation(name),
                HttpStatus.OK);
    }
    @GetMapping("get_masters_by_workshop_id")
    public ResponseEntity<?> get_masters_by_workshop_id(@RequestParam Integer workshopId){
        return new ResponseEntity<>(staffRepo.findMastersByWorkshop(workshopId), HttpStatus.OK);
    }
    @GetMapping("get_masters_by_sector_id")
    public ResponseEntity<?> get_masters_by_sector_id(@RequestParam Integer sectorId){
        return new ResponseEntity<>(staffRepo.findMastersBySector(sectorId), HttpStatus.OK);
    }
    @GetMapping("get_products_in_progress")
    public ResponseEntity<?> get_products_in_progress(){
        return new
                ResponseEntity<>(staffRepo.findAllProductsInProgress(),
                HttpStatus.OK);
    }
    @GetMapping("get_products_in_progress_by_workshop_id")
    public ResponseEntity<?> get_products_in_progress_by_workshop_id(
            @RequestParam Integer workshopId){
        return new
                ResponseEntity<>(staffRepo.findAllProductsInProgressByWorkshop(workshopId),
                HttpStatus.OK);
    }
    @GetMapping("get_products_in_progress_by_sector_id")
    public ResponseEntity<?> get_products_in_progress_by_sector_id(
            @RequestParam Integer sectorId){
        return new
                ResponseEntity<>(staffRepo.findAllProductsInProgressBySector(sectorId),
                HttpStatus.OK);
    }

    @PostMapping("/add_worker")
    public ResponseEntity<?> add_worker(@RequestBody StaffModel staffModel, @RequestParam Integer brigadeId){
        return new ResponseEntity<>(staffService.add_worker(staffModel, brigadeId), HttpStatus.OK);
    }
    @PostMapping("/add_engineer")
    public ResponseEntity<?> add_engineer(@RequestBody StaffModel staffModel,
                                          @RequestParam(value = "name=supervisor",
                                                  required = false)
                                            Optional<Integer> supervisorId){    //if supervisor is  present then engineer is master
        return new ResponseEntity<>(staffService.add_engineer(staffModel, supervisorId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete_staff(@RequestParam("name=personId") Integer personId){
        staffRepo.deleteById(personId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
