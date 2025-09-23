package com.project.logistick.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Carrier;
import com.project.logistick.Exceptions.CarrierAlreadyExistException;
import com.project.logistick.Exceptions.CarrierNotFound;
import com.project.logistick.Repositories.Carrier_Repo;

@Service
public class Carrier_Services {
	@Autowired
	private Carrier_Repo crrepo;
//save carrier details
	public ResponseEntity<ResponceStucture<Carrier>> saveDetails(Carrier c) {

		 Boolean present=crrepo.existsById(c.getId());
		
		ResponceStucture<Carrier> rs=new ResponceStucture<Carrier>();
		if(present) {
			throw new CarrierAlreadyExistException();
		}
		else
		{   crrepo.save(c);
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Carrier details of id "+c.getId()+" saved");
			rs.setData(c);
		}

		return new ResponseEntity<ResponceStucture<Carrier>>(rs,HttpStatus.OK );
		
		
	}
  //find by carrier
	public ResponseEntity<ResponceStucture<Carrier>> findById(int id) {
       
		Optional<Carrier>cropt=crrepo.findById(id);
		ResponceStucture<Carrier> rs=new ResponceStucture<Carrier>();
		if(cropt.isPresent()) {
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Carrier details of id "+id+" Found");
			rs.setData(cropt.get());
			
		}
		else
		{
			throw new CarrierNotFound();
		}

		return new ResponseEntity<ResponceStucture<Carrier>>(rs,HttpStatus.OK );
		
		
		
	}
 
	//delete carrier
	public ResponseEntity<ResponceStucture<Carrier>> deleteId(int id) {

		 Optional<Carrier>cropt=crrepo.findById(id);
			
			ResponceStucture<Carrier> rs=new ResponceStucture<Carrier>();
			if(cropt.isPresent()) {
				 crrepo.deleteById(id);
					rs.setCode(HttpStatus.OK.value());
					rs.setMessage("Deleting Address details with id "+id+" Deleted");
					rs.setData(cropt.get());
				
			}
			else
			{ 
			
				throw new CarrierNotFound();
			}

			return new ResponseEntity<ResponceStucture<Carrier>>(HttpStatus.OK );
		
		
		
	}

}
