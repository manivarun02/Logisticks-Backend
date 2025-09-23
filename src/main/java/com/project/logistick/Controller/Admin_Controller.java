package com.project.logistick.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Address;
import com.project.logistick.Entitiesclasses.Carrier;
import com.project.logistick.Entitiesclasses.Driver_Class;
import com.project.logistick.Entitiesclasses.Loading;
import com.project.logistick.Entitiesclasses.Order;
import com.project.logistick.Entitiesclasses.Truck;
import com.project.logistick.Entitiesclasses.Unloading;
import com.project.logistick.Services.Address_Services;
import com.project.logistick.Services.Carrier_Services;
import com.project.logistick.Services.Driver_Services;
import com.project.logistick.Services.Loading_Services;
import com.project.logistick.Services.Order_Services;
import com.project.logistick.Services.Truck_Services;
import com.project.logistick.Services.Unloading_Services;
import jakarta.validation.Valid;

@RestController
//@RequestMapping("/admin")
public class Admin_Controller {
		
	// driver class crud operations
	@Autowired
	private Driver_Services drservice;

	@PostMapping("/savedriverdetails")
	public ResponseEntity<ResponceStucture<Driver_Class>> saveDriverDetails(@RequestBody @Valid Driver_Class d) {
		return drservice.saveDetails(d);
	}
	
	@GetMapping("/finddriver/{id}")
	public ResponseEntity<ResponceStucture<Driver_Class>> findDriverById(@PathVariable int id)
	{
		return drservice.findDriver(id);
	}
	
	@PutMapping("updatingdriverbytruckCarrier/{id}")
	public ResponseEntity<ResponceStucture<Driver_Class>> updateDriver(@PathVariable int id)
	{
		return drservice.updateDetails(id);
	}
	
	@DeleteMapping("/deletedriver/{id}")
	public ResponseEntity<ResponceStucture<Driver_Class>> deleteDriverDetails(@PathVariable int id)
	{
		 return drservice.deleteDriver(id);
	}

	
	// truck class CRUD operations
	
	@Autowired
	private Truck_Services trservice;

	@PostMapping("/savetruckdetails")
	public ResponseEntity<ResponceStucture<Truck>> saveTruckDetails(@RequestBody @Valid Truck t) {
		 return trservice.saveDetails(t);
	}
	
	@PutMapping("updatetruckcarrier/{id}")
	public ResponseEntity<ResponceStucture<Truck>> updateTruck(@PathVariable int id)
	{
		 return trservice.updateByIds(id);
	}
	
	@GetMapping("/findtruckid/{id}")
	public ResponseEntity<ResponceStucture<Truck>>  findByTruckId(@PathVariable int id)
	{
		return trservice.findById(id);
	}
	
	@DeleteMapping("/deletetruckid/{id}")
	public ResponseEntity<ResponceStucture<Truck>> deleteBydId(@PathVariable int id)
	{
		return trservice.deleteTruck(id);
	}

	
	// carrier class CRUD operations
	@Autowired
	private Carrier_Services crservies;

	@PostMapping("/savecarrier")
	public ResponseEntity<ResponceStucture<Carrier>> saveCarrierDetails(@RequestBody @Valid Carrier c)  {
		return crservies.saveDetails(c);
	}

	@GetMapping("/findcarrier/{id}")
	public ResponseEntity<ResponceStucture<Carrier>> findByCarrierId(@PathVariable int id) {
		 return crservies.findById(id);
	}
	
	@DeleteMapping("/deletecarrier/{id}")
	public ResponseEntity<ResponceStucture<Carrier>> deleteByCarrierId(@PathVariable int id)
	{
	  return crservies.deleteId(id);
	}

	
	//Address class CRUD operations
	@Autowired
	private Address_Services adservices;
	
	@PostMapping("/saveadress")
	public ResponseEntity<ResponceStucture<Address>> saveAdressDetails(@RequestBody @Valid Address ad)
	{
		return adservices.saveadress(ad);
	}
	
	@GetMapping("/findadress/{id}")
	public ResponseEntity<ResponceStucture<Address>> findAdressDetail(@PathVariable int id)
	{
		return adservices.findAdress(id);
	}
	
	@DeleteMapping("/deleteAdress/{id}")
	public ResponseEntity<ResponceStucture<Address>> deleteAdress(@PathVariable int id)
	{
		return adservices.deleteAdress(id);
	}

	
	//Order CRUD Operations
	@Autowired
	private Order_Services orderservice;

	//update carrier by truck id
	@PutMapping("/updateorderassigncarrie/{id}/bytruckid/{truckid}")
	public ResponseEntity<ResponceStucture<Order>> updateOrderByTruckId(@PathVariable int id, @PathVariable int truckid)
	{
		return orderservice.updateOrder(id,truckid);
	}
	//updating loading unloading date and time
	@PutMapping("/updateloadingunloadingdatebyorder/{orderid}")
	public ResponseEntity<ResponceStucture<Order>> updateDateTime(@PathVariable int orderid)
	{
		return orderservice.updateLoadingUnloadingDate(orderid);
	}
	
	//Loading CRUD Operations
	
	@Autowired
	private Loading_Services loadservice;
	
	@PostMapping("/saveloadinglocation")
	public ResponseEntity<ResponceStucture<Loading>> pickUpLocation(@RequestBody @Valid Loading load)
	{
		return loadservice.pickParcel(load);
	}
	
	@GetMapping("/findinglocation/{id}")
	public ResponseEntity<ResponceStucture<Loading>> findLocation(@PathVariable int id)
	{
		return loadservice.findLocation(id);
	}
	
	@DeleteMapping("/RemoveLocation/{id}")
	public ResponseEntity<ResponceStucture<Loading>> removeLocation(@PathVariable int id)
	{
		return loadservice.deleteLocation(id);
	}

//	//unloading order CRUD operation
	@Autowired
	private Unloading_Services unloaddetails;
	
	@PostMapping("/saveunloadingadress")
	public ResponseEntity<ResponceStucture<Unloading>> deliverAdress(@RequestBody Unloading unload)
	{
		return unloaddetails.addDeliverAdress(unload);
	}
	
	@GetMapping("/finddeliverAdress/{id}")
	public ResponseEntity<ResponceStucture<Address>> findDeliverlyDetails(@PathVariable int id)
	{
		return unloaddetails.findDelivery(id);
	}
	
	@DeleteMapping("cancle/{id}")
	public ResponseEntity<ResponceStucture<Unloading>> cancleDetails(@PathVariable int id)
	{
		return unloaddetails.cancleDetails(id);
	}
	

}
