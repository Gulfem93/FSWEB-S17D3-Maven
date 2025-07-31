package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/koalas")
public class KoalaController {

    private final Map<Integer, Koala> koalas;

    public KoalaController() {
        this.koalas = new HashMap<>();
    }

    // [GET] /workintech/koalas
    @GetMapping
    public ResponseEntity<List<Koala>> getAll() {
        return new ResponseEntity<>(new ArrayList<>(koalas.values()), HttpStatus.OK);
    }

    // [GET] /workintech/koalas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Koala> getById(@PathVariable int id) {
        Koala koala = koalas.get(id);
        if (koala != null) {
            return new ResponseEntity<>(koala, HttpStatus.OK);
        }
        throw new ZooException("Koala with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    // [POST] /workintech/koalas
    @PostMapping
    public ResponseEntity<Koala> addKoala(@RequestBody Koala koala) {
        if (koalas.containsKey(koala.getId())) {
            throw new ZooException("Koala with id " + koala.getId() + " already exists", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        return new ResponseEntity<>(koala, HttpStatus.CREATED);
    }

    // [PUT] /workintech/koalas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Koala> updateKoala(@PathVariable int id, @RequestBody Koala updatedKoala) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        updatedKoala.setId(id);
        koalas.put(id, updatedKoala);
        return new ResponseEntity<>(updatedKoala, HttpStatus.OK);
    }

    // [DELETE] /workintech/koalas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKoala(@PathVariable int id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        koalas.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
