package com.project.logistick.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Carrier;
import com.project.logistick.Exceptions.CarrierNotFound;
import com.project.logistick.Repositories.Carrier_Repo;

@Service
public class Carrier_Services {

    @Autowired
    private Carrier_Repo crrepo;

    // ✅ SAVE CARRIER
    public ResponseEntity<ResponceStucture<Carrier>> saveCarrier(Carrier c) {

        Carrier saved = crrepo.save(c);

        ResponceStucture<Carrier> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.CREATED.value());
        rs.setMessage("Carrier saved successfully");
        rs.setData(saved);

        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponceStucture<Carrier>> saveDetails(Carrier c) {
        return saveCarrier(c);
    }

    // ✅ LIST ALL CARRIERS (FIXES CONTROLLER ERROR)
    public List<Carrier> getAllCarriers() {
        return crrepo.findAll();
    }

    // ✅ FIND CARRIER
    public ResponseEntity<ResponceStucture<Carrier>> findCarrier(int id) {

        Carrier carrier = crrepo.findById(id)
                .orElseThrow(CarrierNotFound::new);

        ResponceStucture<Carrier> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Carrier found");
        rs.setData(carrier);

        return ResponseEntity.ok(rs);
    }

    // ✅ DELETE CARRIER
    public ResponseEntity<ResponceStucture<String>> deleteCarrier(int id) {

        Carrier carrier = crrepo.findById(id)
                .orElseThrow(CarrierNotFound::new);

        crrepo.delete(carrier);

        ResponceStucture<String> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Carrier deleted");
        rs.setData("Deleted");

        return ResponseEntity.ok(rs);
    }
}
