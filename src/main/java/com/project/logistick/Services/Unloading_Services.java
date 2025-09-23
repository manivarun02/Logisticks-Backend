package com.project.logistick.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Address;

import com.project.logistick.Entitiesclasses.Unloading;
import com.project.logistick.Exceptions.AdressNotFound;
import com.project.logistick.Exceptions.UnloadAdressNotFOund;
import com.project.logistick.Repositories.Adress_Repo;
import com.project.logistick.Repositories.Unloading_repo;

@Service
public class Unloading_Services {
	@Autowired
	private Adress_Repo adressrepo;
	@Autowired
	private Unloading_repo unloadrepo;
	Address ad=new Address();
	public ResponseEntity<ResponceStucture<Unloading>> addDeliverAdress(Unloading unload) {
//		
//       Optional<Address>adopt=adress.findById(unload.getId());
		Boolean present=adressrepo.existsById(unload.getId());
		ResponceStucture<Unloading> rs=new ResponceStucture<Unloading>();
		
		if(present) {
			unloadrepo.save(ad);
			
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Delivery Adress details of id Found");
			rs.setData(unload);	
		   }
		else
		{

			throw new AdressNotFound();
		}
		return new ResponseEntity<ResponceStucture<Unloading>>(HttpStatus.OK );
		
		
	}

	public ResponseEntity<ResponceStucture<Address>> findDelivery(int id) {
		
		 adressrepo.findById(id).get();
		
		ResponceStucture<Address> rs=new ResponceStucture<Address>();
		if(adressrepo.existsById(id)) {
			rs.setCode(HttpStatus.OK.value());
			rs.setData(ad);

			
		}
		else
		{

			throw new AdressNotFound();
		}
		return new ResponseEntity<ResponceStucture<Address>>(HttpStatus.OK );
		
	}

	public ResponseEntity<ResponceStucture<Unloading>> cancleDetails(int id) {

		 Optional<Unloading>loadopt=unloadrepo.findById(id);
			
			ResponceStucture<Unloading> rs=new ResponceStucture<Unloading>();
			if(loadopt.isPresent()) {
				 unloadrepo.deleteById(id);
					rs.setCode(HttpStatus.OK.value());
					rs.setMessage("Deleting unloading address details with id "+id+" Deleted");
					rs.setData(loadopt.get());
				
			}
			else
			{ 

				throw new UnloadAdressNotFOund();
			}

			return new ResponseEntity<ResponceStucture<Unloading>>(rs,HttpStatus.OK );
				
	}

}
