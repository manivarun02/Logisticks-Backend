package com.project.logistick.Exceptions;
import com.project.logistick.Entitiesclasses.Address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Carrier;
import com.project.logistick.Entitiesclasses.Driver_Class;
import com.project.logistick.Entitiesclasses.Loading;
import com.project.logistick.Entitiesclasses.Order;
import com.project.logistick.Entitiesclasses.Truck;
import com.project.logistick.Entitiesclasses.Unloading;


@RestControllerAdvice
public class Global_ExceptionHandling {
	
	//exception for Address
	@ExceptionHandler(exception=AdressNotFound.class)
	public ResponseEntity<ResponceStucture<Address>> handleAdressNotFound() {
		ResponceStucture<Address> rs=new ResponceStucture<Address>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Adress Not found");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Address>>(rs,HttpStatus.NOT_FOUND);

}
	@ExceptionHandler(exception=AdressAlreadyExistException.class)
	public ResponseEntity<ResponceStucture<Address>> handleAdressAlreadyExist() {
		ResponceStucture<Address> rs=new ResponceStucture<Address>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Adress already Exist with Id");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Address>>(rs, HttpStatus.NOT_FOUND);
		
	}
	//exception for Carrier
	@ExceptionHandler(exception=CarrierNotFound.class)
	public ResponseEntity<ResponceStucture<Carrier>> handleCarrierNotFound() {
		ResponceStucture<Carrier> rs=new ResponceStucture<Carrier>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Carrier Not found");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Carrier>>(rs, HttpStatus.NOT_FOUND);

}
	@ExceptionHandler(exception=CarrierAlreadyExistException.class)
	public ResponseEntity<ResponceStucture<Carrier>> handleCarrierAlreadyExist() {
		ResponceStucture<Carrier> rs=new ResponceStucture<Carrier>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Carrier  already Exist with Id ");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Carrier>>(rs, HttpStatus.NOT_FOUND);
		
	}
	
