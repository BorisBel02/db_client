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

    @PostMapping("/add_worker")
    public ResponseEntity<?> add_worker(@RequestBody StaffModel staffModel, @RequestParam Integer brigadeId){
        return new ResponseEntity<>(staffService.add_worker(staffModel, brigadeId), HttpStatus.OK);
    }
    @PostMapping("/add_engineer")
    public ResponseEntity<?> add_engineer(@RequestBody StaffModel staffModel,
                                          @RequestParam("name=supervisor") Optional<Integer> supervisorId){
        return new ResponseEntity<>(staffService.add_engineer(staffModel, supervisorId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete_staff(@RequestParam("name=personId") Integer personId){
        staffRepo.deleteById(personId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
