package com.alpha.ZipRide.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ZipRide.Entity.Booking;
import com.alpha.ZipRide.Entity.Vehicle;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer>{

	List<Booking> findCompletedBookingsByMobile(long drivermobileno);

}
