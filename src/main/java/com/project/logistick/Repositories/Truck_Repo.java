package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


import com.project.logistick.Entitiesclasses.Truck;
@Repository
public interface Truck_Repo extends JpaRepository<Truck, Integer> {

	@Query("SELECT t FROM Truck t WHERE t.driver.id = :driverId")
	Truck findTruckByDriverId(@Param("driverId") int driverId);
	

	@Query("SELECT t FROM Truck t WHERE t.status = 'Available'")
	List<Truck> findAvailableTrucks();
	
	List<Truck> findByStatus(String status);
	Truck findByNumber(String number);



}
