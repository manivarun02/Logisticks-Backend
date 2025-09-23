package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.logistick.Entitiesclasses.Cargo;
@Repository
public interface Cargo_Repo extends JpaRepository<Cargo, Integer> {

}
