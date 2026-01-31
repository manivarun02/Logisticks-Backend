package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.logistick.Entitiesclasses.Address;

@Repository
public interface Adress_Repo extends JpaRepository<Address, Integer> {
}
