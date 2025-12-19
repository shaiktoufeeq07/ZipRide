package com.alpha.ZipRide.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.alpha.ZipRide.Entity.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

    Driver findByDrivermobileno(long drivermobileno);

    @Query("SELECT d FROM Driver d WHERE d.drivername = :name")
    Driver findDriverByName(@Param("name") String name);
}
