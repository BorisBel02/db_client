package org.nsu.db.Controller;

import org.nsu.db.Entity.EquipmentEntity;
import org.nsu.db.Entity.LabEntity;
import org.nsu.db.Model.TestModel;
import org.nsu.db.Repo.*;
import org.nsu.db.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lab")
public class LabController {
    LabRepo labRepo;
    WorkshopLabRelationRepo workshopLabRelationRepo;
    LabSpecialistRepo labSpecialistRepo;
    EquipmentRepo equipmentRepo;
    SpecialistOnTestRepo specialistOnTestRepo;
    TestRepo testRepo;
    TestEquipmentRepo testEquipmentRepo;
    LabService labService;

    @Autowired
    public LabController(LabRepo labRepo, WorkshopLabRelationRepo workshopLabRelationRepo,
                         LabSpecialistRepo labSpecialistRepo, EquipmentRepo equipmentRepo,
                         SpecialistOnTestRepo specialistOnTestRepo,
                         TestRepo testRepo, TestEquipmentRepo testEquipmentRepo,
                         LabService labService){
        this.labRepo = labRepo;
        this.workshopLabRelationRepo = workshopLabRelationRepo;
        this.labSpecialistRepo = labSpecialistRepo;
        this.equipmentRepo = equipmentRepo;
        this.specialistOnTestRepo = specialistOnTestRepo;
        this.testRepo = testRepo;
        this.testEquipmentRepo = testEquipmentRepo;
        this.labService = labService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> get_all_labs(){
        return new ResponseEntity<>(labRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get_relation")
    public ResponseEntity<?> get_relation(){
        return new ResponseEntity<>(workshopLabRelationRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add_lab(){
        LabEntity labEntity = new LabEntity();
        LabEntity newLab = labRepo.save(labEntity);
        return new ResponseEntity<>("New Lab with number" + newLab.getLabId() + ".\nSuccess", HttpStatus.OK);
    }


    @GetMapping("/equipment/get")
    public ResponseEntity<?> get_all_equipment(@RequestParam(required = false) Optional<Integer> labId){
        if(labId.isPresent()){
            if(!labRepo.existsById(labId.get())){
                return new ResponseEntity<>("Lab " + labId.get() + " does not exists", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(equipmentRepo.findAllByLabId(labId.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(equipmentRepo.findAll(), HttpStatus.OK);
    }
    @PostMapping("/equipment/add")
    public ResponseEntity<?> add_equipment(@RequestParam Integer labId, @RequestParam String equipmentName){
        if(!labRepo.existsById(labId)){
            return new ResponseEntity<>("Lab " + labId + " does not exist", HttpStatus.BAD_REQUEST);
        }

        EquipmentEntity equipmentEntity = new EquipmentEntity();
        equipmentEntity.setEquipmentName(equipmentName);
        equipmentEntity.setLabId(labId);

        equipmentRepo.save(equipmentEntity);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/test/get")
    public ResponseEntity<?> get_test(@RequestParam(required = false) Optional<String> testName){
        return testName.map(s ->
                new ResponseEntity<>(testRepo.findAllByTestName(s), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(testRepo.findAll(), HttpStatus.OK));
    }

    @PostMapping("/test/add")
    public ResponseEntity<?> add_test(@RequestBody TestModel testModel){
        return labService.add_test(testModel);
    }
}
