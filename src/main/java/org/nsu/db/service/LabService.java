package org.nsu.db.service;

import org.nsu.db.Entity.*;
import org.nsu.db.Model.TestModel;
import org.nsu.db.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LabService {
    TestRepo testRepo;
    LabRepo labRepo;
    LabSpecialistRepo labSpecialistRepo;
    SpecialistOnTestRepo specialistOnTestRepo;
    EquipmentRepo equipmentRepo;
    TestEquipmentRepo testEquipmentRepo;
    ProductRepo productRepo;

    @Autowired
    public LabService(TestRepo testRepo, LabRepo labRepo,
                      LabSpecialistRepo labSpecialistRepo,
                      EquipmentRepo equipmentRepo,
                      TestEquipmentRepo testEquipmentRepo,
                      ProductRepo productRepo){
        this.testRepo = testRepo;
        this.labRepo = labRepo;
        this.labSpecialistRepo = labSpecialistRepo;
        this.equipmentRepo = equipmentRepo;
        this.testEquipmentRepo = testEquipmentRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public ResponseEntity<?> add_test(TestModel testModel){
        if (!labRepo.existsById(testModel.getLabId())){
            return new ResponseEntity<>("Lab + " + testModel.getLabId() + " does not exists", HttpStatus.BAD_REQUEST);
        }
        TestEntity test = new TestEntity();
        test.setTestName(testModel.getTestName());

        Optional<ProductEntity> productEntity = productRepo.findByName(testModel.getProductName());
        if (productEntity.isEmpty()){
            return new ResponseEntity<>("Unknown product " + testModel.getProductName(), HttpStatus.BAD_REQUEST);
        }
        test.setProductId(productEntity.get().getProductId());

        int testId = testRepo.save(test).getTestId();


        List<SpecialistOnTestEntity> specialistOnTestEntityList = new ArrayList<>();
        for(var it : testModel.getSpecialistModels()){
            if(!it.getLabId().equals(testModel.getLabId())){
                testRepo.deleteById(testId);
                return new ResponseEntity<>("All specialists must be from the same lab", HttpStatus.BAD_REQUEST);
            }
            Optional<LabSpecialistEntity> labSpecialistEntity = labSpecialistRepo
                    .findByFirstNameAndSecondNameAndLastNameAndLabId(
                            it.getFirstName(), it.getSecondName(),
                            it.getLastName(), it.getLabId()
                    );
            if(labSpecialistEntity.isEmpty()){
                testRepo.deleteById(testId);
                return new ResponseEntity<>("Specialist not found", HttpStatus.BAD_REQUEST);
            }

            SpecialistOnTestEntity specialistOnTestEntity = new SpecialistOnTestEntity();
            specialistOnTestEntity.setTestId(testId);
            specialistOnTestEntity.setSpecialistId(labSpecialistEntity.get().getSpecialistId());

            specialistOnTestEntityList.add(specialistOnTestEntity);
        }

        List<TestEquipmentEntity> testEquipmentEntityList = new ArrayList<>();
        for(var it : testModel.getEquipment()){
            if(!equipmentRepo.existsByEquipmentIdAndLabId(it, testModel.getLabId())){
                testRepo.deleteById(testId);
                return new ResponseEntity<>("No such equipment", HttpStatus.BAD_REQUEST);
            }
            TestEquipmentEntity testEquipmentEntity = new TestEquipmentEntity();
            testEquipmentEntity.setEquipmentId(it);
            testEquipmentEntity.setTestId(testId);
            testEquipmentEntity.setLabId(testModel.getLabId());

            testEquipmentEntityList.add(testEquipmentEntity);
        }

        specialistOnTestRepo.saveAll(specialistOnTestEntityList);

        testEquipmentRepo.saveAll(testEquipmentEntityList);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
