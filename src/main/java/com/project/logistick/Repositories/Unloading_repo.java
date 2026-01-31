package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.logistick.Entitiesclasses.Address;
import com.project.logistick.Entitiesclasses.Unloading;
@Repository
public interface Unloading_repo extends JpaRepository<Unloading, Integer> {

	


}
