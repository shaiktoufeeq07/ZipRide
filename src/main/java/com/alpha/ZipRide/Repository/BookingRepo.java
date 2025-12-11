package com.alpha.ZipRide.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.ZipRide.Entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
