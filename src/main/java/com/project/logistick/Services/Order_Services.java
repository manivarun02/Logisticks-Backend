package com.project.logistick.Services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.logistick.Entitiesclasses.Address;
import com.project.logistick.Entitiesclasses.Cargo;
import com.project.logistick.Entitiesclasses.Loading;
import com.project.logistick.Entitiesclasses.Order;
import com.project.logistick.Entitiesclasses.Truck;
import com.project.logistick.Entitiesclasses.Unloading;
import com.project.logistick.Exceptions.OrderCanceled;
import com.project.logistick.Exceptions.OrderNotFound;
import com.project.logistick.Exceptions.OrderNotPending;
import com.project.logistick.Exceptions.TruckCapacity;
import com.project.logistick.Repositories.Adress_Repo;
import com.project.logistick.Repositories.Cargo_Repo;
import com.project.logistick.Repositories.Carrier_Repo;
import com.project.logistick.Repositories.Loading_Repo;
import com.project.logistick.Repositories.Order_Repo;
import com.project.logistick.Repositories.Truck_Repo;
import com.project.logistick.Repositories.Unloading_repo;
import com.project.logistick.DTO.OrderDto;
import com.project.logistick.DTO.ResponceStucture;

@Service
public class Order_Services {
	@Autowired
	private Order_Repo orderepo;
	@Autowired
	private Adress_Repo adrepo;
	@Autowired
	private Cargo_Repo crepo;
	@Autowired
	private Loading_Repo lrepo;
	@Autowired
	private Unloading_repo unrepo;

	public ResponseEntity<ResponceStucture<Order>> orderPlacing(OrderDto orderdto) {
		Order od=new Order();
		
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=format.format(date);
		
		double cost=100*(orderdto.getCargowieght()*orderdto.getCarcount());
		//order class
		od.setOrderDate(currentDate);
		od.setCost(cost);
		od.setCarrier(null);
		//cargo class
		Cargo cargo=new Cargo();
		cargo.setId(orderdto.getCargoid());
		cargo.setCount(orderdto.getCarcount());
		cargo.setDiscription(orderdto.getCargodiscription());
		cargo.setName(orderdto.getCargoname());
		cargo.setWeight(orderdto.getCargowieght());
		cargo=crepo.save(cargo);
		
		od.setCargo(cargo);
		//loading class
		Loading load=new Loading();
		Address adres=adrepo.findById(orderdto.getLoadid()).get();
		load.setId(orderdto.getLoadid());
		load.setAdress(adres);
		
		load=lrepo.save(load);
		od.setLoading(load);
		//unloading class
		Unloading unload=new Unloading();
		Address unloadadres=adrepo.findById(orderdto.getUnloadid()).get();
		unload.setId(orderdto.getUnloadid());
		unload.setAdress(unloadadres);
		unload=unrepo.save(unload);
		
		od.setUnloading(unload);
		orderepo.save(od);
		
		ResponceStucture<Order> rs= new ResponceStucture<Order>();
		
		rs.setCode(HttpStatus.CREATED.value());
		rs.setMessage("Order Placed successfully");
		rs.setData(od);
		
		return new ResponseEntity<ResponceStucture<Order>>(rs,HttpStatus.OK);
	
	}

	//finding order
	public ResponseEntity<ResponceStucture<Order>> tracingOrder(int id) {
		Optional<Order>ordopt=orderepo.findById(id);
		ResponceStucture<Order> rs=new ResponceStucture<Order>();
		if(ordopt.isPresent()) {
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("Order details of id "+id+" Found");
			rs.setData(ordopt.get());
			
		}
		else
		{

			throw new OrderNotFound();
		}

		return new ResponseEntity<ResponceStucture<Order>>(rs,HttpStatus.OK );
	}

	//cancle or delete order
	public ResponseEntity<ResponceStucture<Order>> cancleOrder(int id) {
		
		ResponceStucture<Order> rs=new ResponceStucture<Order>();
	
		boolean present=orderepo.existsById(id);
		if(present)
		{
			Order odr=orderepo.findById(id).get();
			if(odr.getStatus().equals("pending")) {
			odr.setStatus("cancle");
			//removing loading and unloading details
			odr.setLoading(null);
			odr.setUnloading(null);
			//Removing carrier
			odr.setCarrier(null);
			//Removing cargo
			odr.setCargo(null);
			
			orderepo.save(odr);
			rs.setCode(HttpStatus.OK.value());
			rs.setMessage("cancling the order with details of   id "+id);
			rs.setData(odr);
		}
		      else
		    {
			
			   throw new OrderNotPending();
	     	}
		}
		else {
		
			throw new OrderNotFound();
		}
      	return new ResponseEntity<ResponceStucture<Order>>(rs,HttpStatus.OK );
		
		
	}

