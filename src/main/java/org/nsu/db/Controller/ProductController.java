package org.nsu.db.Controller;

import org.nsu.db.Exception.EntityNotFoundException;
import org.nsu.db.Model.ProductCycleStepModel;
import org.nsu.db.Model.ProductModel;
import org.nsu.db.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;


    @GetMapping("/get/{product_name}")
    public ResponseEntity<?> get_product(@PathVariable(required = false)Optional<String> product_name){
        try {
            return productService.get_product(product_name);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add_product(@RequestBody ProductModel productModel){
        return productService.add_product(productModel);
    }

    @PostMapping
    public ResponseEntity<?> add_product_cycle(@RequestBody List<ProductCycleStepModel> productCycleStepModelList
            , @RequestParam String productName){
        return productService.add_product_cycle(productCycleStepModelList, productName);
    }
}
