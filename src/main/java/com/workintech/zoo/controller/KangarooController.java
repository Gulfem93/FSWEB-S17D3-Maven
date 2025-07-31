package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/kangaroos")
public class KangarooController {

    private final Map<Integer, Kangaroo> kangaroos;

    public KangarooController() {
        this.kangaroos = new HashMap<>();
    }

    // [GET] /workintech/kangaroos
    @GetMapping
    public ResponseEntity<List<Kangaroo>> getAll() {
        return new ResponseEntity<>(new ArrayList<>(kangaroos.values()), HttpStatus.OK);
    }

        // [POST] /workintech/kangaroos
    @PostMapping
    public ResponseEntity<Kangaroo> addKangaroo(@RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);
        return new ResponseEntity<>(kangaroo, HttpStatus.CREATED);
    }

    // [PUT] /workintech/kangaroos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Kangaroo> updateKangaroo(@PathVariable int id, @RequestBody Kangaroo updatedKangaroo) {
        if (!kangaroos.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedKangaroo.setId(id);
        kangaroos.put(id, updatedKangaroo);
        return new ResponseEntity<>(updatedKangaroo, HttpStatus.OK);
    }

    // [DELETE] /workintech/kangaroos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKangaroo(@PathVariable int id) {
        if (!kangaroos.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        kangaroos.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kangaroo> getById(@PathVariable int id) {
        Kangaroo kangaroo = kangaroos.get(id);
        if (kangaroo != null) {
            return new ResponseEntity<>(kangaroo, HttpStatus.OK);
        }
        throw new ZooException("Kangaroo with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

}
