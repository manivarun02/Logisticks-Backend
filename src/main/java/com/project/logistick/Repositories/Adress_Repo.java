package com.project.logistick.Repositories;
import com.project.logistick.Entitiesclasses.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Adress_Repo extends JpaRepository<Address, Integer> {

}
