package com.alpha.ZipRide.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ZipRide.Entity.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

	Driver findByDrivermobileno(long drivermobileno);



}
