package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.logistick.Entitiesclasses.Truck;
@Repository
public interface Truck_Repo extends JpaRepository<Truck, Integer> {

}
