package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.logistick.Entitiesclasses.Carrier;

@Repository
public interface Carrier_Repo extends JpaRepository<Carrier, Integer> {

}