	//update carrier by truck id assigning
	@Autowired
	Truck_Repo trepo;
	@Autowired
	Carrier_Repo carepo;

	public ResponseEntity<ResponceStucture<Order>> updateOrder(int id,int truckid) {
		
	 Order oder=orderepo.findById(id).get();
	 Truck tr=trepo.findById(truckid).get();
	 int truckcapacity=tr.getCapacity();
	 int orderweight=oder.getCargo().getWeight()*oder.getCargo().getCount();
	 
	 if(truckcapacity>=orderweight)
	 {
		 tr.setCapacity(tr.getCapacity()-orderweight);
		 oder.setCarrier(tr.getCarrier());
		 oder.setStatus("pending");
		 
		 orderepo.save(oder);
		 trepo.save(tr);
	 }
	 else
	 {
		 throw new TruckCapacity();
	 }
	 ResponceStucture<Order> rs = new ResponceStucture<Order>();

		rs.setCode(HttpStatus.CREATED.value());
		rs.setMessage("truck and carrier updated successfully");
		rs.setData(oder);

		return new ResponseEntity<ResponceStucture<Order>>(rs, HttpStatus.OK);
		
	}

	public ResponseEntity<ResponceStucture<Order>> updateLoadingUnloadingDate(int orderid) {
		Order od=orderepo.findById(orderid).get();
		
		
		if(od.getStatus().equals("cancle")) {
			throw new OrderCanceled();
		}
		else {

			//loading  date setting
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String currentDate=format.format(date);
			//time setting
			 LocalTime currentTime = LocalTime.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		     String time = currentTime.format(formatter);

			 od.getLoading().setDate(currentDate);
			 od.getLoading().setTime(time);
			 
			 //unloading date setting
			    LocalDate today = LocalDate.now();
			    LocalDate futureDate = today.plusDays(5);
			    //time setting
		        DateTimeFormatter futureformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		        String futuredate = futureDate.format(futureformatter);
		        LocalDateTime now = LocalDateTime.now();
		        // Add 5 days
		        LocalDateTime futureDateTime = now.plusDays(5);
		        // Format as String
		        DateTimeFormatter futureTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		        String dateTime = futureDateTime.format(futureTime);
		        //setting unloading
			    od.getUnloading().setDate(futuredate);
			    od.getUnloading().setTime(dateTime);
			    //saving
			    orderepo.save(od);
		}
		 
		    ResponceStucture<Order> rs = new ResponceStucture<Order>();

			rs.setCode(HttpStatus.CREATED.value());
			rs.setMessage("Loading,unloading date and time updated successfully");
			rs.setData(od);

			return new ResponseEntity<ResponceStucture<Order>>(rs, HttpStatus.OK);

		
		
	}
	
}

//	public ResponseEntity<ResponceStucture<Order>> updateunloadingdate(int id) {
//		Order od=orderepo.findById(id).get();
//		 LocalDate today = LocalDate.now();
//		    LocalDate futureDate = today.plusDays(5);
//
//	        DateTimeFormatter futureformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//	        String futuredate = futureDate.format(futureformatter);
//	        
//	        LocalDateTime now = LocalDateTime.now();
//
//	        // Add 5 days
//	        LocalDateTime futureDateTime = now.plusDays(5);
//
//	        // Format as String
//	        DateTimeFormatter futureTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//	        String dateTime = futureDateTime.format(futureTime);
//
//
//
//		 od.getUnloading().setDate(futuredate);
//		 od.getUnloading().setTime(dateTime);
//		 
//		 
//		 orderepo.save(od);
//		 
//		 ResponceStucture<Order> rs = new ResponceStucture<Order>();
//
//			rs.setCode(HttpStatus.CREATED.value());
//			rs.setMessage("Unoading date and time updated successfully");
//			rs.setData(od);
//
//			return new ResponseEntity<ResponceStucture<Order>>(rs, HttpStatus.OK);
//		
//	}
	

