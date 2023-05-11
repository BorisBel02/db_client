package org.nsu.db.service;

import org.nsu.db.Entity.*;
import org.nsu.db.Exception.InvalidAttributeException;
import org.nsu.db.Model.StaffModel;
import org.nsu.db.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    StaffRepo staffRepo;
    OccupationRepo occupationRepo;
    OccupationAttributeRepo occupationAttributeRepo;
    OccupationAttributeValueRepo occupationAttributeValueRepo;
    MastersRepo mastersRepo;
    BrigadeRepo brigadeRepo;
    WorkshopRepo workshopRepo;
    WorkerRepo workerRepo;
    EngineerRepo engineerRepo;
    SectorRepo sectorRepo;
    @Autowired
    public StaffService(StaffRepo staffRepo, OccupationRepo occupationRepo,
                        OccupationAttributeRepo occupationAttributeRepo,
                        OccupationAttributeValueRepo occupationAttributeValueRepo,
                        MastersRepo mastersRepo, BrigadeRepo brigadeRepo,
                        WorkshopRepo workshopRepo, WorkerRepo workerRepo,
                        EngineerRepo engineerRepo, SectorRepo sectorRepo){
        this.staffRepo = staffRepo;
        this.occupationRepo = occupationRepo;
        this.occupationAttributeRepo = occupationAttributeRepo;
        this.occupationAttributeValueRepo = occupationAttributeValueRepo;
        this.mastersRepo = mastersRepo;
        this.brigadeRepo = brigadeRepo;
        this.workshopRepo = workshopRepo;
        this.workerRepo = workerRepo;
        this.engineerRepo = engineerRepo;
        this.sectorRepo = sectorRepo;
    }


    public ResponseEntity<?> add_worker(StaffModel staffModel, Integer brigadeId){
        if(!brigadeRepo.existsById(brigadeId)){
            return new ResponseEntity<>("Brigade with id: " + brigadeId + " does not exist", HttpStatus.BAD_REQUEST);
        }
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setFirstName(staffModel.getFirstName());
        staffEntity.setSecondName(staffModel.getSecondName());
        staffEntity.setLastName(staffModel.getLastName());

        Optional<OccupationEntity> occupation = occupationRepo.findByOccupationName(staffModel.getOccupationName());
        if(occupation.isEmpty()){
            return new ResponseEntity<>("Invalid Occupation Name: " + staffModel.getOccupationName(), HttpStatus.BAD_REQUEST);
        }
        staffEntity.setOccupationId(occupation.get().getOccupationId());

        if(!workshopRepo.existsByWorkshopId(staffModel.getWorkshopId())){
            return new ResponseEntity<>("Invalid WorkshopId: " + staffModel.getWorkshopId(), HttpStatus.BAD_REQUEST);
        }
        staffEntity.setWorkshopId(staffModel.getWorkshopId());

        int personId = staffRepo.save(staffEntity).getPersonId();
        List<OccupationAttributeValueEntity> attributes;
        try{
            attributes = attrList(staffModel.getOccupationAttributes(), staffEntity.getOccupationId(), personId);
        } catch (InvalidAttributeException e){
            staffRepo.deleteById(personId);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        WorkerEntity workerEntity = new WorkerEntity();
        workerEntity.setWorkerId(personId);
        workerEntity.setBrigadeId(brigadeId);
        workerRepo.save(workerEntity);

        occupationAttributeValueRepo.saveAll(attributes);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<?> add_engineer(StaffModel staffModel, Optional<Integer> supervisorId){
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setFirstName(staffModel.getFirstName());
        staffEntity.setSecondName(staffModel.getSecondName());
        staffEntity.setLastName(staffModel.getLastName());

        Optional<OccupationEntity> occupation = occupationRepo.findByOccupationName(staffModel.getOccupationName());
        if(occupation.isEmpty()){
            return new ResponseEntity<>("Invalid Occupation Name: " + staffModel.getOccupationName(), HttpStatus.BAD_REQUEST);
        }
        staffEntity.setOccupationId(occupation.get().getOccupationId());

        if(!workshopRepo.existsByWorkshopId(staffModel.getWorkshopId())){
            return new ResponseEntity<>("Invalid WorkshopId: " + staffModel.getWorkshopId(), HttpStatus.BAD_REQUEST);
        }
        staffEntity.setWorkshopId(staffModel.getWorkshopId());

        
        int personId = staffRepo.save(staffEntity).getPersonId();
        List<OccupationAttributeValueEntity> attributes;
        try{
            attributes = attrList(staffModel.getOccupationAttributes(), staffEntity.getOccupationId(), personId);
        } catch (InvalidAttributeException e){
            staffRepo.deleteById(personId);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        MastersEntity mastersEntity = new MastersEntity();
        if(supervisorId.isPresent()){
            Optional<SectorEntity> sectorEntity = sectorRepo.findBySectorSupervisor(supervisorId.get());
            if(sectorEntity.isPresent()){
                Integer workshopId = sectorEntity.get().getWorkshopId();
                if(!workshopId.equals(staffModel.getWorkshopId())){
                    staffRepo.deleteById(personId);
                    return new ResponseEntity<>("Employee and master WorkshopIds does not match", HttpStatus.BAD_REQUEST);
                }
                else{
                    mastersEntity.setMasterId(personId);
                    mastersEntity.setSupervisorId(supervisorId.get());
                    mastersRepo.save(mastersEntity);
                }
            }
            else{
                staffRepo.deleteById(personId);
                return new ResponseEntity<>("Invalid supervisorId", HttpStatus.BAD_REQUEST);
            }
        }
        EngineerEntity engineerEntity = new EngineerEntity();
        engineerEntity.setEngineerId(personId);
        engineerRepo.save(engineerEntity);

        occupationAttributeValueRepo.saveAll(attributes);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    public List<StaffModel> convertStaffEntityList(List<StaffEntity> entities){
        List<StaffModel> models = new ArrayList<>();
        for(var it : entities){
            models.add(staffEntitytoStaffModel(it));
        }
        return models;
    }
    public StaffModel staffEntitytoStaffModel(StaffEntity entity){
        int personId = entity.getPersonId();
        Optional<OccupationEntity> occupationEntity = occupationRepo.findById(entity.getOccupationId());
        List<OccupationAttributeValueEntity> occupationAttributeValueEntityList = occupationAttributeValueRepo.findAllByPersonId(personId);
        List<Pair<OccupationAttributeEntity, OccupationAttributeValueEntity>> attributesEntities = new ArrayList<>();
        for(var it : occupationAttributeValueEntityList){
            Optional<OccupationAttributeEntity> attributeEntity = occupationAttributeRepo.findById(it.getOccupationAttributeId());
            attributesEntities.add(Pair.of(attributeEntity.orElse(null), it));
        }

        return new StaffModel(entity, occupationEntity.orElse(null), attributesEntities);
    }
    
    public List<OccupationAttributeValueEntity> attrList(List<Pair<String, String>> nameValueList,
                                                         Integer occupationId,
                                                         int personId){
        List<OccupationAttributeValueEntity> attributes = new ArrayList<>();
        for(var it : nameValueList){
            Optional<OccupationAttributeEntity> occupationAttribute = occupationAttributeRepo
                    .findByOccupationAttributeNameAndOccupationId(it.getFirst(), occupationId);
            if(occupationAttribute.isEmpty()){
                throw new InvalidAttributeException("Attribute " + it.getFirst()
                        + " does not exists for given occupation.");
            }

            OccupationAttributeValueEntity occupationAttributeValueEntity = new OccupationAttributeValueEntity();
            occupationAttributeValueEntity
                    .setOccupationAttributeId(occupationAttribute.get().getOccupationAttributeId());
            occupationAttributeValueEntity.setPersonId(personId);
            occupationAttributeValueEntity.setOccupationAttributeValue(it.getSecond());

            attributes.add(occupationAttributeValueEntity);
        }
        return attributes;
    }
}