	//Exception for Truck
	@ExceptionHandler(exception=TruckNotFound.class)
	public ResponseEntity<ResponceStucture<Truck>> handleTruckNotFound() {
		ResponceStucture<Truck> rs=new ResponceStucture<Truck>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Truck Not found");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Truck>>(rs, HttpStatus.NOT_FOUND);

}
	@ExceptionHandler(exception=TruckAlreadyExistException.class)
	public ResponseEntity<ResponceStucture<Truck>> handleTruckAlreadyExist() {
		ResponceStucture<Truck> rs=new ResponceStucture<Truck>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Truck with this id already exist");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Truck>>(rs, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(exception=TruckAndCArrierNotFound.class)
	public ResponseEntity<ResponceStucture<Truck>> handleTruck_Carrier() {
		ResponceStucture<Truck> rs=new ResponceStucture<Truck>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Truck and carrier with ids not exist");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Truck>>(rs, HttpStatus.NOT_FOUND);
		
	}

//	 Exception for Driver
	@ExceptionHandler(exception=DriverNotFound.class)
	public ResponseEntity<ResponceStucture<Driver_Class>> handleDriverNotFound() {
		ResponceStucture<Driver_Class> rs=new ResponceStucture<Driver_Class>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Driver Not found");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Driver_Class>>(rs, HttpStatus.NOT_FOUND);

}
	@ExceptionHandler(exception=DriverAlreadyExistException.class)
	public ResponseEntity<ResponceStucture<Driver_Class>> handleDriverAlreadyExist() {
		ResponceStucture<Driver_Class> rs=new ResponceStucture<Driver_Class>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Driver with this id is already exist");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Driver_Class>>(rs, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(exception=DriverTruckCarrierNotFound.class)
	public ResponseEntity<ResponceStucture<Driver_Class>> handleDriver_Truck_Carrier() {
		ResponceStucture<Driver_Class> rs=new ResponceStucture<Driver_Class>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Driver,Truck,Carrier with this ids not exist");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Driver_Class>>(rs, HttpStatus.NOT_FOUND);
		
	}
	
//	Exception for Order
	@ExceptionHandler(OrderNotFound.class)
	public ResponseEntity<ResponceStucture<Order>> handleOrderNotFound() {
		ResponceStucture<Order> rs=new ResponceStucture<Order>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Order Details Not Found");
		rs.setData(null);
		return new ResponseEntity<ResponceStucture<Order>>(rs, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(OrderNotPending.class)
	public ResponseEntity<ResponceStucture<Order>> handleOrderNotPending() {
		ResponceStucture<Order> rs=new ResponceStucture<Order>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Order Details with id Status is  in  Pending");
		rs.setData(null);
		return new ResponseEntity<ResponceStucture<Order>>(rs, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(OrderCanceled.class)
	public ResponseEntity<ResponceStucture<Order>> handleOrderCanceled() {
		ResponceStucture<Order> rs=new ResponceStucture<Order>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Order was cancled by cumstomer");
		rs.setData(null);
		return new ResponseEntity<ResponceStucture<Order>>(rs, HttpStatus.NOT_FOUND);
		
	}
	//truck capacity exception
	@ExceptionHandler(exception=TruckCapacity.class)
	public ResponseEntity<ResponceStucture<Truck>> handleTruckCapacity() {
		ResponceStucture<Truck> rs=new ResponceStucture<Truck>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("No Space Avaiable in Truck");
		rs.setData(null);
		
		return new ResponseEntity<ResponceStucture<Truck>>(rs, HttpStatus.NOT_FOUND);
		
	}
	
	//exception for loading
	@ExceptionHandler(LoadAdressNotFound.class)
	public ResponseEntity<ResponceStucture<Loading>> handleloadadressNotFound() {
		ResponceStucture<Loading> rs=new ResponceStucture<Loading>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Loading address Details Not Found with Order Id");
		rs.setData(null);
		return new ResponseEntity<ResponceStucture<Loading>>(rs, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(LoadAlreadyExist.class)
	public ResponseEntity<ResponceStucture<Loading>> handleloadAlreadyExist() {
		ResponceStucture<Loading> rs=new ResponceStucture<Loading>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Loading address Already exist with id");
		rs.setData(null);
		return new ResponseEntity<ResponceStucture<Loading>>(rs, HttpStatus.NOT_FOUND);
		
	}
	//exception for unloading
	@ExceptionHandler(UnloadAdressNotFOund.class)
	public ResponseEntity<ResponceStucture<Unloading>> handleUnloadadressNotFound() {
		ResponceStucture<Unloading> rs=new ResponceStucture<Unloading>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Order Details Not Found with Order Id");
		rs.setData(null);
		return new ResponseEntity<ResponceStucture<Unloading>>(rs, HttpStatus.NOT_FOUND);
		
	}
	//exception for Cargo
	@ExceptionHandler(CargoNotFound.class)
	public ResponseEntity<ResponceStucture<Unloading>> handleCargoNotFound() {
		ResponceStucture<Unloading> rs=new ResponceStucture<Unloading>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Cargo Details Not Found");
		rs.setData(null);
		return new ResponseEntity<ResponceStucture<Unloading>>(rs, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(CargoAlreadyExistException.class)
	public ResponseEntity<ResponceStucture<Unloading>> handleCargoAlreadyexist() {
		ResponceStucture<Unloading> rs=new ResponceStucture<Unloading>();
		rs.setCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Cargo with this id already exist");
		rs.setData(null);
		return new ResponseEntity<ResponceStucture<Unloading>>(rs, HttpStatus.NOT_FOUND);
		
	}
	//Exception for invalid data entered
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponceStucture<Map<String, String>>> handleMethodArgumentNotException(MethodArgumentNotValidException ex)
	{
		List<ObjectError>erorrs=ex.getAllErrors();
		Map<String, String> errormap=new HashMap<String, String>();
		for (ObjectError objectError : erorrs) {
			FieldError fielderror=(FieldError) objectError;
			errormap.put(fielderror.getField(), objectError.getDefaultMessage());			
		}
		ResponceStucture<Map<String, String>> responce=new ResponceStucture<Map<String,String>>();
		responce.setCode(HttpStatus.NOT_ACCEPTABLE.value());
		responce.setMessage("YOU ENTERED INVALID DATA ENTERED");
		responce.setData(errormap);
		return new ResponseEntity<ResponceStucture<Map<String, String>>>(responce,HttpStatus.OK);
	}
	
}
