package com.tolgaybalci.thy.controller;

import com.tolgaybalci.thy.entity.Plane;
import com.tolgaybalci.thy.entity.ThyUser;
import com.tolgaybalci.thy.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plane")
public class PlaneController {

    @Autowired
    private PlaneRepository planeRepository;

    @GetMapping
    public ResponseEntity<Plane> getPlaneList(@RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "0") int size){
        System.out.println("page is:" + page);
        System.out.println("size is:" + size);
        Pageable pageable = PageRequest.of(page, size);
        List<Plane> planes = planeRepository.findAll(pageable).getContent();
        return new ResponseEntity(planes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Plane> addPlane(@RequestBody Plane plane) {
        planeRepository.save(plane);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

}
