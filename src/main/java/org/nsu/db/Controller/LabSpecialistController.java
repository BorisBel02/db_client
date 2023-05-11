package org.nsu.db.Controller;

import org.nsu.db.Entity.LabSpecialistEntity;
import org.nsu.db.Model.LabSpecialistModel;
import org.nsu.db.Repo.LabRepo;
import org.nsu.db.Repo.LabSpecialistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lab/lab_specialist")
public class LabSpecialistController {
    LabSpecialistRepo labSpecialistRepo;
    LabRepo labRepo;

    @Autowired
    public LabSpecialistController(LabSpecialistRepo labSpecialistRepo,
                                   LabRepo labRepo){
        this.labRepo = labRepo;
        this.labSpecialistRepo = labSpecialistRepo;
    }


    @GetMapping("/get_all")
    public ResponseEntity<?> get_all(){
        return new ResponseEntity<>(labSpecialistRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add_specialist(@RequestBody LabSpecialistModel model, @RequestParam Integer labId){
        if(!labRepo.existsById(labId)){
            return new ResponseEntity<>("Illegal LabID", HttpStatus.BAD_REQUEST);
        }
        LabSpecialistEntity labSpecialistEntity = new LabSpecialistEntity();
        labSpecialistEntity.setLabId(labId);
        labSpecialistEntity.setFirstName(model.getFirstName());
        labSpecialistEntity.setSecondName(model.getSecondName());
        labSpecialistEntity.setLastName(model.getLastName());

        labSpecialistRepo.save(labSpecialistEntity);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    //TODO realise api for lab, equipment, tests, products...
}
