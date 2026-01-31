package com.project.logistick.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Cargo;
import com.project.logistick.Repositories.Cargo_Repo;

@Service
public class Cargo_Services {
    
    @Autowired
    private Cargo_Repo carrepo;
    
    public ResponseEntity<ResponceStucture<Cargo>> saveCargo(Cargo cargo) {
        carrepo.save(cargo);
        ResponceStucture<Cargo> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Cargo details of id " + cargo.getId() + " saved");
        rs.setData(cargo);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    
    // ✅ ADDED: findCargo method (used by controller)
    public ResponseEntity<ResponceStucture<Cargo>> findCargo(Integer id) {
        Optional<Cargo> caropt = carrepo.findById(id);
        ResponceStucture<Cargo> rs = new ResponceStucture<>();
        
        if (caropt.isPresent()) {
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Cargo details of id " + id + " Found");
            rs.setData(caropt.get());
        } else {
            rs.setCode(HttpStatus.NOT_FOUND.value());
            rs.setMessage("Cargo not found for id " + id);
            rs.setData(null);
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    
    // Keep the old method for backward compatibility
    public ResponseEntity<ResponceStucture<Cargo>> trackCargo(int id) {
        return findCargo(id);
    }
    
    // ✅ ADDED: deleteCargo method (used by controller)
    public ResponseEntity<ResponceStucture<String>> deleteCargo(Integer id) {
        Optional<Cargo> caropt = carrepo.findById(id);
        ResponceStucture<String> rs = new ResponceStucture<>();
        
        if (caropt.isPresent()) {
            carrepo.deleteById(id);
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Removed Cargo with id " + id);
            rs.setData("Deleted successfully");
        } else {
            rs.setCode(HttpStatus.NOT_FOUND.value());
            rs.setMessage("Cargo not found for id " + id);
            rs.setData(null);
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
    
    // Keep the old method for backward compatibility
    public ResponseEntity<ResponceStucture<Cargo>> removeCargo(int id) {
        Optional<Cargo> caropt = carrepo.findById(id);
        ResponceStucture<Cargo> rs = new ResponceStucture<>();
        
        if (caropt.isPresent()) {
            carrepo.deleteById(id);
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Removed Cargo with id " + id);
            rs.setData(caropt.get());
        } else {
            rs.setCode(HttpStatus.NOT_FOUND.value());
            rs.setMessage("Cargo not found for id " + id);
            rs.setData(null);
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}