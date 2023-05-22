package org.nsu.db.Controller;

import org.nsu.db.Exception.EntityNotFoundException;
import org.nsu.db.Model.ProductCycleStepModel;
import org.nsu.db.Model.ProductModel;
import org.nsu.db.Repo.ProductRepo;
import org.nsu.db.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;
    ProductRepo productRepo;

    @Autowired
    public  ProductController(ProductService productService, ProductRepo productRepo){
        this.productService = productService;
        this.productRepo = productRepo;
    }

    @GetMapping("/get/")
    public ResponseEntity<?> get_product(@RequestParam(required = false)Optional<String> product_name){
        try {
            return productService.get_product(product_name);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_by_type")
    public ResponseEntity<?> get_by_type(@RequestParam(name = "type") String type){
        return new ResponseEntity<>(productRepo.findByProductTypeName(type), HttpStatus.OK);
    }
    @GetMapping("/get_by_type_and_workshop_id")
    public ResponseEntity<?> get_by_type_and_workshop_id(@RequestParam(name = "type") String type,
                                                         @RequestParam(name = "workshop_id") Integer workshopId){
        return new ResponseEntity<>(productRepo.findByProductTypeNameAndWorkshop(type, workshopId), HttpStatus.OK);
    }

    @GetMapping("/get_manufactured_by_period")
    public ResponseEntity<?> get_manufactured_by_period(@RequestParam(name = "date_min") Date date_min,
                                                        @RequestParam(name = "date_max") Date date_max){
        return new ResponseEntity<>(productRepo.findByTime(date_min, date_max), HttpStatus.OK);
    }
    @GetMapping("/get_manufactured_by_period_and_workshop_id")
    public ResponseEntity<?> get_manufactured_by_period_and_workshop_id(@RequestParam(name = "date_min") Date date_min,
                                 @RequestParam(name = "date_max") Date date_max,
                                 @RequestParam(name = "workshop_id") Integer workshop_id){
        return new ResponseEntity<>(productRepo.findByTimeAndWorkshop(date_min, date_max, workshop_id), HttpStatus.OK);
    }
    @GetMapping("/get_manufactured_by_period_and_sector_id")
    public ResponseEntity<?> get_manufactured_by_period_and_sector_id(@RequestParam(name = "date_min") Date date_min,
                                 @RequestParam(name = "date_max") Date date_max,
                                 @RequestParam(name = "sector_id") Integer sector_id){
        return new ResponseEntity<>(productRepo.findByTimeAndWorkshop(date_min, date_max, sector_id), HttpStatus.OK);
    }
    @GetMapping("/get_steps_by_product_name")
    public ResponseEntity<?> get_steps_by_product_name(@RequestParam String name){
        return new ResponseEntity<>(productRepo.findProductStepsByProductName(name), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> add_product(@RequestBody ProductModel productModel){
        return productService.add_product(productModel);
    }

    @PostMapping("add/product_cycle")
    public ResponseEntity<?> add_product_cycle(@RequestBody List<ProductCycleStepModel> productCycleStepModelList
            , @RequestParam String productName){
        return productService.add_product_cycle(productCycleStepModelList, productName);
    }


}
