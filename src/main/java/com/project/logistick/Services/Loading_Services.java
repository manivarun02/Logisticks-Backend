package com.project.logistick.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Loading;
import com.project.logistick.Exceptions.LoadAdressNotFound;
import com.project.logistick.Exceptions.LoadAlreadyExist;
import com.project.logistick.Repositories.Loading_Repo;

@Service
public class Loading_Services {
	@Autowired
	private Loading_Repo loadrepo;

	public ResponseEntity<ResponceStucture<Loading>> pickParcel(Loading load) {
		Boolean present = loadrepo.existsById(load.getId());

		ResponceStucture<Loading> rs = new ResponceStucture<Loading>();
		if (present) {

			throw new LoadAlreadyExist();

		} else {
			loadrepo.save(load);
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Loading details of id " + load.getId() + " saved");
			rs.setData(load);
		}

		return new ResponseEntity<ResponceStucture<Loading>>(rs, HttpStatus.OK);
		
	}

	public ResponseEntity<ResponceStucture<Loading>> findLocation(int id) {
		
		Optional<Loading>loadopt=loadrepo.findById(id);
		ResponceStucture<Loading> rs=new ResponceStucture<Loading>();
		if(loadopt.isPresent()) {
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Loading Adress details of id "+id+" Found");
			rs.setData(loadopt.get());
			
		}
		else
		{

			throw new LoadAdressNotFound();
		}

		return new ResponseEntity<ResponceStucture<Loading>>(rs,HttpStatus.OK );
		
		
	}

	public ResponseEntity<ResponceStucture<Loading>> deleteLocation(int id) {

		 Optional<Loading>loadopt=loadrepo.findById(id);
			
			ResponceStucture<Loading> rs=new ResponceStucture<Loading>();
			if(loadopt.isPresent()) {
				 loadrepo.deleteById(id);
					rs.setCode(HttpStatus.OK.value());
					rs.setMessage("Deleting load Address details with id "+id+" Deleted");
					rs.setData(loadopt.get());
				
			}
			else
			{ 

				throw new LoadAdressNotFound();
			}

			return new ResponseEntity<ResponceStucture<Loading>>(rs,HttpStatus.OK );
		
		
		
	}
	

}
