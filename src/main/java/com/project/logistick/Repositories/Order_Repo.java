package com.project.logistick.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.logistick.Entitiesclasses.Order;

@Repository
public interface Order_Repo extends JpaRepository<Order, Long> {

    // Used to fetch orders for the logged-in user
    List<Order> findByUserId(Long userId);

    // Used for admin or driver tracking
    List<Order> findByTruckId(Integer truckId);
    
    // ✅ FIXED: Use truckId field directly (matches your findByTruckId method)
    @Query("SELECT COUNT(o) FROM Order o WHERE o.truckId = :truckId")
    long countByTruckId(@Param("truckId") Integer truckId);

 int countByTruckIdAndStatusNot(long i, String status);
    
    // ✅ Option 2: Custom query (if needed)
    @Query("SELECT COUNT(o) FROM Order o WHERE o.truckId = :truckId AND o.status != :status")
    int countActiveOrdersForTruck(@Param("truckId") Long truckId, @Param("status") String status);
}
