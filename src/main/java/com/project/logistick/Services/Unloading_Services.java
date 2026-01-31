package com.project.logistick.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Unloading;
import com.project.logistick.Repositories.Unloading_repo;

@Service
public class Unloading_Services {

    @Autowired
    private Unloading_repo unloadingRepo;

    // ✅ GET ALL UNLOADING LOCATIONS
    public List<Unloading> getAllUnloadings() {
        return unloadingRepo.findAll();
    }

    public ResponseEntity<ResponceStucture<Unloading>> saveUnloading(Unloading unloading) {
        Unloading saved = unloadingRepo.save(unloading);
        ResponceStucture<Unloading> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.CREATED.value());
        rs.setMessage("Unloading location saved");
        rs.setData(saved);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponceStucture<Unloading>> findUnloading(Integer id) {
        Optional<Unloading> opt = unloadingRepo.findById(id);
        ResponceStucture<Unloading> rs = new ResponceStucture<>();
        
        if (opt.isPresent()) {
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Unloading found");
            rs.setData(opt.get());
            return ResponseEntity.ok(rs);
        }
        
        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Unloading not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }

    public ResponseEntity<ResponceStucture<String>> deleteUnloading(Integer id) {
        ResponceStucture<String> rs = new ResponceStucture<>();
        
        if (unloadingRepo.existsById(id)) {
            unloadingRepo.deleteById(id);
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Unloading deleted");
            rs.setData("Deleted");
            return ResponseEntity.ok(rs);
        }
        
        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Unloading not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }
}