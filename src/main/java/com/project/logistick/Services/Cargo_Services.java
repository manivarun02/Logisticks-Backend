package com.project.logistick.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Cargo;
import com.project.logistick.Exceptions.CargoNotFound;
import com.project.logistick.Exceptions.CarrierAlreadyExistException;
import com.project.logistick.Repositories.Cargo_Repo;

@Service
public class Cargo_Services {
	@Autowired
	private Cargo_Repo carrepo;

	public ResponseEntity<ResponceStucture<Cargo>> saveCargo(Cargo cargo) {
		  Boolean present=carrepo.existsById(cargo.getId());
		  ResponceStucture<Cargo> rs=new ResponceStucture<Cargo>();

			if(present)
				{
				  throw new CarrierAlreadyExistException();
				}
			else
			{   carrepo.save(cargo);
				rs.setCode(HttpStatus.OK.value());
				rs.setMessage("Cargo details of id "+cargo.getId()+" saved");
				rs.setData(cargo);
			}
			return new ResponseEntity<ResponceStucture<Cargo>>(rs,HttpStatus.OK );

	}

	public ResponseEntity<ResponceStucture<Cargo>> trackCargo(int id) {
		
		Optional<Cargo>caropt=carrepo.findById(id);
		ResponceStucture<Cargo> rs=new ResponceStucture<Cargo>();
		if(caropt.isPresent()) {
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Cargo details of id "+id+" Found");
			rs.setData(caropt.get());
			
		}
		else
		{

			throw new CargoNotFound();
		}

		return new ResponseEntity<ResponceStucture<Cargo>>(rs,HttpStatus.OK );
		
		
	}

	public ResponseEntity<ResponceStucture<Cargo>> removeCargo(int id) {
		 Optional<Cargo>caropt=carrepo.findById(id);
			
			ResponceStucture<Cargo> rs=new ResponceStucture<Cargo>();
			if(caropt.isPresent()) {
				 carrepo.deleteById(id);
					rs.setCode(HttpStatus.OK.value());
					rs.setMessage("Removing Cargo details with id "+id);
					rs.setData(caropt.get());
				
			}
			else
			{ 

				throw new CargoNotFound();
			}

			return new ResponseEntity<ResponceStucture<Cargo>>(rs,HttpStatus.OK );
		
	}

}
