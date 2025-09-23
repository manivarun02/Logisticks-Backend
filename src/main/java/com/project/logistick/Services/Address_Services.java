package com.project.logistick.Services;
import com.project.logistick.Entitiesclasses.Address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;

import com.project.logistick.Exceptions.AdressAlreadyExistException;
import com.project.logistick.Exceptions.AdressNotFound;
import com.project.logistick.Repositories.Adress_Repo;

@Service
public class Address_Services {
	@Autowired
	private Adress_Repo adrepo;

	public ResponseEntity<ResponceStucture<Address>> saveadress(Address ad)   {
		
		  Boolean present=adrepo.existsById(ad.getId());
		  ResponceStucture<Address> rs=new ResponceStucture<Address>();

			if(present)
				{
				  throw new AdressAlreadyExistException();
				}
			else
			{   adrepo.save(ad);
				rs.setCode(HttpStatus.OK.value());
				rs.setMessage("Adress details of id "+ad.getId()+" saved");
				rs.setData(ad);
			}

			return new ResponseEntity<ResponceStucture<Address>>(rs,HttpStatus.OK );
			
		
	}

	public ResponseEntity<ResponceStucture<Address>> findAdress(int id) {
		
		Optional<Address>adopt=adrepo.findById(id);
		ResponceStucture<Address> rs=new ResponceStucture<Address>();
		if(adopt.isPresent()) {
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Adress details of id "+id+" Found");
			rs.setData(adopt.get());
			
		}
		else
		{

			throw new AdressNotFound();
		}

		return new ResponseEntity<ResponceStucture<Address>>(rs,HttpStatus.OK );
		
	}

	public ResponseEntity<ResponceStucture<Address>> deleteAdress(int id) {

		 Optional<Address>adopt=adrepo.findById(id);
			
			ResponceStucture<Address> rs=new ResponceStucture<Address>();
			if(adopt.isPresent()) {
				 adrepo.deleteById(id);
					rs.setCode(HttpStatus.OK.value());
					rs.setMessage("Deleting Address details with id "+id+" Deleted");
					rs.setData(adopt.get());
				
			}
			else
			{ 

				throw new AdressNotFound();
			}

			return new ResponseEntity<ResponceStucture<Address>>(rs,HttpStatus.OK );
		
	}

}
