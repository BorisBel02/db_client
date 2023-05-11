package org.nsu.db.service;

import org.nsu.db.Entity.AttributeValueEntity;
import org.nsu.db.Entity.ProductEntity;
import org.nsu.db.Entity.ProductTypeAttributeEntity;
import org.nsu.db.Entity.ProductTypeEntity;
import org.nsu.db.Exception.EntityNotFoundException;
import org.nsu.db.Model.ProductModel;
import org.nsu.db.Repo.AttributeValueRepo;
import org.nsu.db.Repo.ProductRepo;
import org.nsu.db.Repo.ProductTypeAttributeRepo;
import org.nsu.db.Repo.ProductTypeRepo;
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

    @Autowired
    public ProductService(ProductRepo productRepo, ProductTypeRepo productTypeRepo,
                          ProductTypeAttributeRepo productTypeAttributeRepo,
                          AttributeValueRepo attributeValueRepo){
        this.productRepo = productRepo;
        this.productTypeRepo = productTypeRepo;
        this.productTypeAttributeRepo = productTypeAttributeRepo;
        this.attributeValueRepo = attributeValueRepo;
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
}
