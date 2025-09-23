package com.project.logistick.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Carrier;
import com.project.logistick.Entitiesclasses.Driver_Class;
import com.project.logistick.Entitiesclasses.Truck;
import com.project.logistick.Exceptions.DriverAlreadyExistException;
import com.project.logistick.Exceptions.DriverNotFound;
import com.project.logistick.Exceptions.DriverTruckCarrierNotFound;
import com.project.logistick.Repositories.Carrier_Repo;
import com.project.logistick.Repositories.Driver_Repo;
import com.project.logistick.Repositories.Truck_Repo;

@Service
public class Driver_Services {
	@Autowired
	private Driver_Repo drrepo;

	//save driver details
	public ResponseEntity<ResponceStucture<Driver_Class>> saveDetails(Driver_Class d) {
	
		 Boolean present=drrepo.existsById(d.getId());
			
			ResponceStucture<Driver_Class> rs=new ResponceStucture<Driver_Class>();
			if(present) {
				throw new DriverAlreadyExistException();
				
			}
			else
			{  drrepo.save(d);
				rs.setCode(HttpStatus.OK.value());
				rs.setMessage("Truck details of id "+d.getId()+" saved");
				rs.setData(d);
			}

			return new ResponseEntity<ResponceStucture<Driver_Class>>(rs,HttpStatus.OK );
			
		
	}
	//finding driver

	public ResponseEntity<ResponceStucture<Driver_Class>> findDriver(int id) {
		
			Optional<Driver_Class>dropt=drrepo.findById(id);
					
					ResponceStucture<Driver_Class> rs=new ResponceStucture<Driver_Class>();
					if(dropt.isPresent()) {
						rs.setCode(HttpStatus.OK.value());
						rs.setMessage("Truck details of id "+id+" Found");
						rs.setData(dropt.get());
						
					}
					else
					{
						throw new DriverNotFound();
					}

					return new ResponseEntity<ResponceStucture<Driver_Class>>(rs,HttpStatus.OK );
	
		
	}

	//delete driver
	public ResponseEntity<ResponceStucture<Driver_Class>> deleteDriver(int id) {
	
		 Optional<Driver_Class>dropt=drrepo.findById(id);
			
			ResponceStucture<Driver_Class> rs=new ResponceStucture<Driver_Class>();
			if(dropt.isPresent()) {
				 drrepo.deleteById(id);
					rs.setCode(HttpStatus.OK.value());
					rs.setMessage("Deleting Address details with id "+id+" Deleted");
					rs.setData(dropt.get());
				
			}
			else
			{ 
				throw new DriverNotFound();
			}

			return new ResponseEntity<ResponceStucture<Driver_Class>>(rs,HttpStatus.OK );
		
			
		
	}
	
	//update driver

	@Autowired
	private Carrier_Repo crrepo;
	@Autowired Truck_Repo trepo;
	
	
	Driver_Class dr=new Driver_Class();
	

	public ResponseEntity<ResponceStucture<Driver_Class>> updateDetails(int id) {
		if(drrepo.existsById(id) && trepo.existsById(id)&& crrepo.existsById(id))
		{
			
			dr=drrepo.findById(id).get();
			
			Truck truck=trepo.findById(id).get();
			dr.setTruck(truck);
			
			Carrier cr= crrepo.findById(id).get();
			dr.setCarrier(cr);
			
			dr=drrepo.save(dr);
			}
			else
			{
				throw new DriverTruckCarrierNotFound();
			}
			
			ResponceStucture<Driver_Class> rs = new ResponceStucture<Driver_Class>();

			rs.setCode(HttpStatus.CREATED.value());
			rs.setMessage("Driver,truck and carrier updated successfully");
			rs.setData(dr);

			return new ResponseEntity<ResponceStucture<Driver_Class>>(rs, HttpStatus.OK);
			
	}
	

}
