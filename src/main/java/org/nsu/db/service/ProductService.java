package org.nsu.db.service;

import org.aspectj.weaver.loadtime.Options;
import org.nsu.db.Entity.*;
import org.nsu.db.Exception.EntityNotFoundException;
import org.nsu.db.Model.ProductCycleStepModel;
import org.nsu.db.Model.ProductModel;
import org.nsu.db.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepo productRepo;
    ProductTypeRepo productTypeRepo;
    ProductTypeAttributeRepo productTypeAttributeRepo;
    AttributeValueRepo attributeValueRepo;
    ProductCycleRepo productCycleRepo;
    SectorRepo sectorRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductTypeRepo productTypeRepo,
                          ProductTypeAttributeRepo productTypeAttributeRepo,
                          AttributeValueRepo attributeValueRepo,
                          ProductCycleRepo productCycleRepo,
                          SectorRepo sectorRepo){
        this.productRepo = productRepo;
        this.productTypeRepo = productTypeRepo;
        this.productTypeAttributeRepo = productTypeAttributeRepo;
        this.attributeValueRepo = attributeValueRepo;
        this.productCycleRepo = productCycleRepo;
        this.sectorRepo = sectorRepo;
    }

    public ResponseEntity<?> get_product(Optional<String> productName){
        if(productName.isEmpty()){
            List<ProductModel> productModelList = new ArrayList<>();
            List<ProductEntity> productEntityList = productRepo.findAll();
            for(var it : productEntityList){
                productModelList.add(makeModel(it));
            }
            return new ResponseEntity<>(productModelList, HttpStatus.OK);
        }
        else{
            Optional<ProductEntity> productEntity = productRepo.findByName(productName.get());
            if(productEntity.isEmpty()){
                throw new EntityNotFoundException("Product with name " + productName + "not found");
            }
            return new ResponseEntity<>(makeModel(productEntity.get()), HttpStatus.OK);
        }
    }

    @Transactional
    public ResponseEntity<?> add_product(ProductModel productModel){
        ProductEntity productEntity = new ProductEntity();

        Optional<ProductTypeEntity> productTypeEntity =  productTypeRepo.findByProductTypeName(productModel.getProductTypeName());
        if(productTypeEntity.isEmpty()){
            return new ResponseEntity<>("Invalid product type: " + productModel.getProductName(), HttpStatus.BAD_REQUEST);
        }

        productEntity.setProductTypeId(productTypeEntity.get().getProductTypeId());
        productEntity.setName(productModel.getProductName());
        productEntity.setWorkshopId(productModel.getWorkshopId());

        int productId = productRepo.save(productEntity).getProductId();


        List<AttributeValueEntity> productAttributeValueEntityList = new ArrayList<>();

        for(var it : productModel.getAttributes()){
            Optional<ProductTypeAttributeEntity> productTypeAttributeEntity =
                    productTypeAttributeRepo.findByProductTypeIdAndAttributeName(
                            productTypeEntity.get().getProductTypeId(),
                            it.getFirst());
            if(productTypeAttributeEntity.isEmpty()) {
                productRepo.deleteById(productId);
                return new ResponseEntity<>("Invalid attribute: "
                        + it.getFirst() + " for productType "
                        + productTypeEntity.get().getProductTypeName(),
                        HttpStatus.BAD_REQUEST);
            }
            AttributeValueEntity attributeValueEntity = new AttributeValueEntity();
            attributeValueEntity.setAttributeId(productTypeAttributeEntity.get().getAttributeId());
            attributeValueEntity.setProductId(productId);

            productAttributeValueEntityList.add(attributeValueEntity);
        }

        attributeValueRepo.saveAll(productAttributeValueEntityList);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ProductModel makeModel(ProductEntity productEntity){
        Optional<ProductTypeEntity> productTypeEntity = productTypeRepo.findById(productEntity.getProductTypeId());
        if(productTypeEntity.isEmpty()){
            throw new EntityNotFoundException("Product type with id=" + productEntity.getProductTypeId() + " not found");
        }

        List<AttributeValueEntity> attributeValueEntityList = attributeValueRepo
                .findAllByProductId(productEntity.getProductId());

        ProductModel productModel = new ProductModel();
        productModel.setProductTypeName(productTypeEntity.get().getProductTypeName());
        productModel.setProductName(productEntity.getName());
        productModel.setWorkshopId(productEntity.getWorkshopId());

        for(var it : attributeValueEntityList){
            productModel.getAttributes().add(Pair.of(
                    productTypeAttributeRepo.findById(it.getAttributeId()).get().getAttributeName(),
                    it.getValue()
            ));
        }

        return productModel;
    }

    @Transactional
    public ResponseEntity<?> add_product_cycle(List<ProductCycleStepModel> productCycleStepModelList,
                                               String productName){
        Optional<ProductEntity> productEntity = productRepo.findByName(productName);
        if(productEntity.isEmpty()){
            return new ResponseEntity<>("Product " + productName + " does not exists", HttpStatus.BAD_REQUEST);
        }

        if(productCycleRepo.existsByProductId(productEntity.get().getProductId())){
            return new ResponseEntity<>("Product cycle for " + productName
                    + " already exist",
                    HttpStatus.BAD_REQUEST);
        }

        List<ProductCycleEntity> productCycleEntityList = new ArrayList<>();
        Integer workshopId = productEntity.get().getWorkshopId();
        int step = 1;
        for(var it : productCycleStepModelList){
            if(!sectorRepo.existsById(it.getSectorId())){
                return new ResponseEntity<>("SectorId: " + it.getSectorId()
                    + " does not exist", HttpStatus.BAD_REQUEST);
            }

            if(workshopId != sectorRepo.findById(it.getSectorId()).get().getWorkshopId()){
                return new ResponseEntity<>("Product can not manufacture in several workshops",
                        HttpStatus.BAD_REQUEST);
            }
            ProductCycleEntity productCycleEntity = new ProductCycleEntity();
            productCycleEntity.setProductId(productEntity.get().getProductId());
            productCycleEntity.setSectorId(it.getSectorId());
            productCycleEntity.setStep(step);
            ++step;
            productCycleEntityList.add(productCycleEntity);
        }
        productCycleRepo.saveAll(productCycleEntityList);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> delete_product_cycle(String productName){
        Optional<ProductEntity> productEntity = productRepo.findByName(productName);
        if(productEntity.isEmpty()){
            return new ResponseEntity<>("Product " + productName + " does not exists",
                    HttpStatus.BAD_REQUEST);
        }
        productCycleRepo.deleteAllByProductId(productEntity.get().getProductId());

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
