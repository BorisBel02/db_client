package org.nsu.db.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public ResponseEntity<?> main_page(){
        return new ResponseEntity<>("Welcome to the manufacture database client", HttpStatus.OK);
    }
}
