package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.logistick.Entitiesclasses.Driver_Class;
@Repository
public interface Driver_Repo extends JpaRepository<Driver_Class, Integer> {

}
