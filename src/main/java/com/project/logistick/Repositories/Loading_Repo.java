package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.logistick.Entitiesclasses.Loading;
@Repository
public interface Loading_Repo extends JpaRepository<Loading, Integer> {

}
