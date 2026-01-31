package com.project.logistick.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Loading;
import com.project.logistick.Repositories.Loading_Repo;

@Service
public class Loading_Services {

    @Autowired
    private Loading_Repo loadingRepo;

    // ✅ GET ALL LOADING LOCATIONS
    public List<Loading> getAllLoadings() {
        return loadingRepo.findAll();
    }

    public ResponseEntity<ResponceStucture<Loading>> saveLoading(Loading loading) {
        Loading saved = loadingRepo.save(loading);
        ResponceStucture<Loading> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.CREATED.value());
        rs.setMessage("Loading location saved");
        rs.setData(saved);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponceStucture<Loading>> findLoading(Integer id) {
        Optional<Loading> opt = loadingRepo.findById(id);
        ResponceStucture<Loading> rs = new ResponceStucture<>();
        
        if (opt.isPresent()) {
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Loading found");
            rs.setData(opt.get());
            return ResponseEntity.ok(rs);
        }
        
        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Loading not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }

    public ResponseEntity<ResponceStucture<String>> deleteLoading(Integer id) {
        ResponceStucture<String> rs = new ResponceStucture<>();
        
        if (loadingRepo.existsById(id)) {
            loadingRepo.deleteById(id);
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Loading deleted");
            rs.setData("Deleted");
            return ResponseEntity.ok(rs);
        }
        
        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Loading not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }
}