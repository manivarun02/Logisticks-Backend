package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import com.project.logistick.Entitiesclasses.Driver_Class;
@Repository
public interface Driver_Repo extends JpaRepository<Driver_Class, Integer> {
	
	@Query("SELECT d FROM Driver_Class d WHERE d.truck IS NULL")
	List<Driver_Class> findAvailableDrivers();

}
