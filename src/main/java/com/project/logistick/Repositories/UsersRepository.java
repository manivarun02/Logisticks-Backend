package com.project.logistick.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.logistick.Entitiesclasses.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email); // for login
}
