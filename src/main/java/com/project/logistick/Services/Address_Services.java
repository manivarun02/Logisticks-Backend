package com.project.logistick.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Address;
import com.project.logistick.Exceptions.AdressNotFound;
import com.project.logistick.Repositories.Adress_Repo;

@Service
public class Address_Services {
    
    @Autowired
    private Adress_Repo adrepo;

    // ✅ SAVE ADDRESS
    public ResponseEntity<ResponceStucture<Address>> saveadress(Address ad) {
        Address saved = adrepo.save(ad);
        ResponceStucture<Address> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.CREATED.value());
        rs.setMessage("Address saved successfully");
        rs.setData(saved);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    // ✅ FIND ADDRESS (Controller calls this)
    public ResponseEntity<ResponceStucture<Address>> findAddress(int id) {
        Optional<Address> adopt = adrepo.findById(id);
        if (adopt.isEmpty()) {
            throw new AdressNotFound();
        }
        ResponceStucture<Address> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Address found");
        rs.setData(adopt.get());
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    // Keep old method name for backward compatibility
    public ResponseEntity<ResponceStucture<Address>> findAdress(int id) {
        return findAddress(id);
    }

    // ✅ DELETE ADDRESS (returns String as expected by controller)
    public ResponseEntity<ResponceStucture<String>> deleteAddress(int id) {
        Optional<Address> adopt = adrepo.findById(id);
        if (adopt.isEmpty()) {
            throw new AdressNotFound();
        }
        adrepo.deleteById(id);
        ResponceStucture<String> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Address deleted");
        rs.setData("Deleted successfully");
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    // Keep old method for backward compatibility
    public ResponseEntity<ResponceStucture<Address>> deleteAdress(int id) {
        Optional<Address> adopt = adrepo.findById(id);
        if (adopt.isEmpty()) {
            throw new AdressNotFound();
        }
        adrepo.deleteById(id);
        ResponceStucture<Address> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Address deleted");
        rs.setData(adopt.get());
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}