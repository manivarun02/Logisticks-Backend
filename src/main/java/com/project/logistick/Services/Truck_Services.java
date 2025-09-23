package com.project.logistick.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Carrier;
import com.project.logistick.Entitiesclasses.Truck;
import com.project.logistick.Exceptions.TruckAlreadyExistException;
import com.project.logistick.Exceptions.TruckAndCArrierNotFound;
import com.project.logistick.Exceptions.TruckNotFound;
import com.project.logistick.Repositories.Carrier_Repo;
import com.project.logistick.Repositories.Truck_Repo;

@Service
public class Truck_Services {
	@Autowired
	private Truck_Repo trepo;

	// saving Details
	public ResponseEntity<ResponceStucture<Truck>> saveDetails(Truck t) {

		Boolean present = trepo.existsById(t.getId());

		ResponceStucture<Truck> rs = new ResponceStucture<Truck>();
		if (present) {

			throw new TruckAlreadyExistException();

		} else {
			trepo.save(t);
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Truck details of id " + t.getId() + " saved");
			rs.setData(t);
		}

		return new ResponseEntity<ResponceStucture<Truck>>(rs, HttpStatus.OK);
	}

	// finding details
	public ResponseEntity<ResponceStucture<Truck>> findById(int id) {
		Optional<Truck> tropt = trepo.findById(id);

		ResponceStucture<Truck> rs = new ResponceStucture<Truck>();
		if (tropt.isPresent()) {
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Truck details of id " + id + " Found");
			rs.setData(tropt.get());

		} else {

			throw new TruckNotFound();
		}

		return new ResponseEntity<ResponceStucture<Truck>>(rs, HttpStatus.OK);

	}

	// delete truck
	public ResponseEntity<ResponceStucture<Truck>> deleteTruck(int id) {

		Optional<Truck> topt = trepo.findById(id);

		ResponceStucture<Truck> rs = new ResponceStucture<Truck>();
		if (topt.isPresent()) {
			trepo.deleteById(id);
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Deleting Address details with id " + id + " Deleted");
			rs.setData(topt.get());

		} else {
			throw new TruckNotFound();
		}
		return new ResponseEntity<ResponceStucture<Truck>>(rs, HttpStatus.OK);

	}

	//update truck
	Truck truck=new Truck();
	@Autowired
	Carrier_Repo crrepo;

	public ResponseEntity<ResponceStucture<Truck>> updateByIds(int id) {
		if(trepo.existsById(id) && crrepo.existsById(id))
		{
			truck=trepo.findById(id).get();
			Carrier cr = crrepo.findById(id).get();
		truck.setCarrier(cr);
			truck = trepo.save(truck);		} 
		else {
			throw new TruckAndCArrierNotFound();}
				ResponceStucture<Truck> rs = new ResponceStucture<Truck>();

		rs.setCode(HttpStatus.CREATED.value());
		rs.setMessage("truck and carrier updated successfully");
		rs.setData(truck);

		return new ResponseEntity<ResponceStucture<Truck>>(rs, HttpStatus.OK);

			
		}
		
	}


