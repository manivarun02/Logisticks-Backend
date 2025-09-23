package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.logistick.Entitiesclasses.Order;

@Repository
public interface Order_Repo extends JpaRepository<Order, Integer> {

	

}
